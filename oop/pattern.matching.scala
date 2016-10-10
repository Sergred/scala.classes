def third[A] (l: List[A]): A = l.tail.tail.head

third(List(1,2,3,4)) // = 3
third(List("one","two","three","four")) // = 3

def map[A, B](l: List[A], f: A => B): List[B] = l match {
  case Nil => Nil
  case h :: t => f(h) :: map(t, f)
}

class Stack[T] {
  var elems: List[T] = Nil
  def push(x: T) { elems = x :: elems }
  def top: T = elems.head
  def pop() { elems = elems.tail }
}

val stack = new Stack[Int]

stack.push(1)
stack.push('a')
println(stack.top) // = 97
stack.pop()
println(stack.top) // = 1

// _:A => ...
// _:B => ...
// _:C => ...
