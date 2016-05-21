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

  test("Parallel Huge Matrix Multiplications") {
    val X = DenseMatrix.rand(100,400)
    (1 to 500).toList.par.foreach(x =>  inv(X.t * X) )
    assert(true)
  }
  */

  test("Sequential Huge Matrix Multiplications") {
    val X = DenseMatrix.rand(500,500)
    (1 to 1000).toList.foreach(x =>  X.t * X )
    assert(true)
  }

}
