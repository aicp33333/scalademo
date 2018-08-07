package spark2

import scala.util.parsing.json.JSON

/**
  * Created by 2017/11/14.
  *
  * @author rongpei
  */
object JsonDemo {

  def main(args: Array[String]): Unit = {

    val tt= "{\"me\":\"123asdq\",\"ere\":true}"

    val b = JSON.parseFull(tt)
    println(b)
    b match {
      // Matches if jsonStr is valid JSON and represents a Map of Strings to Any
      case Some(map: Map[String, Any]) => println(map)
      case None => println("None")
      
    }






  }

}
