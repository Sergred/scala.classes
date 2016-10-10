/* a*x^2 + b*x + c = 0
 * D = b^2 - 4*a*c
 */

((a:Int, b:Int, c:Int) => {
 {
   val d = math.sqrt(b*b - 4*a*c)
   val x1 = (d - b)/(2*a)
   val x2 = (-d - b)/(2*a)
   (x1, x2)
 }
})(1, 2, 1)

((a:Int, b:Int, c:Int) => {
 val d = math.sqrt(b*b - 4*a*c)
 val x1 = (d - b)/(2*a)
 val x2 = (-d - b)/(2*a)
 (x1, x2)
})(1, 0, -3)
