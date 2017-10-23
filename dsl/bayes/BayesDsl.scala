import scala.language.implicitConversions

abstract class BayesDsl {
  sealed abstract class Type
  object Int extends Type { override def toString = "Int"}
  object Double extends Type { override def toString = "Double"}
  case class Arr(t:Type, size:Symbol) extends Type
  case class Vec(t:Type, size:Symbol) extends Type
  case class Mat(t:Type, row:Symbol, col:Symbol) extends Type

  type T = Type

  val Data: Map[Symbol, T]
  //val Likdlihood: ???
  //val Priors: ???
  //val Init: ???
  //val Proposal: ???
  //val Other: ???

  def generateCode() = {
    ???
  }

  implicit def distributed(sym:Symbol) = {
    println("Hi")
  }
}


object myMCMC extends BayesDsl {
  val Data = Map('y -> Vec(Int, 'N), 
                 'X -> Mat(Double, 'N, 'K),
                 'Z -> Arr(Mat(Int, 'J, 'N_i), 'I))

  //'y.distributed
  //distributed('y)

  def main(args:Array[String]=Array()) = {
    Data.foreach{ i => println(i._2) }
  }
}

myMCMC.main()
