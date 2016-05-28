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

  /*
    def test() { val Xs = (1 to 1000).map( x => DenseMatrix.rand(200,200) }
    def test_par() { val Xs = (1 to 1000).par.map( x => DenseMatrix.rand(200,200) }

   * test_par is much faster here. The rand isn't parallel. So we're safe.
   *
   * For this case:
   *   val Xs = (1 to 500).map( x => DenseMatrix.rand(200,200) )
   *   Xs.toList.foreach( x => inv(x.t * x) )
   * I think the key is if the matrix is big, we are reaping the benefits
   * of multiple cores despite the List is non-parallel. On the other hand,
   * for small matrices, we get slower. But then, the matrices are smaller,
   * so they invert quickly anyway. Still, it would be nice if there was a way
   * to do openblas_set_num_threads. Also, this depends on your system's openblas.
   * If none is found, breeze just uses the netlib-java provided ones.
   */

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
