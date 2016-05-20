package tictac
import org.scalatest.FunSuite

class FunTicTacSuite extends FunSuite  {
    import Tictac._

    test("Print a 2 x 2 x 2 matrix") {
      println(new Board(Set[Int](), Set[Int](), 2))
    }

    test("Check 3D diag win in 3 x 3 x 3") {
      val B3 = new Board(Set[Int](), Set[Int](), 3)
      val A3 = B3.mark('C',1).mark('C',14).mark('C',27)
      A3.show
      assert(A3.win('C'))
    }

    test("Check 3D diag win in 4 x 4 x 4") {
      val B4 = new Board(Set[Int](), Set[Int](), 4)
      val A4 = B4.mark('C',1).mark('C',22).mark('C',43).mark('C',64)
      assert(A4.win('C'))
    }

    test("Check all cells for 4 x 4 x 4") {
      val B4 = new Board(Set[Int](), Set[Int](), 4)
      assert(B4.allCells == (1 to 64).toSet)
    }

    test("Check empty cells for 4 x 4 x 4") {
      val B4 = new Board(Set[Int](), Set[Int](), 4)
      assert(B4.emptyCells == (1 to 64).toSet)
    }

}
