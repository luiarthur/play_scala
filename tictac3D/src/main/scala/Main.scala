package tictac

object Main extends App {
  import Tictac._

  println("Enter Board Size: ")
  val n = readLine().toInt

  println("Enter Difficulty (1-3): ")
  val N = math.pow(10,readLine().toDouble).toInt
  
  println("Do you want to place the first move? (y/n)")
  val humanFirst = if (readLine()=="n") false else true

  val Bn = new MotherBoard(n)
  val B = new Bn.Board(Set[Int](), Set[Int]())

  if (humanFirst) B.playBoard('H',N) else B.playBoard('C',N)
}
