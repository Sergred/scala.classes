val thereis: ((Int, List[Int]) => Boolean) = (a, l) => l match {
  case Nil => false
  case hd::tl => if (hd == a) true else thereis(a, tl)
}

thereis(1, List(3, 2, 1))
thereis(4, List(3, 2, 1))

val thereisP: ((List[Int], Int => Boolean) => Boolean) = (l, p) => l match {
  case Nil => false
  case hd::tl => if (p(hd)) true else thereisP(tl, p)
}

thereisP(List(3, 2, 1), (a) => a % 3 == 0)
thereisP(List(4, 3, 2, 1), (a) => a % 2 == 0)
