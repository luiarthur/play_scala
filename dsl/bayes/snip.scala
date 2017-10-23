import scala.language.implicitConversions
case class LineBuilder(num:Int) {
  object PRINT {
    def apply(str:String) = println(str)
  }
}
implicit def int2LineBuilder(i: Int) = LineBuilder(i)

10 PRINT "STR"

/*
object Bla
implicit def blabla(b: Bla.type) = println(10)
implicit def intblabla(i:Int) = println(10)
def PRINT(implicit v:String) = println(v)
*/
