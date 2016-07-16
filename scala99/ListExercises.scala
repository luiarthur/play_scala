val x = List(1,1,2,3,5,8)

//01: last in list
x.last

//02: penultimate of list
x.init.last

//03: K-th in list
x(3)

//04: num of elem in list
x.size

//05: reverse a list
x.reverse

//06: check if palindrome
val palindrome = List(1,2,3,4,3,2,1)
x.reverse == x
palindrome.reverse == palindrome

//07: flatten a list
val bumpyList = List(1,List(1,2,3),List(List(4,3),List(2,5)))
bumpyList flatMap {
  case ms: List[_] => flat(ms)
  case e => List(e)
}

//08: Eliminate consequtive dups
val redundant = List(3,3,3,1,2,2,2,2,2,4,4,4)
???
