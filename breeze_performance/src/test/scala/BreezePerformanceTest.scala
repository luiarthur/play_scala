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
    val n = 200
    val B = 20000
    val y = W.goCrazy(n,B)
    val z = round(y.map( x => x / B.toDouble ).reduce(_+_))

    assert( z == DenseVector.zeros[Long](n) )
  }
  */

  test("Huge Matrix Multiplications") {
    val X = DenseMatrix.rand(100,500)
    (1 to 5000).toList.par.foreach(x =>  X.t * X )
    assert(true)
  }

}
