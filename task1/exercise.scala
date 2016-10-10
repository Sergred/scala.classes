def executionTime[A](f: => A) = {
	val start = System.nanoTime
	val result = f
	val time = (System.nanoTime - start) / 1e6
	(result, time)
}

def meanExecutionTime[A](n: Int, f: => A) = {
	(1 to n).map(_ => executionTime(f)._2).reduce((x1, x2) => x1 + x2) / n
}

// exercise 1
val from: ((Int, Int) => List[Int]) = (n, m) => n match {
  case `m` => List(m)
  case _ => n::from(n + 1, m)
}

// from(4, 20)

// exercise 2
val from: ((Any, Any, Any => Any) => List[Any]) = (vFrom, vTo, gen) => vFrom match {
  case `vTo` => List(vTo)
  case _ => vFrom::from(gen(vFrom), vTo, gen)
}

// from(3, 10, (a:Int) => (a + 1))
// from(3, 10, (a:Any) => (a.asInstanceOf[Int] + 1).asInstanceOf[Any])
// from(2, 10, (a:Any) => (a.asInstanceOf[Int] + 2).asInstanceOf[Any])
// from('a', 'k', (a:Any) => (a.asInstanceOf[Char].asInstanceOf[Int] + 1).asInstanceOf[Char].asInstanceOf[Any])
// from(16, 256, (a:Any) => (a.asInstanceOf[Int]*2).asInstanceOf[Any])

// exercise 3
val nextrow: (List[Int] => List[Int]) = l => {
  def go(list:List[Int]): List[Int] = list match {
    case Nil => List(1)
    case hd::tl => if (tl.isEmpty) go(tl) else List(hd + tl.head):::go(tl)
  }
  1::go(l)
}

// nextrow(List(1, 3, 3, 1))
// nextrow(List(1))

val nextrow: (List[Int] => List[Int]) = l => (l:::List(0)).zip(List(0):::l).map((t:(Int, Int)) => t._1 + t._2)

// nextrow(List(1, 3, 3, 1))

// exercise 4
def append: ((List[Int], List[Int]) => List[Int]) = (listA, listB) => listB match {
  case Nil => listA
  case hd::tl => append(listA:::List(hd), tl)
}

// append(List(1, 2, 3), List(4, 5, 6))

val append: ((List[Int], List[Int]) => List[Int]) = (listA, listB) => {
  (listA:::List.fill(listB.length)(0)).zip(List.fill(listA.length)(0):::listB).map((t:(Int, Int)) => t._1 + t._2)
}

// append(List(2, 3, 4), List(5, 6, 7))

// exercise 5
val curryF: (Double => (Double => (Double => Double))) = a => { b => { c => a*(b + c)*(b + c)}}

// curryF(2)(3)(1)
// List(1.0, 2.0, 3.0).map(curryF(2)(3))

// exercise 6
val quicksort: (List[Int] => List[Int]) = list => list match {
  case Nil => Nil
  case hd::tl => quicksort(tl.filter(_ < hd)):::hd::quicksort(tl.filter(_ >= hd))
}

// quicksort(List(5, 4, 8, 9, 2, 1, 3, 7, 6))

// exercise 7
val quicksort: ((List[Any], (Any, Any) => Boolean) => List[Any]) = (list, compare) => list match {
  case Nil => Nil
  case hd::tl => quicksort(tl.filter(compare(_,  hd)), compare):::hd::quicksort(tl.filter(!compare(_, hd)), compare)
}

// quicksort(List(5, 4, 8, 9, 2, 1, 3, 7, 6), (a, b) => if (a.asInstanceOf[Int] < b.asInstanceOf[Int]) true else false)
// quicksort(List(3.0, 2.9, 2.4, 4.8, 6.5, 6.7, 5.2, 9.3), (a, b) => if (a.asInstanceOf[Double] < b.asInstanceOf[Double]) true else false)
// quicksort(List("hello", "world", "a", "cat", "home"), (a, b) => if (a.toString().length < b.toString().length) true else false)

// exercise 8
val monomial: ((Double, Double) => (Double => Double)) = (a, e) => x => a*math.pow(x, e)

// val a = 1
// val e = 2
// val setOfValues = Set(1, 2, 3, 2.2)
// monomial(1, 2)(5)
// setOfValues.map(monomial(a, e))

// exercise 9
val polynomialAt: (List[Double] => (Double => Double)) = list => {
  x => {
    def go(list: List[Double], x: Double, count: Int): Double = list match {
      case Nil => 0
      case hd::tl => hd*math.pow(x, count) + go(tl, x, count + 1)
    }
    go(list, x, 0)
  }
}

// polynomialAt(List(1, 3, 3, 1))(2)
// meanExecutionTime(10, polynomialAt(List(1, 3, 3, 1))(2))

val polynomialAt: (List[Double] => (Double => Double)) = list => { x =>
  list.zip(0 to list.length - 1).map((t:(Double, Int)) => t._1*math.pow(x, t._2)).foldLeft(0.0)((a, b) => a + b)
}

// polynomialAt(List(1, 3, 3, 1))(2)
// meanExecutionTime(10, polynomialAt(List(1, 3, 3, 1))(2))

// exercise 10
val polynomialCompactAt: (List[Double] => (List[Int] => (Double => Double))) = a => { power => { x =>
  a.zip(power).map((t:(Double, Int)) => t._1*math.pow(x, t._2)).foldLeft(0.0)((a, b) => a + b)
}}

