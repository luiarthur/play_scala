object tictac {
  /*
    H|_|_|_
    _|H|_|_
    _|_|H|_
     | | |H
  */

  class Board(val comp: Set[Int], val human: Set[Int],
              val n: Int) { // 3D Board

    def mark(player: Char, pos: Int): Board = {
      if (player == 'C') new Board(comp + pos, human, n) else
      new Board(comp, human+pos, n)
    }

    def show() {
      println()
      for (k <- 1 to n) {
        for (j <- 1 to n) {
          for (i <- 1 to n) {
            val ind = i+n*(j-1)+n*n*(k-1)
            val p = if (comp contains ind) "C" else 
                    if (human contains ind) "H" else "0"
            print(p)
          }
          println()
        }
        println()
      }
    }

    val c = (0 to n*n-1).toList
    val d = (0 to n-1).map(x=>(0 to 3).map(y=>y+x*n*n)).flatten.toList
    val a = (0 to n-1).toList
    val winSets:List[Set[Int]] = 
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
  }

  object Board {
    ???
  }

  def main(args: Array[String]) {
    val B3 = new Board(Set[Int](), Set[Int](), 3)
    val A3 = B3.mark('C',1).mark('C',14).mark('C',27)
    A3.show
    println(A3.win('C'))

    val B4 = new Board(Set[Int](), Set[Int](), 4)
    val A4 = B4.mark('C',1).mark('C',22).mark('C',43).mark('C',64)
    A4.show
    println(A4.win('C'))
  }
}
