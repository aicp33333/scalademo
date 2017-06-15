package com.spark

import org.apache.spark.streaming.{Duration, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * Created by rp on 16-12-15.
  */
object SparkShuffle {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("dataTest")
    val sc = new SparkContext("local","dataTest",conf)
    //val sc = new SparkContext(conf)



//    val s_basic_flow = sc.textFile("hdfs://10.95.3.138:8020/data/stg_dp/s_basic_flow/*/*" )
//    val s_basic_app_use = sc.textFile("hdfs://10.95.3.138:8020/data/stg_dp/s_basic_app_use/*/*")
//
//    val rdd1 = s_basic_flow.map{
//      line =>
//        val lines =line.split("\001",-1)
//        (lines(1),line)
//    }.partitionBy(new HashPartitioner(10))
//
//
//
//    val rdd2 = s_basic_app_use.map{
//      line =>
//        val lines =line.split("\001",-1)
//        (lines(15),line)
//    }.partitionBy(new HashPartitioner(50))
//
//
//
//    rdd1.join(rdd2).saveAsTextFile("hdfs://10.95.3.138:8020/user/tescomm/rp/test/SparkShuffle")


    val test = sc.textFile("/home/rp/d/test.txt");
    val ssc = new StreamingContext(sc,Duration(2000))
    val accum = ssc.sparkContext.accumulator(0,"broadcasttest")

    val rdd1 = test.map{
      line =>
        accum += 1
        val lines = line.split(",",-1)
        (lines(1),lines)
    }.groupByKey().sortByKey(false).map{
      case (k,v) =>
        (k, v.toList.sortWith(_(0)>_(0)))
    }.flatMap{
      case (k,v) =>
        v
    }

    //test.foreach( x =>  accum += 1)
    accum.value

    println(accum.value+"----------------")


    //rdd1.collect().map(x => println(x.mkString(",")))

  }


}
