def executionTime[A](f: => A) = {
	val start = System.nanoTime
	val result = f
	val time = (System.nanoTime - start) / 1e6
	(result, time)
}

def meanExecutionTime[A](n: Int, f: => A) = {
	(1 to n).map(_ => executionTime(f)._2).reduce((x1, x2) => x1 + x2) / n
}

val fact: Int => Int = n => n match {
  case 0 => 1
  case m => m*fact(m - 1)
}

val factorial: Int => Int = n => {
  lazy val loop: (Int, Int) => Int = (x, acum) =>
    if (x <= 1) acum
    else loop(x - 1, acum * x)
  loop(n, 1)
}

var a = 0.0
var b = 1.0
var count = 1
while (a < b) {
  a = meanExecutionTime(10, fact(count))
  b = meanExecutionTime(10, factorial(count))
  count = count + 1
}
println(s"$count")
