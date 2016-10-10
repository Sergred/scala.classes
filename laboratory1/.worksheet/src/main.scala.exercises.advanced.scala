package main.scala.exercises

/**
 * Some a bit more advanced(?) exercises for practicing:
 * - Recursion.
 * - Recursion with lists.
 * - Pattern matching.
 * - Anonymous functions
 * - Higher order functions.
 *
 * One rule: Only immutable variables.
 */
object advanced {
  type ??? = Nothing;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(595); 

  /**
   * -- Exercise 1 --
   * Create a function "fibonacci" which returns the value of a given position
   * within the fibonacci sequence.
   * https://en.wikipedia.org/wiki/Fibonacci_number
   * Test:
   * - 4 = 3
   * - 7 = 13
   * - 12 = 144
   */
  lazy val fibonacci: Int => Int = n => ???;System.out.println("""fibonacci: => Int => Int""");$skip(415); 

  /**
   * -- Exercise 2 --
   * Create a function "reduce" which receives a list of integers "ns" and a function "combine"
   * and returns an integer resulting from having combined all values of "ns".
   * Test (use anonymous functions):
   * - Sum: List(1, 8, 4, 3, 9, 5) = 30.
   * - Max: List(23, 76, 34, 84, 24, 58) = 84.
   */
  lazy val reduce: (List[Int], (Int, Int) => Int) => Int = (ns, combine) => ???;System.out.println("""reduce: => (List[Int], (Int, Int) => Int) => Int""");$skip(518); 

  /**
   * -- Exercise 3 --
   * Create a function "pascal" which receives two parameters, the first referring to a row number and
   * the latter to the column. These two are the coordinates to a value in the pascal triangle.
   * The function should return the value of the given coordinates.
   * https://en.wikipedia.org/wiki/Pascal%27s_triangle
   * Test:
   * - pascal(1, 1) = 1
   * - pascal(3, 2) = 2
   * - pascal(6, 4) = 10
   * - pascal(5, 5) = 1
   */
  lazy val pascal: (Int, Int) => Int = (r, c) => ???;System.out.println("""pascal: => (Int, Int) => Int""");$skip(510); 

  /**
   * -- Exercise 4 --
   * Create a function which indicates whether a string containing parentheses is balanced.
   * Notes:
   * - You can use nested functions (use "lazy val" for nested functions).
   * - The String will be given in the form of a List of characters.
   * Test:
   * - "hello (world)".toList = true (balanced).
   * - "(()".toList = false (unbalanced).
   * - "()(())".toList = true.
   * - "(a)(b(c)()".toList = false.
   */
  lazy val balanced: List[Char] => Boolean = chars => ???;System.out.println("""balanced: => List[Char] => Boolean""")}

}
