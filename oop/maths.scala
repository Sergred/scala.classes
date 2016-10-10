object Maths {
  val sum: (Int, Int) => Int = (a, b) => a + b
  val max: (Int, Int) => Int = (a, b) =>
    if (a > b) a
    else b
}

Maths.sum(3, 4) // = 7

val m = Maths
m.max(8, 5) // = 8
