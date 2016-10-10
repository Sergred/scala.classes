// Map-Reduce
val mapReduce: (Int, Int) => (Int => Int) => (((Int, Int) => Int) => Int) = (a, b) => {
  fmap => {
    freduce => freduce(fmap(a), fmap(b))
  }
}

mapReduce(3, 4)

val times: Int => (Int => Int) = n => a => a * n
val time3 = times(3)
time3(4)

// Syntactic sugar
def mapReduce(a:Int, b:Int)(fmap: Int => Int)(freduce: (Int, Int) => Int):Int = freduce(fmap(a), fmap(b))

mapReduce(3, 2)(_ + 1)(_ + _)

// Laziness
lazy val looper(Int => Unit) => if (n <= 0) println("done") else {
  println(n)
  looper(n - 1)
}

looper(5)

trait Monoid[A] {
  def id: A // identity element
  def op (a: A, b: A): A // associative function
}

// 0 + s = s + 0 = s
// a + b + c = (a + b) + c = a + (b + c)

class StringMonoid extends Monoid[String] {
  def id:
  def op(String a, String b): String = a + b
}

val s = new StringMonoid
s.op("a", s.op("b", s.op("c", "d")))
s.op(s.op("a", "b"), s.op("c", "d")))
