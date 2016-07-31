object BinaryTrees {
  sealed abstract class Tree[+T]
  case object End extends Tree[Nothing]
  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]
  object Node {
    def apply[T](value: T): Node[T] = Node(value, End, End)
  }
}
