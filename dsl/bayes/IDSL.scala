import scala.util.parsing.combinator._

class Arith extends JavaTokenParsers {
  def expr: Parser[Any] = term~rep("+"~term | "-"~term)
  def term: Parser[Any] = factor~rep("*"~factor | "/"~factor)

  // floatingPointNumber is a primitive Parser
  def factor: Parser[Any] = floatingPointNumber | "("~expr~")"
}


object ParseExpr extends Arith {
  //def parse(s: String) = {
  //  parseAll(expr, s)
  //}
  def doit(s:String) = {
    parse(expr, s)
  }
}

val x = ParseExpr.doit("1 * 2 + 3 * (2+3) + 2")


