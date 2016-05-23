package tictac

/** Playing tic-tac-toe like a pro.
 *
 *
 */

object Tictac {
  import scala.util.Random
  private val rand = new Random(System.currentTimeMillis());

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
    val emptyCells = allCells diff (comp union human)

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


    private def opp(player: Char): Char = if (player == 'C') 'H' else 'C'
    def win(player: Char): Boolean = {
      val p = if (player == 'C') comp else human
      winSets.exists(w => w subsetOf p)
    }
    def lose(player: Char): Boolean = win(opp(player))
    def draw(): Boolean = this.emptyCells == Set[Int]() && !win('C') && !win('P')
    def inProg(): Boolean = this.emptyCells.size > 0 && !win('C') && !win('P')
    def winner(): Char = {
      if (!this.draw) {
        if (this.win('C')) 'C' else 'H'
      } else 'D'
    }

    def randMove(player: Char): Board = {
      val A = this.emptyCells.toArray
      val cell = A( rand.nextInt(A.size) );
      this.mark(player, cell)
    }
    /** Plays a random game based on this current board starting with 
     *  `player`.
     */
    def randomGame(player: Char): Board = {
      if (this.inProg) {
        val A = this.emptyCells.toArray
        val cell = A( rand.nextInt(A.size) );
        randMove(player).randomGame(opp(player)) 
      } else this
    }

    def winGame(player: Char): Int = if (this.winner == player) 1 else 0

    /** Edit this function
     *  prob of winning is computed by simulating N games that
     *  continue the game board and dividing the number of wins by N. 
     *  Each of the N simulations is a random game.
     */
    def probWin(player: Char, pos: Int, N: Int = 1000): Double = {
      def sumWin(): Int = (1 to N).toList.map(x => this.mark(player,pos).
          randomGame(opp(player)).winGame(player)).sum
      sumWin() / N.toDouble
    }
    def smartMove(player: Char, N: Int = 100): Int = {
      val cells = this.emptyCells.toList.par
      val probs = cells.map(x => this.probWin(player,x,N)) zip cells
      probs.maxBy(_._1)._2
    }

    def playBoard(player: Char, N: Int = 1000): Board = {
      if (this.inProg) {
        if (player=='H') {
          println("Current Board:")
          this.show
          println("Enter your move as an Integer")
          val move = readInt()
          this.mark('H',move).playBoard('C',N)
        } else {
          val move = this.smartMove('C',N)
          this.mark('C',move).playBoard('H',N)
        }
      } else {
        println("###################################")
        show()
        if ( draw() ) println("It's a draw!") else 
          if ( winner() == 'H' ) println("You win!") else println("You lose.")
        println("End of Game")
        this
      }
    }

    def show() { print(this) }
  }

  object Board {
    ???
  }
}
