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

//08: Eliminate consecutive dups
val redundant = List(3,3,3,1,2,2,2,2,2,4,4,4,3)
def compress[T](ls: List[T]): List[T] = ls match {
  case h :: tail => h :: compress(tail).dropWhile( _ == h )
  case Nil => Nil
}
compress(redundant)

//09: pack consecutive dups into sublists
def pack[T](ls: List[T]): List[List[T]] = {
  if (ls.isEmpty) List(ls) else {
    val (packed, next) = ls span { _ == ls.head }
    if (next.isEmpty) List(packed) else packed :: pack(next)
  }
}
pack(redundant)

//10: Run-length encoding of list
def encode[T](ls: List[T]): List[(Int,T)] = pack(ls) map { x => (x.size, x.head) }
encode(redundant)
encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) == List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
