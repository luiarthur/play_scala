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


  test("Multivariate Normal Working") {
    val W = new Woohoo
    val n = 300
    val B = 200000
    val y = W.goCrazy(n,B)
    val z = round(y.map( x => x / B.toDouble ).reduce(_+_))

    assert( z == DenseVector.zeros[Long](n) )
  }

}
