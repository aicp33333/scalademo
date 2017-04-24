package com.spark2

import net.sf.json.JSONObject
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}



/**
  * Created by rongpei on 2017/4/21.
  */
object test {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local").appName("test").getOrCreate()

    val sc =sparkSession.sparkContext
    import sparkSession.implicits._

    val file=sparkSession.read.text("/Users/rongpei/work/54.169.233.67-notice-ad_server_empty.log.2017-03-12-00").as[String]



//  val dt=  file.toJavaRDD.rdd.map{
//      line =>
//        val lines = line.split("\t",-1)
//        val jsonstr = lines(2).split("-params:")(1)
//
//        val js = JSONObject.fromObject(jsonstr)
//        Row(lines(0),js.getString("timestamp"),js.getString("appId"),js.getString("unitId"))
//    }
//    val schema = StructType(Array(StructField("datea", DataTypes.StringType), StructField("timestamp", DataTypes.StringType)
//      ,StructField("appId",DataTypes.StringType),StructField("unitId",DataTypes.StringType) ))
//
//    val peopleDF = sparkSession.createDataFrame(dt, schema)
//    peopleDF.createOrReplaceTempView("test")

   // sparkSession.sql("select datea, timestamp, appId, unitId from test where appId ='24178'")//.write.format("parquet").save("/Users/rongpei/work/namesAndAges.parquet")



    val dd= file.map{
      line =>
        val lines = line.split("\t",-1)
        val jsonstr = lines(2).split("-params:")(1)

        val js = JSONObject.fromObject(jsonstr)
        (lines(0),js.getString("timestamp"),js.getString("appId"),js.getString("unitId"))
    }.toDF("datea","timestamp","appId","unitId")
    dd.printSchema()


    dd.map(teenager => "date: " + teenager(0)).show()
  }

}
