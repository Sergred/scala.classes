// exercise 1

val length: (List[Any] => Int) = list => list match {
  case Nil => 0
  case _::tl => 1 + length(tl)
}

length(List(1, 2, 3, 4))
length(List())

// exercise 2
// lazy val pow: (Int => (Int => Int)) = a => { b => List.fill(a)(b).foldLeft(1)((x, y) => x*y)}

val pow: (Double => (Double => Double)) = a => { b => math.pow(b, a) }

val sqr = pow(2);
sqr(4) // = 16

val cube = pow(3);
cube(3) // = 27

// exercise 3
lazy val iter: (((Int => Int), Int, Int) => Int) = (f, i, x) => i match {
  case 0 => x
  case _ => f(iter(f, i - 1, x))
}

iter(x => x + 1, 4, 1)
iter(x => x * x, 3, 5)
iter(x => if (x % 2 == 0) x + 1 else x + 2, 10, 1)

// exercise 4

val zip: ((List[Int], List[Int]) => List[(Int, Int)]) = (lA, lB) => if (lA.length > lB.length) lB match {
  case Nil => Nil
  case hd::tl => (lA.head, hd)::zip(lA.tail, tl)
} else lA match {
  case Nil => Nil
  case hd::tl => (hd, lB.head)::zip(tl, lB.tail)
}

zip(List(1, 2, 3), List(4, 5, 6))
zip(List(1, 2), List(4, 5, 6))
zip(List(1, 2), List(3))

// exercise 5

lazy val describe: (List[Int] => (Double, Int, Int)) = list => list match {
  case Nil => (0.0, Integer.MAX_VALUE, Integer.MIN_VALUE)
  case hd::tl => ((hd + tl.length*describe(tl)._1)/list.length, hd.min(describe(tl)._2), hd.max(describe(tl)._3))
}

describe(List(1, 3, 6, 8))
describe(List(1, 14, 7, 8, 20, 5))
