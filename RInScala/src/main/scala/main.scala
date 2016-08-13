object RscalaTest extends App {
  import breeze.stats.distributions._
  import breeze.linalg._
  import scala.io.Source
  val R = org.ddahl.rscala.RClient() // initialise an R interpreter
  R eval ".libPaths(c(.libPaths(), '~/lib/R_lib'))"
 
  // first simulate some data consistent with a Poisson regression model
  val x = Uniform(50,60).sample(1000)
  val eta = x map { xi => (xi * 0.1) - 3 }
  val mu = eta map { math.exp(_) }
  val y = mu map { Poisson(_).draw }
   
  // call to R to fit the Poission regression model
  R.x = x.toArray // send x to R
  R.y = y.toArray // send y to R
  R eval "mod <- glm(y~x,family=poisson())"  // fit the model in R
  // pull the fitted coefficents back into scala
  //val beta = DenseVector[Double](R.toVector[Double]("mod$coefficients"))
  R eval "modCoef <- as.numeric(mod$coefficients)"
  val modCoef = R.getD1("modCoef")
  val beta = new DenseVector(modCoef)

  R.eval("library(maps)") // fit the model in R
  R.eval("map('state')") // fit the model in R
  R.eval("my_p <- function(x) plot(x,fg='grey',bty='n')") // fit the model in R
  //R.eval("my_p(rnorm(100))") // fit the model in R
  
  //val rcmd = Source.fromFile("src/main/resources/myplot.R").getLines.toList.toString
  //println(rcmd)
  R.eval("source('src/main/resources/myplot.R')")
  //R.eval("myplot(1000,2,4)")
  val res = myfunc.f(3.0)
  println(res)
 
  // print the fitted coefficents
  println(beta)
}
