import org.scalatest.FunSuite
class TestSuite extends FunSuite {
  import CorrectEmails.Fun.correctEmails

  def makeTest(s: String, ans: Int) = test(s) {
    assert(correctEmails(s) == ans)
  }

  makeTest("abcd@?bcd.ca", 5)
  makeTest("a??@???.af", 0)
  makeTest("??????????", 11562500) // 1 minute & wrong ans
  makeTest("a?c@b?c", 6)
  makeTest("a????.?", 2125)
  makeTest("a?c@b?c.?", 180)
}
