// exercise 1
lazy val sum: (Int, Int) => Int = (f, t) => f match {
  case `t` => t
  case _ => f + sum(f + 1, t)
}

println("sum(1, 3)")
sum(1, 3)
println("sum(5, 10)")
sum(5, 10)

// exercise 2
lazy val fact: Int => Int = n => n match {
  case 0 => 1
  case _ => n*fact(n - 1)
}

println("fact(3)")
fact(3)
println("fact(5)")
fact(5)
println("fact(7)")
fact(7)

// exercise 3
lazy val square: List[Int] => List[Int] = l => l match {
  case Nil => Nil
  case hd::tl => (hd*hd)::square(tl)
}

println("square(List(1, 2, 3, 4, 5))")
square(List(1, 2, 3, 4, 5))
println("square(12, 56, 32)")
square(List(12, 56, 32))

// exercise 4
lazy val map: (List[Int], Int => Int) => List[Int] = (ns, f) => ns match {
  case Nil => Nil
  case hd::tl => f(hd)::map(tl, f)
}

println("map(List(1, 2, 3, 4, 5), (n) => n*n)")
map(List(1, 2, 3, 4, 5), (n) => n*n)
println("map(List(1, 2, 3, 4, 5), (n) => n*n*n)")
map(List(1, 2, 3, 4, 5), (n) => n*n*n)

// exercise 5
lazy val filter: (List[Int], Int => Boolean) => List[Int] = (ns, include) => ns match {
  case Nil => Nil
  case hd::tl => if (include(hd)) hd::filter(tl, include) else filter(tl, include)
}

println("filter(List(1, 2, 3, 4, 5), (n) => if (n % 2 == 0) true else false)")
filter(List(1, 2, 3, 4, 5), (n) => if (n % 2 == 0) true else false)
println("")
filter(List(9, 8, 7, 6, 5, 4), (n) => if (n % 3 == 0) true else false)
println("")
filter(List(60, 23, 11, 76, 42, 9), (n) => if (n > 20 && n < 65) true else false)
