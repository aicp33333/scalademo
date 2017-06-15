package com.spark2

import net.sf.json.JSONObject
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}



/**
  * Created by rongpei on 2017/4/21.
  */
object test {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local").appName("test").getOrCreate()

    val sc =sparkSession.sparkContext
    import sparkSession.implicits._

    val file=sparkSession.read.text("/Users/rongpei/work/52.59.33.66-notice-ad_server_empty.log.2017-05-20-00.gz").as[String]


    val nameregex = """.*-params:\+(.*)\-result:.*""".r


  val dt=  file.toJavaRDD.rdd.map{
      line =>
        val lines = line.split("\t",-1)
        val jsonstr = lines(2)  match { case nameregex(bc) => bc }

        val js = JSONObject.fromObject(jsonstr)
        Row(lines(0),js.getString("timestamp"),js.getString("appId"),js.getString("unitId"))
    }
    val schema = StructType(Array(StructField("datea", DataTypes.StringType), StructField("timestamp", DataTypes.StringType)
      ,StructField("appId",DataTypes.StringType),StructField("unitId",DataTypes.StringType) ))

    val peopleDF = sparkSession.createDataFrame(dt, schema)
   // peopleDF.write.format("parquet").save("/Users/rongpei/work/namesAndAges.parquet")
    peopleDF.createOrReplaceTempView("test")

    sparkSession.sql("select datea, timestamp, appId, unitId from test where appId ='24178'").show()
    //.write.format("parquet").save("/Users/rongpei/work/namesAndAges.parquet")



    val dd= file.map{
      line =>
        val lines = line.split("\t",-1)
        val jsonstr = lines(2)  match { case nameregex(bc) => bc }

        val js = JSONObject.fromObject(jsonstr)
        (lines(0),js.getString("timestamp"),js.getString("appId"),js.getString("unitId"))
    }.toDF("datea","timestamp","appId","unitId")
    dd.printSchema()


    dd.map(teenager => "date: " + teenager(0)).show()
    dd.createOrReplaceTempView("parquet")
    dd.sparkSession.sql("select * from parquet ").show()
  }

}
