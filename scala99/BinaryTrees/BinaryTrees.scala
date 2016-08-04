// immutable updatable tree
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
