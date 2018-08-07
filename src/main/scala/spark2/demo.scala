package com.spark2

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
  * Created by rongpei on 2017/5/26.
  */
object demo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Dataskew")
    val sc = new SparkContext("local", "Dataskew", conf)
    var rdd1 = sc.makeRDD(1 to 5,2)

   val rdd2= rdd1.map{
      x =>
        (x,x)
    }.partitionBy(new HashPartitioner(2)).map {
      case (k, v) =>
        v
    }

    var rdd3 = rdd2.mapPartitions{ x => {
      x
      print("-----------")
      val list = new ListBuffer[Int]()
      while (x.hasNext){
        list += x.next()
      }
      list += list.sum
      list.iterator
      }
     }
    print(rdd3.collect().mkString(","))

  }


}
