// immutable updatable tree
// test 1
object BinaryTrees {

  object Node {
    def apply[T <% Ordered[T]](value: T): Node[T] = Node(value, null, null)
  }

  case class Node[T <% Ordered[T]](value: T, left: Node[T], right: Node[T]) {
    //def this(value: T) = this(value,null,null)
    def isLeaf = (left,right) match { case (null,null) => true; case _ => false }

    def updateLeaf(x: T, newX: T): Node[T] = {
      if (isLeaf && x == value) Node(newX) else {
        if (x < value) this.copy(left = left.updateLeaf(x, newX)) else this.copy(right = right.updateLeaf(x, newX))
      }
    }

    override def toString = if (isLeaf) value.toString else "N(" +  value + ", " + left + ", " + right + ")" 
  }
}

import BinaryTrees._
val t = Node(1)
t.isLeaf

val t2 = Node(1, Node(2), Node(3, Node(4), Node(5) ))

t2.updateLeaf(5,100)




// test 2
sealed abstract class Tree[+T] { def addValue[U >: T <% Ordered[U]](x: U): Tree[U] }

case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
  def addValue[U >: T <% Ordered[U]](x: U) =
    if (x < value) Node(value, left.addValue(x), right)
    else Node(value, left, right.addValue(x))
}
object Node { def apply[T](value: T): Node[T] = Node(value, End, End) }
case object End extends Tree[Nothing] { def addValue[U <% Ordered[U]](x: U) = Node(x) }


// test 3

object Tree { def apply[T](elem: T): Tree[T] = Tree(elem, null, null) }
case class Tree[T](elem: T, left: Tree[T], right: Tree[T]) {
  def isLeaf = (left,right) match {case (null,null) => true; case _ => false}
}

object TI { 
  def apply(e: Int): TI = new TI(e, null, null) 
  def apply(e: Int, l: TI, r: TI): TI = new TI(e, l, r) 
}
class TI(elem: Int, left: TI, right: TI) extends Tree[Int](elem,left,right)

val t = TI(1,TI(2),TI(3))
t.copy(left=TI(100))
