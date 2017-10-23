import scala.language.implicitConversions

abstract class BayesDsl {
  sealed abstract class Type
  object Int extends Type { override def toString = "Int"}
  object Double extends Type { override def toString = "Double"}
  case class Arr(t:Type, size:Symbol) extends Type
  case class Vec(t:Type, size:Symbol) extends Type
  case class Mat(t:Type, row:Symbol, col:Symbol) extends Type

  sealed abstract class specialValues
  case class I(n:Any) extends specialValues

  sealed abstract class Distributions 
  case class Normal(mean:Any, sd:Any) extends Distributions
  case class MvNormal(m:Any, S:Any) extends Distributions
  case class Gamma(shape:Any, rate:Any) extends Distributions
  case class InvGamma(a:Any, b:Any) extends Distributions
  case class Uniform(a:Any, b:Any) extends Distributions


  val Data: Map[Symbol, Type]
  val Likelihood: Map[Symbol, Distributions]
  val Priors: Map[Symbol, Distributions]
  val Init: Map[Symbol, Any] = Map()
  val Proposal: Map[Symbol, Distributions] = Map()
  //val Other: ???

  def print = {
    val tab = "    "
    println("Data:")
    Data.foreach{ i => println(tab + i._1.name + ": " + i._2) }

    println("\nLikelihood:")
    Likelihood.foreach{ 
      i => println(tab + i._1.name + " ~ " + i._2) 
    }

    println("\nPrior:")
    Priors.foreach { 
      i => println(tab + i._1.name + " ~ " + i._2) 
    }
  }
}


object myMCMC extends BayesDsl {
  val Data = Map('y -> Vec(Int, 'N), 
                 'X -> Mat(Double, 'N, 'K),
                 'Z -> Arr(Mat(Int, 'J, 'N_i), 'I))

  val Likelihood = Map('y -> Normal('X__times__beta, I('N)))
  val Priors = Map('beta -> Normal(0, I('K)))
}

myMCMC.print
