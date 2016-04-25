object GibbsSc {
 
    import breeze.stats.distributions._
    import breeze.numerics.sqrt
 
    def main(args: Array[String]) {
        val N=50000
        val thin=1000
        var x=0.0
        var y=0.0
        val rngN = Gaussian(0,1)
        val rngG = Gamma
        println("Iter x y")
        for (i <- 0 until N) {
            for (j <- 0 until thin) {
                x = rngG(3.0,y*y+4).sample
                //y = rngN(1.0/(x+1),1.0/sqrt(2*x+2)).sample
                y = rngN.sample * (1.0/sqrt(2*x+2)) + 1.0/(x+1)
            }
            println(i+" "+x+" "+y)
        }
    }
}
