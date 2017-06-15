package com.spark

import org.apache.spark.{RangePartitioner, SparkConf, SparkContext}

/**
  * Created by rp on 17-3-9.
  */
object SparkSort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkSort")

    val sc = new SparkContext(conf)


    val s_basic_flow = sc.textFile("hdfs://cloud138:8020/data/stg_dp/s_basic_flow/1/*/*/")

     val rdd1 = s_basic_flow.map{
       line =>
         val lines = line.split("\001",-1)
         (lines(4),List(1))
     }.reduceByKey((x,y) =>(x,y).zipped.map(_+_)).map{
       case (k,v) =>
         (v(0),k)
     }

   val sort= rdd1.repartitionAndSortWithinPartitions(new RangePartitioner(10,rdd1,false))//.map{
      //case (k,v) =>
       // (v,k)
    //}//.saveAsTextFile("hdfs://cloud138:8020/user/tescomm/rp/test/170309")
       sort.top(10).foreach(println)
  }
}
