package demo

import java.util.{Calendar, Date}

import scala.collection.mutable.ListBuffer

/**
  * Created by 2018/3/5.
  *
  * @author rongpei
  */
object DateDmoe {
  def main(args: Array[String]): Unit = {
    println(getSimpleMonth(new Date , false))
    println(getMonthNum(new Date ))

    val l1 = List(Map("u"->1,"a"->3),Map("u"->2,"a"->5),Map("u"->3,"a"->6),Map("u"->5,"a"->6))
    val l2 = List(Map("u"->1,"c"->10),Map("u"->6,"a"->6))

    val l3 = List(Map("u" -> 1, "a" -> 3, "c" -> 10))


    (l1 ++ l2).map{
      map =>

    }






    val ok = ListBuffer[Map[String,Int]]()
    val else1 = ListBuffer[Map[String,Int]]()


       (l1 ++ l2).groupBy(_.getOrElse("u",0)).map{
       case  (a,b) =>
         b.flatten.toMap
     }.foreach(println)


//    for( map <- l1){
//
//
//      for(map2 <- l2){
//         val t1 = map.getOrElse("u",0)
//         val t2 = map2.getOrElse("u",0)
//        if(t1.equals(t2)){
//        val l3 = map.++:(map2)
//          ok.append(l3)
//        }else{
//          else1.append(map2)
//          else1.append(map)
//        }
//      }
//    }
//
//    val list= ok ++ else1.toList.distinct
//
//    ok.toList.foreach(println)
//    println("--------ok----------")
//    else1.toList.foreach(println)
//    println("--------else1----------")
//    list.foreach(println)




  }
  def getSimpleMonth(date: Date,flag: Boolean) = {
    val Uparr = Array("January","February","March","April","May","June","July","August","October","November","December")
    val LowerArr = Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec")
    val cal = Calendar.getInstance()
    cal.setTime(date)
    val i = cal.get(Calendar.MONTH)//
    println(i)
    if(flag){
      Uparr(i)
    } else {
      LowerArr(i)
    }
  }

  def getMonthNum(date: Date)= {
    val cal = Calendar.getInstance()
    cal.setTime(date)
    val num = cal.get(Calendar.MONTH)+1
    s"$num"
  }

}
