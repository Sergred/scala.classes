import scala.collection.mutable.ListBuffer

object Calculator {
  val operations: ListBuffer[String] = ListBuffer.empty
  val sum: (Int, Int) => Int = (a, b) => {
    val r: Int = a + b
    operations += s"sum($a, $b) = $r"
    r
  }

  val multiply: (Int, Int) => Int = (a, b) => {
    val r: Int = a * b
    operations += s"multiply($a, $b) = $r"
    r
  }
}

Calculator.sum(1, 2)
Calculator.multiply(2, 3)
Calculator.operations.foreach(println)
