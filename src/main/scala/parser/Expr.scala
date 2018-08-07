package parser

import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by 2018/1/4.
  *
  * @author rongpei
  */
abstract class Expr
case class Variable(name: String) extends Expr
case class Number(value: Double) extends Expr
case class UnaryOp(op: String, expr: Expr) extends Expr
case class BinaryOp(op: String, left: Expr, right: Expr) extends Expr

object Calc {

  object Arith extends JavaTokenParsers {
    def compose(bi: Expr, list: List[String ~ Expr]): Expr = list match {
      case Nil => bi
      case (op ~ e) :: t => compose(BinaryOp(op, bi, e), t)
    }
    def expr: Parser[Expr] = term ~ rep("+"~ term |"-"~ term) ^^ {
      case t ~ list => compose(t, list)
    }
    def term: Parser[Expr] = factor ~ rep("*"~ factor |"/"~ factor) ^^ {
      case t ~ list => compose(t, list)
    }
    def factor: Parser[Expr] = floatingPointNumber ^^ { x => Number(x.toDouble)} |"("~> expr <~")"

    def parse(str: String): Expr = {
      val result = parseAll(expr, str)
      if (result.successful)
        result.get
      else Number(0)
    }
  }

  def parse(str: String) = Arith.parse(str)

  def evaluate(e: Expr): Double = simplify(e) match {
    case Number(x) => x
    case UnaryOp("-", x) => -evaluate(x)
    case BinaryOp("+", x1, x2) => (evaluate(x1) + evaluate(x2))
    case BinaryOp("-", x1, x2) => (evaluate(x1) - evaluate(x2))
    case BinaryOp("*", x1, x2) => (evaluate(x1) * evaluate(x2))
    case BinaryOp("/", x1, x2) => (evaluate(x1) / evaluate(x2))
  }

  /*
   * Lex's version:
  */
  def simplify(e: Expr): Expr = {
    // first simplify the subexpressions
    val simpSubs = e match {
      // Ask each side to simplify
      case BinaryOp(op, left, right) => BinaryOp(op, simplify(left), simplify(right))
      // Ask the operand to simplify
      case UnaryOp(op, operand) => UnaryOp(op, simplify(operand))
      // Anything else doesn't have complexity (no operands to simplify)
      case _ => e
    }

    // now simplify at the top, assuming the components are already simplified
    def simplifyTop(x: Expr) = x match {
      // Double negation returns the original value
      case UnaryOp("-", UnaryOp("-", x)) => x

      // Positive returns the original value
      case UnaryOp("+", x) => x

      // Multiplying x by 1 returns the original value
      case BinaryOp("*", x, Number(1)) => x

      // Multiplying 1 by x returns the original value
      case BinaryOp("*", Number(1), x) => x

      // Multiplying x by 0 returns zero
      case BinaryOp("*", x, Number(0)) => Number(0)

      // Multiplying 0 by x returns zero
      case BinaryOp("*", Number(0), x) => Number(0)

      // Dividing x by 1 returns the original value
      case BinaryOp("/", x, Number(1)) => x

      // Dividing x by x returns 1
      case BinaryOp("/", x1, x2) if x1 == x2 => Number(1)

      // Adding x to 0 returns the original value
      case BinaryOp("+", x, Number(0)) => x

      // Adding 0 to x returns the original value
      case BinaryOp("+", Number(0), x) => x

      // Anything else cannot (yet) be simplified
      case e => e
    }
    simplifyTop(simpSubs)
  }

  def main(args: Array[String]) {
    // (3 + 4) * (5 - 2) / (4 - 1)
    val expr = BinaryOp("/", BinaryOp("*", BinaryOp("+", Number(3), Number(4)), BinaryOp("-", Number(5), Number(2))), BinaryOp("-", Number(4), Number(1)))
    println(evaluate(expr))

    // val e2 = parse("(3 + 4) * (5 - 2) / (4 - 1) + (-5)")
    val e2 = parse("(3 + 4) * 5")
    println(e2)
    println(evaluate(e2))
  }
}
