package com.spark2

import java.util.Random

import net.sf.json.JSONObject
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by rongpei on 2017/5/23.
  */
object Dataskew {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Dataskew")
    val sc = new SparkContext("local", "Dataskew", conf)
    val abserver = sc.textFile("/Users/rongpei/work/52.57.82.83-notice-ad_server_empty.log.2017-05-20-00.gz")
    val abserver2 = sc.textFile("/Users/rongpei/work/52.59.33.66-notice-ad_server_empty.log.2017-05-20-00.gz")


//                val str= abserver.first().split("\t",-1)
//
//                val tmp = str(2)
//                val jsontmp =tmp.substring(tmp.indexOf("{"),tmp.lastIndexOf("}")+1)
//                println(jsontmp)
//
//                val jsonStr= JSONObject.fromObject(jsontmp)
//                println(jsonStr.get("googleAdvertisingId"))
//                println(jsonStr.get("installIdSet").equals("null"))
//                println(jsonStr.get("includePackageNameSet"))

        val ran = new Random
        val rdd1 = abserver.flatMap {
          line =>
            val lines = line.split("\t", -1)
            val tmp = lines(2)
            val jsontmp = tmp.substring(tmp.indexOf("{"), tmp.lastIndexOf("}") + 1)
            val jsonStr = JSONObject.fromObject(jsontmp)
            //val listIds = new ArrayBuffer[(String, (String, String, String, String))]()



         //   if (!"null".equals(jsonStr.get("googleAdvertisingId")) || !"null".equals(jsonStr.get("idfa"))) {
              val installIdSet = jsonStr.get("installIdSet")
              val includePackageNameSet = jsonStr.get("includePackageNameSet")
              val packageName = jsonStr.get("packageName")
              val includePackageName = jsonStr.get("platform")
//              if ("null".equals(installIdSet) || "null".equals(includePackageNameSet)) {
//                val installIds = if (!"null".equals(installIdSet)) {
//                  JSONObject.fromObject(installIdSet).keySet().toArray()
//                } else {
//                  Array()
//                }
//                val mkPackageName = if (!"null".equals(includePackageNameSet)) {
//                  val tmpArray = JSONObject.fromObject(includePackageNameSet).keySet().toArray()
//                  if ("null".equals(packageName)) {
//                    tmpArray :+ packageName
//                  }
//                  tmpArray.mkString("@@")
//                } else {
//                  ""
//                }
//
//                installIds.map {
//                  id =>
//                    val tm= (id.toString +"_"+ran.nextInt(20), (jsonStr.get("googleAdvertisingId").toString, jsonStr.get("idfa").toString, mkPackageName, includePackageName))
//                    listIds += tm
//                }
//              }


         //   }
             List((jsonStr.get("googleAdvertisingId"),Array(installIdSet,includePackageNameSet,packageName,includePackageName).mkString("@@")),
               (jsonStr.get("idfa"),Array(installIdSet,includePackageNameSet,packageName,includePackageName).mkString("@@")))
        }.repartition(6)


        val rdd2  =abserver2.flatMap{
          line =>
            val lines = line.split("\t", -1)
            val tmp = lines(2)
            val jsontmp = tmp.substring(tmp.indexOf("{"), tmp.lastIndexOf("}") + 1)
            val jsonStr = JSONObject.fromObject(jsontmp)
            //val listIds = new ArrayBuffer[(String, (String, String, String, String))]()



            //   if (!"null".equals(jsonStr.get("googleAdvertisingId")) || !"null".equals(jsonStr.get("idfa"))) {
            val installIdSet = jsonStr.get("installIdSet")
            val includePackageNameSet = jsonStr.get("includePackageNameSet")
            val packageName = jsonStr.get("packageName")
            val includePackageName = jsonStr.get("platform")
            List((jsonStr.get("googleAdvertisingId"),Array(installIdSet,includePackageNameSet,packageName,includePackageName).mkString("@@")),
              (jsonStr.get("idfa"),Array(installIdSet,includePackageNameSet,packageName,includePackageName).mkString("@@")))
        }.repartition(5)


        val rdd3= rdd2.join(rdd1)

     val arr= rdd3.mapPartitionsWithIndex{
      (partIdx,iter) => {
        var part_map = scala.collection.mutable.Map[String,Int]()
        while(iter.hasNext){
          var part_name = "part_" + partIdx
          if(part_map.contains(part_name)) {
            var ele_cnt = part_map(part_name)
            part_map(part_name) = ele_cnt + 1
          } else {
            part_map(part_name) = 1
          }
          iter.next()
        }
        part_map.iterator

      }
    }.collect


    arr.foreach(print)
  }
}
