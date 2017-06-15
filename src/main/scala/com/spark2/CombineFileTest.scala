package com.spark

import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat
import org.apache.spark.{SparkConf, SparkContext, SparkEnv}

/**
  * Created by rp on 16-12-9.
  */
object CombineFileTest {

  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//    val sc = new SparkContext("local","CombineFileTest",conf)
    val conf = new SparkConf().setAppName("CombineFileTest").set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    val hconf = new Configuration();
    hconf.set("mapred.max.split.size","102400000");
    val a = sc.newAPIHadoopFile(
      "hdfs://10.95.3.138:8020/data/stg_dp/s_basic_para/*/*",classOf[CombineTextInputFormat],classOf[LongWritable],classOf[Text],hconf
    )

    val acc=sc.accumulator(0,"acc")



    //sc.hadoopConfiguration.set("mapred.max.split.size","102400")
    //val a =sc.newAPIHadoopFile[LongWritable, Text, CombineTextInputFormat]("hdfs://127.0.0.1:9000/user/jack/data/pagerank_data.txt")

    val t = a.map(_._2.toString).map{
      str =>
        val s = str.split("\001",2)

        acc += 1
        acc.value.compare(1)
        (s(0),(s(1),s(0)))

    }

    t.sortByKey().sortBy(_._2)

    val c = t.map{
      case (k,v) =>
        k+","+v
    }

    c.saveAsTextFile("hdfs://10.95.3.138:8020/user/tescomm/rp/test/combine")
  }


}
