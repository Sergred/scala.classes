val combinations: (Int, List[Int]) => Int = (amount, coins) => amount match {
  case 0 => 1
  case n => if (n < 0) 0 else {
    coins.map(n => combinations(amount - n, coins.filter(j => j >= n))).foldLeft(0)((a, b) => a + b)
  }
}

combinations(1, List(1))
combinations(3, List(1, 2, 3))
combinations(4, List(1, 2))
combinations(5, List(1, 2))

val combinations: (Int, List[Int]) => Int = (amount, coins) => amount match {
  case 0 => 1
  case n => if (n < 0) 0 else coins match {
    case Nil => 0
    case hd::tl => combinations(amount - hd, coins) + combinations(amount - hd, tl)
  }
}

combinations(1, List(1))
combinations(3, List(1, 2, 3))
combinations(4, List(1, 2))
combinations(5, List(1, 2))
