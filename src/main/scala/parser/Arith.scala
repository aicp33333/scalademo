package parser

import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by 2017/12/6.
  *
  * @author rongpei
  */
class Arith extends JavaTokenParsers{

  def expr : Parser[Any] = trem~rep("+"~trem | "-"~trem)
  def trem : Parser[Any] = factor~rep("*"~factor | "/"~factor)
  def factor : Parser[Any] = floatingPointNumber | "("~expr~")"

}
