object boards {

  class Boards(val N: Int) {
    lazy val winSets = {
      println("I'm in.")
      Set(1 to N)
    }

    class Board {
      val ws = winSets
    }
  }
    

  def main(args: Array[String]) {
    val B4 = new Boards(4)
    println(B4.winSets)
    val b4 = new B4.Board
    val a4 = new B4.Board
    val c4 = new B4.Board
    println(b4.ws)
    println(a4.ws)
    println(c4.ws)
  }
}
