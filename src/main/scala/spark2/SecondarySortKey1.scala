package com.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by rp on 17-3-10.
  */
object SecondarySortKey1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SecondarySortKey")
    val sc = new SparkContext("local","dataTest",conf)

    val arr  =Array(("ds",5,3),("ds",4,1),("asd",3,2),("fg",4,3),("fg",8,7),("asd",2,1))

    val rdd = sc.makeRDD(arr)
    val rdd1 = rdd.map{
      case (r1,r2,r3) =>
        (r1,(r2,r3))
    }.groupByKey().sortByKey(true).map{
      case (r1,li) =>
        (r1,(li.toList.sortWith(_._1>_._1)))
    }.collect().foreach(println)
    println("------------------------")

    rdd.map{
      case (r1,r2,r3) =>
        (r1,List(r2,r3))
    }.reduceByKey((a,b) => (a ++ b))



  }
}
