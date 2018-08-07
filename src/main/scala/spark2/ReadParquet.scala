package com.spark2

import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by rongpei on 2017/4/21.
  */
object ReadParquet {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local").appName("test").getOrCreate()

    val sc = sparkSession.sparkContext


    val file = sparkSession.read.parquet("/Users/rongpei/work/namesAndAges.parquet/*")

    val rdd = file.toJavaRDD



  }


}
