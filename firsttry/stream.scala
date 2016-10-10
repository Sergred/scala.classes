val stream:Stream[Int] = 0#::stream.map(_ + 1)

val sum: ((Stream[Double], Int) => Double) = (stream, n) => n match {
	case 0 => 0
	case _ => stream.head + sum(stream.tail, n - 1)
}

val sum: ((Stream[Int], Stream[Int]) => Stream[Int]) = (l1, l2) => (l1, l2) match {
  case (hd1#::tl1, hd2#::tl2) => (hd1 + hd2)#::sum(tl1, tl2)
}

sum(stream, stream).take(5) foreach println

val even = stream.zip(stream).map(t => t._1 + t._2)
even.take(5) foreach println

val fib:Stream[Int] = 0#::1#::fib.zip(fib.tail).map(t => t._1 + t._2)
fib.take(7) foreach println
