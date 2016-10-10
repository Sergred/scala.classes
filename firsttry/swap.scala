def swap(list:List[Int], i:Int, j:Int): List[Int] = {
    if (i == j) list else {
      if (i > j) swap(list, j, i) else {
        val (l1, rest) = list.splitAt(i - 1)
        val (l2, l3) = rest.splitAt(j - l1.length - 1)
        println(l1)
        println(l2)
        println(l3)
        println()
        l1:::List(l3.head):::l2.tail:::List(l2.head):::l3.tail
      }
    }
}

for (i <- 1 to 10) {
  for (j <- 1 to 10) {
    println(s"$i, $j")
    println(swap(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), i, j))
  }
}

def get(list:List[Int], i:Int): Int = {
  var tmp = list
  for (i <- 1 to i-1) tmp = tmp.tail
  tmp.head
}

get(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 1)
get(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2)
get(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3)
get(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 8)
get(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 10)
