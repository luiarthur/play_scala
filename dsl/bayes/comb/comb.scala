import scala.util.parsing.combinator._

//class JSON extends JavaTokenParsers {   
//
//  def value : Parser[Any] = obj | arr | 
//                            stringLiteral | 
//                            floatingPointNumber | 
//                            "null" | "true" | "false"
//
//  def obj   : Parser[Any] = "{"~repsep(member, ",")~"}"
//
//  def arr   : Parser[Any] = "["~repsep(value, ",")~"]"
//
//  def member: Parser[Any] = stringLiteral~":"~value
//}

class JSON extends JavaTokenParsers {   
  //https://booksites.artima.com/programming_in_scala_2ed/examples/html/ch33.html
  def obj: Parser[Map[String, Any]] = 
    "{"~> repsep(member, ",") <~"}" ^^ (Map() ++ _)

  def arr: Parser[List[Any]] =
    "["~> repsep(value, ",") <~"]" 

  def member: Parser[(String, Any)] = 
    stringLiteral~":"~value ^^ 
      { case name~":"~value => (name, value) }

  def value: Parser[Any] = (
    obj | arr | stringLiteral | 
    floatingPointNumber ^^ (_.toDouble) | 
    "null"  ^^ (x => null) | 
    "true"  ^^ (x => true) |
    "false" ^^ (x => false)
  )
}

object ParseJSON extends JSON {
  import java.io.FileReader
  def doit(f: String) = {
    val reader = new FileReader(f)
    parseAll(value, reader)
  }
}

val x = ParseJSON.doit("address.json")

