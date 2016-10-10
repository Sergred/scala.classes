val prod:(((Int,Int)) => Int) = (a) => a match { case (a1, a2) => a1*a2 }
val intProd:((List[Int], List[Int]) => Int) = (vec1, vec2) => {
  (vec1.zip(vec2).map(prod)).foldLeft(0)((a, b) => a + b)
}

intProd(List(1, 2, 3, 4, 5), List(0, 1, 2, 3, 4))

def intprod(v1:List[Int], v2:List[Int]): Int = {
  v1.zip(v2).map((t:(Int, Int)) => t._1*t._2).foldLeft(0)((a, b) => a+b)
}

intprod(List(1, 2, 3, 4), List(4, 5, 6, 7))
