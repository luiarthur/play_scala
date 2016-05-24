package tictac
import org.scalatest.FunSuite

class FunTicTacSuite extends FunSuite  {
    import Tictac._

    val B2 = new MotherBoard(2)
    val B3 = new MotherBoard(3)
    val B4 = new MotherBoard(4)

    test("Print a 2 x 2 x 2 matrix") {
      println(new B2.Board(Set[Int](), Set[Int]()))
    }

    test("Check 3D diag win in 3 x 3 x 3") {
      val b3 = new B3.Board(Set[Int](), Set[Int]())
      val a3 = b3.mark('C',1).mark('C',14).mark('C',27)
      a3.show
      assert(a3.win('C'))
    }

    test("Check 3D diag win in 4 x 4 x 4") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      val a4 = b4.mark('C',1).mark('C',22).mark('C',43).mark('C',64)
      assert(a4.win('C') && !a4.inProg)
    }

    test("Check 3D diag draw in 4 x 4 x 4") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      val a4 = b4.mark('C',1).mark('C',22).mark('C',43).mark('C',64)
      assert(!a4.draw())
    }

    test("Check 3D diag lose in 4 x 4 x 4") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      val a4 = b4.mark('C',1).mark('C',22).mark('C',43).mark('C',64)
      assert(a4.lose('P'))
    }

    test("Check 3D diag win not in Progress in 4 x 4 x 4") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      val a4 = b4.mark('C',1).mark('C',22).mark('C',43).mark('C',64)
      b4.show
      assert(!a4.inProg)
    }

    test("Check 3D game in Progress in 4 x 4 x 4") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      val a4 = b4.mark('C',2).mark('C',22).mark('C',43).mark('C',64)
      assert(a4.inProg)
    }

    test("Check all cells for new  4 x 4 x 4") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      assert(B4.allCells == (1 to 64).toSet)
    }

    test("Check empty cells for 4 x 4 x 4") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      assert(b4.mark('C',4).mark('H',3).emptyCells == (1 to 64).filter(x => x != 4 && x != 3).toSet)
    }

    test("Play Random 3 Board") {
      val n = 3
      val b = new B4.Board(Set[Int](), Set[Int]())
      val brand = b.randomGame('H')
      println("The winner is: " + brand.winner() )
      brand.show
      assert(true)
    }

    test("Play Random 4 Board") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      val brand = b4.mark('C',1).randomGame('H')
      println("The winner is: " + brand.winner() )
      brand.show
      assert(true)
    }

    test("Calculate Prob of End Game 1") {
      val b = new B4.Board(Set[Int](), Set[Int]())
      val c = b.mark('C',1).mark('H',2).mark('C',4).mark('H',5)
      println("P(C wins | C moves to 7) = " + c.probWin('C',7))
      c.show
      assert(true)
    }

    test("Play a smart move") {
      val b4 = new B4.Board(Set[Int](), Set[Int]())
      val brand = b4.mark('C',1).mark('H',2).mark('C',4).mark('H',5)
      println("Computer should move to: " + brand.smartMove('C'))
      brand.show
      assert(true)
    }

    test("Correct Win Sets") {
      assert( B4.winSets.size == 76 )
    }

    test("Print Winsets") {
      B4.winSets.foreach(println)
      assert(true)
    }
}
