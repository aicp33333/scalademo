package com.spark2

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by rongpei on 2017/3/28.
  */
object Rddt {

  def main(args: Array[String]): Unit = {

    val spark=SparkSession.builder().master("local").appName("test")
    val list=Array("","")




  }

}
