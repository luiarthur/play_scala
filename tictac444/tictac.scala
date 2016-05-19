object tictac {

  class Vec(val v: Array[Int]) {
    override def toString(): String = v.mkString
  }

  class Mat(val m: Array[Vec]) {
    override def toString(): String = m.mkString("\n")
  }

  class Cube(val c: Array[Mat]) {
    override def toString(): String = c.mkString("\n\n")+"\n"
    //def mark (marker: Int, pos: (Int,Int,Int)): Cube = {
    //}
  }

  object Cube {
    def init(n: Int): Cube = {
      val v = Array.fill(n)(0)
      val V = new Vec(v)
      val M = new Mat(Array.fill(n)(V))
      val C = new Cube(Array.fill(n)(M))
      C
    }
  }

  def main(args: Array[String]) {
    val C = Cube.init(3)
    val M = C.c(0)
    val V = M.m(0)
    println(V)
    println("\nC:\n"+C)
  }

}
