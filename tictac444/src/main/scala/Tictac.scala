package tictac

/** Playing tic-tac-toe like a pro.
 *
 *
 */

object Tictac {
  /*
    H|_|_|_
    _|H|_|_
    _|_|H|_
     | | |H
  */

  /** 
   * @constructor This creates a board
   */
  class Board(val comp: Set[Int], val human: Set[Int],
              val n: Int) { // 3D Board

    val allCells = (1 to n*n*n).toSet
    val emptyCells = allCells diff comp union human

    def mark(player: Char, pos: Int): Board = {
      if (player == 'C') new Board(comp + pos, human, n) else
      new Board(comp, human+pos, n)
    }

    // Can I do this using tail recursion?
    override def toString(): String = {
      var out = "" // Can I do this using val?
      for (k <- 1 to n) {
        for (j <- 1 to n) {
          for (i <- 1 to n) {
            val ind = i+n*(j-1)+n*n*(k-1)
            val p = if (comp contains ind) "C" else 
                    if (human contains ind) "H" else "0"
            out += p
          }
          out += "\n"
        }
        out += "\n"
      }
      out
    }

    private val c = (0 to n*n-1).toList
    private val a = (0 to n-1).toList
    private val d = (0 to n-1).map(x=>(0 to 3).map(y=>y+x*n*n)).flatten.toList
    /** Can I use recursion here? */
    private val winSets:List[Set[Int]] = 
      ((1 to n*n by n+1).zip(0 to n-1)).map(x =>x._1+x._2*n*n).toSet ::
      ((1 to n*n by n+1).zip(n-1 to 0 by -1)).map(x=>x._1+x._2*n*n).toSet ::
      (n to n*n-1 by n-1).zip(0 to n-1).map(x => x._1+x._2*n*n).toSet ::
      (n to n*n-1 by n-1).zip(n-1 to 0 by -1).map(x=>x._1+x._2*n*n).toSet ::
      c.map(x => (1 to n).toList.map(y => y + n*x).toSet) :::
      d.map(x => (1 to n*n by n).toList.map(y => y + x).toSet) :::
      c.map(x => (1 to n*n*n by n*n).toList.map(y => y + n*x).toSet) ::: 
      a.map(x => (1 to n*n by n+1).toList.map(y => y + n*n*x).toSet) ::: 
      a.map(x => (n to n*n-1 by n-1).toList.map(y => y + n*n*x).toSet)


    def win(player: Char): Boolean = {
      val p = if (player == 'C') comp else human
      winSets.exists(w => w subsetOf p)
    }
    def lose(player: Char): Boolean = !win(player)

    def probWin(player: Char, pos: Int, N: Int): Double = {
      // Edit this function
      // prob of winning is computed by simulating N games that
      // continue the game board and dividing the number of wins by N. 
      // Each of the N simulations is a random game.
      ???
    }

    def playBoard(player: Char) {
      ???
    }

    def show() { println(this) }
  }

  object Board {
    ???
  }
}
