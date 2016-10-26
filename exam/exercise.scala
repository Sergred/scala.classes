// Exam 1
// Exercise 1
val from: ((Int, Int) => List[Int]) = (n, m) => if (n <= m) n::from(n+1, m) else Nil

// Exercise 2
def from[A](vFrom:A, vTo:A, gen:(A => A)): List[A] = if (vFrom != vTo) vFrom::from(gen(vFrom), vTo, gen) else vTo::Nil

from(2, 10, (n:Int) => n + 2)
from('a', 's', (s:Char) => (s.asInstanceOf[Int] + 1).asInstanceOf[Char])

// Exercise 3
lazy val s: Stream[Double] = 1.0#::stream.map(_ + 1)

s.take(5) foreach println

lazy val average: Stream[Double] = s.zip(s.tail).map(t => t._1/2 + t._2).zip(s.tail.tail).map(t => t._1/2 + t._2/4)

average.take(5) foreach println

// Exercise 4
lazy val polynomialAt: (List[Int] => (Int => Int)) = list => { x =>
  list.zip(0 to (list.length - 1)).map(t => t._1*math.pow(x, t._2).toInt).foldLeft(0)((a, b) => a + b)
}

polynomialAt(List(1, 2, 3, 4))(2)

// Exercise 5
// tail-recursion

// Exam 2
// Exercise 1
val from: ((Int, Int) => List[Int]) = (n, m) => if (n <= m) n::from(n+1, m) else Nil

// Exercise 2
val nextrow: (List[Int] => List[Int]) = list => if (list.length == 1) List(1, 2, 1) else List(1):::(list.tail.zip(list).map(t => t._1 + t._2)):::List(1)

nextrow(List(1))
nextrow(List(1, 2, 1))
nextrow(List(1, 3, 3, 1))
nextrow(List(1, 4, 6, 4, 1))

// Exercise 3
def interleave[A](s1:Stream[A], s2:Stream[A]): Stream[A] = s1.head#::s2.head#::interleave(s1.tail, s2.tail)

interleave(Stream(1, 2, 3, 4), Stream(10, 20, 30, 40)).take(8) foreach println

// Exercise 4
var append: (List[Int], List[Int]) => List[Int] = (l1, l2) => l1:::l2

append(List(1, 2, 3, 4), List(5, 6, 7, 8))

var append: (List[Int], List[Int]) => List[Int] = (l1, l2) => l2 match {
  case Nil => l1
  case hd::tl => append(l1:::List(hd), tl)
}

append(List(1, 2, 3, 4), List(5, 6, 7, 8))

// Exercise 5
// currification
