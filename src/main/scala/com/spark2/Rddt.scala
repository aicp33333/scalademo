package com.spark2

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by rongpei on 2017/3/28.
  */
object Rddt {

  def main(args: Array[String]): Unit = {

//    val sparkSession = SparkSession.builder.master("local").appName("example").getOrCreate()
//
//    val sc =sparkSession.sparkContext
//    sc.textFile("")
//
//    val rdd = sc.textFile("/Users/rongpei/file/ideaerror.txt").map(_.split(""))
//    val rddStringToRowRDD =rdd.map(value => Row(value))
//    val dfschema = StructType(Array(StructField("value",StringType)))
//    val rddToDF = sparkSession.createDataFrame(rddStringToRowRDD,dfschema)
//    val rDDToDataSet = rddToDF.as[String]
//
//    rDDToDataSet.toJavaRDD.rdd



  }

}
