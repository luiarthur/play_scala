trait BayesDsl  {
  abstract sealed class ValidTypes
  type T <: ValidTypes
  case class Int() extends ValidTypes
  case class Double() extends ValidTypes
  case class Vec[T](n:Symbol) extends ValidTypes 
  case class Mat[T](n:Symbol) extends ValidTypes
  case class Arr[T](n:Symbol) extends ValidTypes

  val Data: Map[Symbol, ValidTypes]
}



object myMCMC extends BayesDsl {
  val Data = Map('y -> Vec[Double]('N), 
                 'X -> Mat[Double]('NxM),
                 'Z -> Arr[Mat[Int]]('I__JxN_i))

  Data.foreach{ i => println(i._2) }
}

myMCMC
