package demo

import scala.io.Source
import java.io.FileWriter

/**
  *  ${todo}
  *
  * @author rongpei
  * 2018/5/29
  */
object ReadFile {

  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("/Users/rongpei/Downloads/graylog-search-result-absolute-2018-07-25T06_10_00.000Z-2018-07-25T06_20_00.000Z.csv").getLines().toList

    val out = new FileWriter("/Users/rongpei/Downloads/paixu.txt",true)

    val tt=source.map{
      line =>
       val tm= line.split(",")
        (tm(0),line)
    }

    tt.sortWith((s,t) => s._1.compareTo(t._1) <0).map{
      case (_,t2) =>
        out.write(t2)
        out.write("\r\n")
        //println(t2)

    }
  }

}
