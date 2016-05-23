package tictac

object Main extends App {
  import Tictac._

  println("Enter Board Size: ")
  val n = readInt()
  println("Enter Think Time (positive integer): ")
  val N = readInt()
  val B = new Board(Set[Int](), Set[Int](), n)
  B.playBoard('H',N)
}
