package scalikejdbc

import com.alibaba.druid.sql.SQLUtils
import com.alibaba.druid.sql.ast.SQLStatement
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor
import com.alibaba.druid.util.JdbcConstants
import scalikejdbc._

/**
  * Created by 2017/11/23.
  *
  * @author rongpei
  */
object ScalaJDBC  {

  def main(args: Array[String]): Unit = {
    Class.forName("com.mysql.jdbc.Driver")

    val url = "jdbc:mysql://192.168.158.101:3308/cif_utcs?useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false"
    val userName = "prod_cifutcs"
    val password = "prod_cifutcs.SRqqtLmVvmjOTn4q3oWRQmmGKroDotda"
    ConnectionPool.singleton(url, userName,password)

    implicit val session = AutoSession

    val entities: List[(String,String)] = sql"SELECT lable_name,lable_sql from lable_info where is_mongo_sql =0 "
      .map(rs=> (rs.string(1),rs.string(2)) ).list.apply()


    entities.map{
      case (k,v) =>
        try{

          val  sql =
            """
              |SELECT
              |MAX(CASE WHEN arp.reduce_special_service_fee > 0 THEN 1 ELSE 0 END) AS pysp_lend_special_before_repaid
              |FROM asset_core.asset_repay_plan arp, asset_core.asset_info ai, finup_lend.lend_request lr ,finup_lend.lend_customer lc
              |WHERE arp.asset_info_id = ai.id and ai.id = lr.core_lend_request_id and lc.id = lr.lend_customer_id
              |  and lc.id_no='$idNoCipher'
              | AND ai.channel_type IN ('LEND','LEND_CYCLE')
              |-- AND ai.`status` = 'BEFORE_REPAID'			--当前新核心库数据有问题，待修复后再决定是否加上该条件
              |GROUP BY lc.id_no
            """.stripMargin


        val stmtList:java.util.List[SQLStatement] = SQLUtils.parseStatements(v, JdbcConstants.MYSQL);
        println(k +"-----------")


          stmtList.toArray.map{
            stm =>
              val visitor = new MySqlSchemaStatVisitor
              stm.asInstanceOf[SQLStatement].accept(visitor)
              visitor.getTables.keySet.toArray.map{
                db_table =>

                  println(db_table.toString.split("\\.")(0))
              }
          }

        } catch {
          case _ => println(k)
            println(v)
           }

    }

  }

}
