class loopToN(n: Int) extends Runnable {
  def run () {
    (1 to n).map((i) => print(s"$i ")) // side effect
    println
  }
}

(new loopToN(10)).run // normal execution

val thread1 = new Thread(new loopToN(100))
val thread2 = new Thread(new loopToN(100))

thread1.start // parallel execution
thread2.start
