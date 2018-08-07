package com.spark2

import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable.ArrayBuffer

/**
  * Created by rongpei on 2017/6/15.
  */
object SecondarySort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SecondarySort")
    val sc = new SparkContext("local", "SecondarySort", conf)

     val rdd = sc.textFile("/Users/rongpei/work/test.txt")

     val rdd1= rdd.map{
       line =>
         val lines = line.split(" ")
         (lines(0),lines(1))
     }.groupByKey().sortByKey(false)

     rdd1.map{
       case (key,values) =>
         val sortVal = values.toList.sortWith(_>_)
         (key,sortVal)
     }.collect().map{
       case (k,v) =>
         println(k+"||"+v.mkString(","))
     }






  }

}
