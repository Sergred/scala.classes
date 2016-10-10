val foo: (Stream[Int] => Stream[Int]) = s => s.head#::s.tail.filter(_ % s.head != 0)
val stream:Stream[Int] = 2#::stream.map(_ + 1)
val primes = foo(stream)

primes.take(20) foreach println
