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

  class MotherBoard(val n: Int) {
    val allCells = (1 to n*n*n).toSet
    private val dir1 = Set( Set(1,n,n-1,n+1), Set(-1,+1) )
    private def combine(acc:Set[Int], set:Set[Int]) = for (a <- acc; s <- set) yield s*a
    private def combine2(acc:Set[Int], set:Set[Int]) = for (a <- acc; s <- set) yield s+a
    private val dir2 = dir1.reduceLeft(combine)
    private val dirs = Set( dir2, Set(+n*n,-n*n,0) ).reduceLeft(combine2) union Set(+n*n, -n*n) 

    private val np1 = n+1
    private val nm1 = n-1
    private def oob(i: Int): Boolean = i < 1 || i > n*n*n // out of bounds
    private def oos(f: Int, t: Int, d: Int): Boolean = { // not contiguous
      val x = math.abs(d) 
      if (x < n*n) {
        val sameplane = (f-1) / (n*n) == (t-1) / (n*n)
        if ( !sameplane ) true else {
          val good = x match {
            case 1 => (f-1)/n == (t-1)/n // same row
            case `np1` => (f-1)/n < (t-1)/n 
            case `nm1` => (f-1)/n < (t-1)/n
            case `n` => (f-1) % n == (t-1) % n //
            case _ => true
          }
          !good || !sameplane 
        }
      } else {
        false
      }
    }

    private def anyoos(S: Set[Int], d: Int): Boolean = oos(S.min,S.max,d)
    private def allContig(S: Set[Int], d: Int): Boolean = !anyoos(S,d)

    private def build(from: Int, dir: Int): Set[Int] = {
      def loop(f: Int, S: Set[Int]): Set[Int] = {
        if ( S.size == n && !oob(f) && allContig(S,dir) ) S else {
          val dest = f + dir
          val newS = S + dest
          if (oob(dest) || newS.size > n) S.empty else loop(dest,newS)
        }
      }
      loop(from,Set(from))
    }
    private val winSets: List[Set[Int]] = {
      val ws = for(i <- (1 to n*n*n); j <- dirs) yield build(i,j)
      ws.toSet.filterNot(x => x == Set.empty).toList
    }
        

    /** 
     * @constructor This creates a board
     */
    class Board(val comp: Set[Int], val human: Set[Int]) { // 3D Board

      val emptyCells = allCells diff (comp union human)

      def mark(player: Char, pos: Int): Board = {
        if (player == 'C') new Board(comp + pos, human) else
        new Board(comp, human+pos)
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
      def probWin(player: Char, pos: Int, N: Int = 100): Double = {
        val sumWin = (1 to N).toList.map(x => this.mark(player,pos).
            randomGame(opp(player)).winGame(player)).sum
        val sumOWin = (1 to N).toList.map(x => this.mark(player,pos).
            randomGame(opp(player)).winGame(opp(player))).sum
        sumWin.toDouble / sumOWin.toDouble
      }
      def smartMove(player: Char, N: Int = 100): Int = {
        val cells = this.emptyCells.toList.par
        val probs = cells.map(x => this.probWin(player,x,N)) zip cells
        probs.maxBy(_._1)._2
      }

      def playBoard(player: Char, N: Int = 100): Board = {
        def readMove(): Int = {
          println("Enter your move as an Integer")
          val x = readInt()
          if ( !(emptyCells contains x) ) readMove() else x
        }
        if (this.inProg) {
          println("Current Board:")
          this.show
          if (player=='H') {
            val move = readMove()//readInt()
            this.mark('H',move).playBoard('C',N)
          } else {
            println("Your opponent is thinking...")
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
}