// polynomialCompactAt(List(2, 3, 6, 3))(List(1, 2, 3, 4))(2)

// exercise 11
val stream: ((Int, (Int => Double)) => Stream[Double]) = (n, f) => f(n)#::stream(n + 1, f)

val s = stream(0, n => math.pow(-1, n)/(2*n + 1))
val t = stream(0, n => math.pow(-4.0/3, n)/(2*n + 1))

val diff: ((Stream[Double], Stream[Double]) => Stream[Double]) = (s1, s2) => math.abs(s1.head - s2.head)#::diff(s1.tail, s2.tail)

val pi: ((Stream[Double], Double) => (Int => Double)) = (stream, coef) => {
	n => n match {
		case 0 => 0.0
		case _ => coef*stream.head + pi(stream.tail, coef)(n - 1)
	}
}

// pi(s, 4)(5)
// pi(t, math.pow(12, 0.5))(5)

val foo: (Stream[Double] => Stream[Double]) = stream => math.abs(stream.head - math.Pi)#::foo(stream.tail)

lazy val sumn: (Stream[Double] => Stream[Double]) = stream => {
	lazy val sum: ((Stream[Double], Int) => Double) = (s, n) => n match {
		case 0 => 0.0
		case _ => s.head + sum(s.tail, n - 1)
	}
	lazy val go: ((Stream[Double], Int) => Stream[Double]) = (stream, n) => sum(stream, n)#::go(stream, n + 1)
	go(stream, 1)
}


// sumn(s).take(5) foreach println
// foo(s).take(5) foreach println
// sumn(t).take(5) foreach println
// foo(t).take(5) foreach println

// exercise 12
lazy val q: ((Stream[Int], Int) => Int) = (s, n) => n match {
	case 1 => s.head
	case _ => q(s.tail, n - 1)
}

lazy val qngen: ((Stream[Int], Int) => Stream[Int]) = (s, n) => (q(s, n - q(s, n - 1)) + q(s, n - q(s, n - 2)))#::qngen(s#:::(q(s, n - q(s, n - 1)) + q(s, n - q(s, n - 2)))#::Stream.empty, n + 1)

val qn:Stream[Int] = 1#::1#::qngen(1#::1#::Stream.empty, 3)
// qn.take(10) foreach println

val dr: Int => Int = n => n match {
	case 1 => 1
	case 2 => 1
	case _ => dr(dr(n - 1)) + dr(n - 1 - dr(n - 2))
}

lazy val foo: (Int => Stream[Int]) = n => dr(n)#::foo(n + 1)
val d:Stream[Int] = foo(1)
// d.take(10) foreach println

// exercise 13
lazy val interleave: ((Stream[Int], Stream[Int]) => Stream[Int]) = (a, b) => a.head#::b.head#::interleave(a.tail, b.tail)

// interleave(Stream(1, 2, 3, 4),Stream(10, 20, 30, 40)).take(5) foreach println

// exercise 14
lazy val seqSingen: (Int => Stream[Double]) = n => Math.sin(n.toDouble/2)#::seqSingen(n + 1)

val seqSin: Stream[Double] = seqSingen(1)
// seqSin.take(5) foreach println

lazy val seqSinEgen: (Int => Stream[Double]) = n => (Math.sin(n.toDouble/2) + scala.util.Random.nextDouble/10)#::seqSinEgen(n + 1)

val seqSinE: Stream[Double] = seqSinEgen(1)
// seqSinE.take(5) foreach println

// exercise 15
lazy val movAv: (List[Double] => (Stream[Double] => Stream[Double])) = list => {
	stream => {
		lazy val go: (Stream[Double], List[Double]) => Double = (s, l) => l match {
			case Nil => 0
			case hd::tl => hd*s.head + go(s.tail, tl)
		}
		lazy val gen: ((Stream[Double], List[Double]) => Stream[Double]) = (s, l) => go(s, l)#::gen(s.tail, l)
		lazy val foo: ((Stream[Double], Stream[Double], List[Double]) => Stream[Double]) = (s, t, l) => l.length match {
			case 1 => s.head#::gen(t, l)
			case _ => s.head#::foo(s.tail, s, l.tail)
		}
		foo(stream, stream, list)
	}
}

lazy val compare: ((Stream[Double], Stream[Double], Int) => Double) = (stream1, stream2, num) => {
	lazy val go: ((Stream[Double], Stream[Double], Int) => Double) = (s1, s2, n) => n match {
		case 0 => 0.0
		// case _ => math.pow(math.pow(s1.head, 2) - math.pow(s2.head, 2), 0.5) + go(s1.tail, s2.tail, n - 1)
		case _ => math.abs(s1.head - s2.head) + go(s1.tail, s2.tail, n - 1)
	}
	go(stream1, stream2, num)/num
}

val av1:Stream[Double] = movAv(List(1.0/3, 1.0/3, 1.0/3))(seqSinE)
val av2:Stream[Double] = movAv(List(1.0/14, 1.0/7, 4.0/7, 1.0/7, 1.0/14))(seqSinE)

// av1.take(10) foreach println
// av2.take(10) foreach println

// compare(av1, seqSin, 10)
// compare(av2, seqSin, 10)

// exercise 16
// Tail-recursion

// exercise 17
// Currification
