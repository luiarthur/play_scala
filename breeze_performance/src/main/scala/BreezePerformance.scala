package breezeperf

object BreezePerf {
  import breeze.stats.distributions._
  import breeze.linalg._
  import breeze.numerics._
  
  class Woohoo {
    def goCrazy(n: Int, B: Int): List[DenseVector[Double]] = {
      val m = DenseVector.zeros[Double](n)
      val S = DenseMatrix.eye[Double](n)
      val y = new MultivariateGaussian(m,S).sample(B)
      y.toList
    }
  }

}
