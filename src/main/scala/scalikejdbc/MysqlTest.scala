package scalikejdbc

import java.io.{File, PrintWriter}
import java.sql.{Connection, DriverManager, ResultSet, SQLException}
import java.util.Date

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by 2018/3/20.
  *
  * @author rongpei
  */
object MysqlTest {
  val tableNameList = new ArrayBuffer[String]()
  var tMap = scala.collection.mutable.Map[String,Int]()
  def main(args: Array[String]): Unit = {
     val url = "jdbc:mysql://192.168.153.2:3309/finup_core?user=cif&password=PNF)LS~5I9Ti"
    //val url = "jdbc:mysql://192.168.192.9:4000/finup_core?user=root&password=hGDQYG0huSed"
     val conn = getConnection(url,"com.mysql.jdbc.Driver")

    val stat = conn.createStatement()


//    val arr = getSql("/Users/rongpei/work/casejmeter.txt")
//
//    val writer = new PrintWriter(new File("/Users/rongpei/work/TIDBqryueTime.txt"))

      // stat.setQueryTimeout(15)


    val sql =
      """
        |select max(case when status = 'OVER_DUE' then 1 else 0 end )  as USRCV064
        |from
        |  finup_core.core_asset_customer as cac right join
        |  finup_core.core_customer as cc  on cc.id_no = cac.id_no
        |  right join
        |  finup_core.core_lend_request as clr on clr.core_lend_customer_id=cc.id
        |where
        |cac.mobile = '$$mobile' and
        |(clr.request_source like 'JA%' or clr.request_source like 'JR%')
        |group by cac.mobile
      """.stripMargin

     val  rs:ResultSet= stat.executeQuery(sql)

    while (rs.next()){
      println(rs.getObject(0))
    }


//    arr.map{
//      case(lable,sql)=>
//        val l1 = System.currentTimeMillis()
//        try{
//          stat.executeQuery(sql)
//          println(lable +": 执行时间 :"+(System.currentTimeMillis() - l1))
//          writer.write(lable +": 执行时间 :"+(System.currentTimeMillis() - l1))
//          writer.write("\n")
//        }catch {
//          case ex :SQLException =>{
//            println(lable +": 执行时间 :"+(System.currentTimeMillis() - l1))
//            writer.write(lable +": 超过15秒未执行完成")
//            writer.write("\n")
//          }
//
//        }
//
//
//
//    }
//    writer.flush()
//    writer.close()
   }


  def getSql(path : String ): Array[(String,String)] ={
    val source = Source.fromFile(path).getLines().toArray
     val sqlArr =new ArrayBuffer[(String,String)]()
    source.map{
      line =>
        sqlArr +=((sqlArr.size.toString,line))
    }
    sqlArr.toArray
  }


  def getTableCount(conn: Connection, fulltableName: String):Int = {
    val sql = "select count(*) as count from {tableName}".replace("{tableName}",fulltableName)

    val stat = conn.createStatement()
    var resultSet:ResultSet = null
    var count = -1
    try {
      resultSet = stat.executeQuery(sql);
      while (resultSet.next()) {
        count = resultSet.getInt("count")
      }
    }catch {
      case e:Exception=>{
        println(sql)
        e.printStackTrace()
      }
    }finally {
      if (resultSet!=null) resultSet.close()
      stat.close()
    }
    count
  }
  def getConnection(dbUrl: String, driverClass: String) :Connection= {
    Class.forName(driverClass)
    val connection = DriverManager.getConnection(dbUrl)
    connection
  }

}
