package com.spark2

import org.apache.spark.sql.SparkSession


/**
  * Created by rongpei on 2017/3/28.
  */
object DataetT {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder.master("local").appName("example").getOrCreate()

    import sparkSession.implicits._
    val data = sparkSession.read.text("/Users/rongpei/file/ideaerror.txt").as[String]
    val words = data.flatMap(value => value.split(""))
    val groupwords = words.groupByKey(_.toLowerCase)
    val couts = groupwords.count()
    couts.show()


  }
}
