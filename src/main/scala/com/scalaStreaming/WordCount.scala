package com.scalaStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.log4j.{Level, Logger}
/**
  * Created by rongpei on 2017/5/31.
  */
object WordCount {
  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(10))

    val lines = ssc.socketTextStream("localhost", 9999)
   // val lines = ssc.textFileStream("file:///Users/rongpei/work/tmp/")
    // Split each line into words

    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    val  windows=wordCounts.window(Seconds(30),Seconds(10)).groupByKey().map{
      case (k,v)=>
        (k,v.sum)
    }
    // Print the first ten elements of each RDD generated in this DStream to the console
    windows.print()
    windows.saveAsTextFiles("/Users/rongpei/work/tmp/")

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate

  }


}
