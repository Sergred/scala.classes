package main.scala.exercises

/**
 * Some warm up exercises on:
 * - Recursion.
 * - Recursion with lists.
 * - Pattern matching.
 * - Anonymous functions
 * - Higher order functions.
 *
 * One rule: Only immutable variables.
 */
object Basic {
  type ??? = Nothing

  /**
   * -- Exercise 1 --
   * Create a "sum" function that receives two Int parameters.
   * The function should return the sum of all integers within the given range.
   * Test:
   * - sum(1, 3) = 6
   * - sum(5, 10) = 45
   */
  lazy val sum: (Int, Int) => Int = (f, t) => ??? //> sum: => (Int, Int) => Int

  /**
   * -- Exercise 2 --
   * Create a function "fact" that computes the factorial of a given number "n".
   * Test:
   * - fact(3) = 6
   * - fact(5) = 120
   * - fact(7) = 5040
   */
  lazy val fact: Int => Int = (n) => ???          //> fact: => Int => Int

  /**
   * -- Exercise 3 --
   * Create a function "square" that receives a list integers "ns" and
   * returns a new list with the squares of the values given in "ns"
   * Use pattern matching.
   * Test:
   * - List(1, 2, 3, 4, 5) = List(1, 4, 9, 16, 25)
   * - List(12, 56, 32) = List(144, 3136, 1024)
   */
  lazy val square: List[Int] => List[Int] = l => ???
                                                  //> square: => List[Int] => List[Int]

  /**
   * -- Exercise 4 --
   * Create a function "map" which receives a list of integers "ns" and a function "f"
   * and returns a new list with values of "ns" transformed by "f".
   * Test (use anonymous functions):
   * - Squares: List(1, 2, 3, 4, 5) = List(1, 4, 9, 16, 25)
   * - Cubes: List(1, 2, 3, 4, 5) = List(1, 8, 27, 64, 125)
   */
  lazy val map: (List[Int], Int => Int) => List[Int] = (ns, f) => ???
                                                  //> map: => (List[Int], Int => Int) => List[Int]

  /**
   * -- Exercise 5 --
   * Create a function "filter" that receives a list of integers "ns" and a function "include", and
   * returns a new list with only the values of "ns" which passes the "include" test.
   * Test (using anonymous functions):
   * - Even numbers: List(1, 2, 3, 4, 5) = List(2, 4)
   * - Multiples of 3: List(9, 8, 7, 6, 5, 4) = List(9, 6)
   * - Ages between 20 and 65: List(60, 23, 11, 76, 42, 9) = List(60, 23, 42)
   */
  lazy val filter: (List[Int], Int => Boolean) => List[Int] = (ns, include) => ???
                                                  //> filter: => (List[Int], Int => Boolean) => List[Int]
}
