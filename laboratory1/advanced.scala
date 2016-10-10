// exercise 1
lazy val fibonacci: Int => Int = n => n match {
  case 0 => 0
  case 1 => 1
  case _ => fibonacci(n-1) + fibonacci(n-2)
}

println("")
fibonacci(4)
println("fibonacci(7)")
fibonacci(7)
println("fibonacci(12)")
fibonacci(12)

// exercise 2
lazy val reduce: (List[Int], (Int, Int) => Int) => Int = (ns, combine) => ns match {
  case Nil => throw new Exception("Empty list!")
  case hd::Nil => hd
  case hd::tl => combine(hd, reduce(tl, combine))
}

println("reduce(List(1, 8, 4, 3, 9, 5), (a, b) => a + b)")
reduce(List(1, 8, 4, 3, 9, 5), (a, b) => a + b)
println("reduce(List(23, 76, 34, 84, 24, 58), (a, b) => if (a > b) a else b)")
reduce(List(23, 76, 34, 84, 24, 58), (a, b) => if (a > b) a else b)
reduce(List(1, 2, 3, 4, 5), (a, b) => a*b)

// exercise 3
lazy val pascal: (Int, Int) => Int = (r, c) => {
  val nextrow: List[Int] => List[Int] = l => {
    lazy val go: List[Int] => List[Int] = list => list match {
      case Nil => List(1)
      case hd::tl => if (tl.isEmpty) go(tl) else List(hd + tl.head):::go(tl)
    }
    1::go(l)
  }
  lazy val row: (List[Int], Int) => List[Int] = (list, count) => count match {
    case 1 => list
    case _ => row(nextrow(list), count - 1)
  }
  lazy val res: (List[Int], Int) => Int = (list, count) => count match {
    case 1 => list.head
    case _ => res(list.tail, count - 1)
  }
  res(row(List(1), r), c)
}

println("pascal(1, 1)")
pascal(1, 1)
println("pascal(3, 2)")
pascal(3, 2)
println("pascal(6, 4)")
pascal(6, 4)
println("pascal(5, 5)")
pascal(5, 5)

val pascal: (Int, Int) => Int = (r, c) => {
  if (r < 3 || c == 1 || r == c) 1 else pascal(r - 1, c - 1) + pascal(r - 1, c)
}

println("pascal(1, 1)")
pascal(1, 1)
println("pascal(3, 2)")
pascal(3, 2)
println("pascal(6, 4)")
pascal(6, 4)
println("pascal(5, 5)")
pascal(5, 5)

// exercise 4
lazy val balanced: List[Char] => Boolean = chars => {
  lazy val go: (List[Char], Int) => Boolean = (list, count) => if (count < 0) false else list match {
    case Nil => if (count == 0) true else false
    case hd::tl => hd match {
      case '(' => go(tl, count + 1)
      case ')' => go(tl, count - 1)
      case _ => go(tl, count)
    }
  }
  go(chars, 0)
}

println("balanced('hello (world)'.toList)")
balanced("hello (world)".toList)
println("balanced('(()'.toList)")
balanced("(()".toList)
println("balanced('()(())'.toList)")
balanced("()(())".toList)
println("balanced('a)(b(c)()'.toList)")
balanced("(a)(b(c)()".toList)
balanced(")(".toList)
