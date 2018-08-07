package parser

/**
  * Created by 2017/12/6.
  *
  * @author rongpei
  */
object ParseExpr extends Arith {
  def main(args: Array[String]): Unit = {
    println(parseAll(expr," 12 - 1"))

  }

}
