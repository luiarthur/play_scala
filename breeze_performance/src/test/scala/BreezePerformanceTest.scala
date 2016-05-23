package breezeperf
import org.scalatest.FunSuite

class BreezePerfSuite extends FunSuite {

  import BreezePerf._
  import breeze.stats.distributions._
  import breeze.linalg._
  import breeze.numerics._

  test("Hi") {
    assert(true)
  }

  /*
  test("Multivariate Normal Working") {
    val W = new Woohoo
    val n = 100
    val B = 200
    val y = W.goCrazy(n,B)
    val z = round(y.map( x => x / B.toDouble ).reduce(_+_))

    assert( z == DenseVector.zeros[Long](n) )
  }

  */
  test("Parallel Huge Matrix Multiplications") {
    val Xs = (1 to 500).map( x => DenseMatrix.rand(200,200) )
    Xs.toList.foreach( x => inv(x.t * x) )
    assert(true)
  }

  /**
     Compare this to the implementation in julia in ../jl/test.jl
     The time taken to perform this in Scala is 60s.
     The time taken to perform this is Julia is 50s.
     Yes, Julia is faster, but the memory management in Julia isn't 
     as efficient. At one point, Julia uses over 100% of RAM. 
     Scala stays at around 3.6% of total RAM. If you have plenty 
     of RAM, Julia is great. Still, something tells me julia is not 
     a good idea for big data, because it uses a lot of memory,
     and will slow down many other processes.
   */
  /*
  test("Sequential Huge Matrix Multiplications") {
    val X = DenseMatrix.rand(500,500)
    (1 to 10000).toList.foreach(x =>  X.t * X )
    assert(true)
  }
  */

}
