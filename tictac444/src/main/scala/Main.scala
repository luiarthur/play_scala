package tictac

object Main extends App {
  import Tictac._

  println("Enter Board Size: ")
  val n = readInt()
  println("Enter Think Time (positive integer): ")
  val N = readInt()
  val B4 = new MotherBoard(n)
  val B = new B4.Board(Set[Int](), Set[Int]())
  B.playBoard('H',N)
}
