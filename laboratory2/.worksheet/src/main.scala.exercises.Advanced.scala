package main.scala.exercises

/**
 * Second round of warm up exercises on:
 * - Recursion.
 * - Recursion with lists.
 * - Pattern matching.
 * - Anonymous functions
 * - Higher order functions.
 *
 * One rule: Only immutable variables.
 */
object Advanced {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(261); val res$0 = 
	1;System.out.println("""res0: Int(1) = """ + $show(res$0))}

  /**
   * -- Exercise --
   * Create a function "length" which returns the number of elements in a given list.
   * Test:
   * - length(List(1, 2, 3, 4)) = 4
   * - length(List()) = 0
   */

  /**
   * -- Exercise --
   * Create a curryfied function "pow" which receives a number "exp" and returns a function
   * that calculates the power of another number "n". In that sense:
   * - exponential(2) would return a function that calculates the square of a number.
   * - exponential(3) would return a function that calculates the cube of a number.
   * Hint:
   * - Work with doubles.
   * - You can use Math.pow to get the power of a number e.g. Math.pow(2, 3) = 8
   * Test:
   * - val sqr = pow(2); sql(4) // = 16
   * - val cube = pow(3); cube(3) // = 27
   */

  /**
   * -- Exercise --
   * Create a function "iter" which receives a function "f" and two integers "i" and "x".
   * The function should return "i" times "f(x)", in that sense:
   * - For i = 3; iter = f(f(f(x)))
   * - For i = 0; iter = x
   * - For i = 1; iter = f(x)
   * Test:
   * - iter(x => x + 1, 4, 1) = 5
   * - iter(x => x * x, 3, 5) = 390625
   * - iter(x => if (x % 2 == 0) x + 1 else x + 2, 10, 1) = 21
   */

  /**
   * -- Exercise --
   * Create a function "zip" which takes two lists of integers and returns a new list of tuples where
   * each tuple contains one element from each list.
   * Test:
   * - zip(List(1, 2, 3), List(4, 5, 6)) = List((1, 4), (2, 5), (3, 6))
   * - zip(List(1, 2), List(4, 5, 6)) = List((1, 4), (2, 5))
   * - zip(List(1, 2), List(3)) = List((1, 3))
   */

  /**
   * -- Exercise --
   * Create a function "describe" which returns a tuple containing the average, minimum and maximum of a given list.
   * Hints:
   * - Minimum and maxium values for the type Int can be taken from the Java class Integer e.g. Integer.MAX_VALUE
   * - Type Int has inbuilt functions e.g. val a = 4; a.max(7); a.min(5)
   * Test:
   * - List(1, 3, 6, 8) = (4.5,1.0,8.0)
   * - List(1, 14, 7, 8, 20, 5) = (9.166666666666666,1.0,20.0)
   */

}
