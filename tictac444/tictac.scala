object tictac {

  class Cube(val at: Array[Array[Array[Int]]]) {
    def mark (marker: Int, pos: (Int,Int,Int)): Cube = {
      at(pos._1)(pos._2)(pos._3) = marker
      new Cube(at)
    }

    def show() {
      for (m <- at) {
        for (v <- m) {
          v.foreach(print)
          println()
        }
        println()
      }
    }

    def win(marker: Int): Boolean = {
      // Edit this function
      true
    }
    def lose(marker: Int): Boolean = !win(marker)

    def probWin(marker: Int, pos: (Int,Int,Int), N: Int): Double = {
      // Edit this function
      // prob of winning is computed by simulating N games that
      // continue the game board and dividing the number of wins by N. 
      // Each of the N simulations is a random game.
      0.0
    }

    def playBoard(marker: Int) {
    }
  }

  object Cube {
    def init(n: Int): Cube = 
      new Cube( Array.fill(n)(Array.fill(n)(Array.fill(n)(0))) )
  }

  def main(args: Array[String]) {
    val M = Cube.init(2)
    M.show
    M.mark(1,(1,1,1)).show
    M.show
  }

}
