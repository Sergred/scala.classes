def swap(list:List[Int], i:Int, j:Int): List[Int] = {
  if (i == j) list else {
    if (i > j) swap(list, j, i) else {
      val (l1, rest) = list.splitAt(i - 1)
      val (l2, l3) = rest.splitAt(j - l1.length - 1)
      l1:::List(l3.head):::l2.tail:::List(l2.head):::l3.tail
    }
  }
}

def get(list:List[Int], i:Int): Int = if (i == 1) list.head else get(list.tail, i - 1)

// def partition(list:List[Int], p:Int, r:Int): (List[Int], Int) = {
//   val x = get(list, r)
//   var i = p
//   var tmp = list
//   for (j <- p to r) {
//     if (get(list, j) <= x) {
//       i = i + 1
//       tmp = swap(tmp, i, j)
//     }
//   }
//   (swap(tmp, i + 1, r), i + 1)
// }

import scala.util.control.Breaks._
def partition(list:List[Int], lo:Int, hi:Int): (List[Int], Int) = {
  val pivot = get(list, lo)
  var i = lo - 1
  var j = hi + 1
  var tmp = list
  breakable {
    do i = i + 1 while (get(list, i) < pivot)
    do j = j - 1 while (get(list, j) > pivot)
    if (i >= j) break else tmp = swap(list, i, j)
  }
  (tmp, j)
}

def join(lA:List[Int], lB:List[Int], pivot:Int): List[Int] = {
  if (pivot == 1) lB else List(lA.head):::join(lA.tail, lB.tail, pivot - 1)
}

def qsort(list:List[Int], p:Int, r:Int): List[Int] = {
  // println(s"$p, $r")
  if (p < r) {
    val (l, q) = partition(list, p, r)
    join(qsort(l, p, q - 1), qsort(l, q + 1, r), q)
  } else list
}

def quicksort(list:List[Int]): List[Int] = {
  qsort(list, 1, list.length)
}

quicksort(List(3, 2, 1, 4, 7, 6, 5, 9))

// exercise 7
def swap(list:List[Any], i:Int, j:Int): List[Any] = {
  if (i == j) list else {
    if (i > j) swap(list, j, i) else {
      val (l1, rest) = list.splitAt(i - 1)
      val (l2, l3) = rest.splitAt(j - l1.length - 1)
      l1:::List(l3.head):::l2.tail:::List(l2.head):::l3.tail
    }
  }
}

def get(list:List[Any], i:Int): Any = if (i == 1) list.head else get(list.tail, i - 1)

def partition(list:List[Any], compare:(Any, Any) => Boolean, lo:Int, hi:Int): (List[Any], Int) = {
  val pivot = get(list, hi)
  var i = lo - 1
  var tmp = list
  for (j <- lo to hi - 1) {
    if (compare(get(list, j), pivot)) {
      i = i + 1
      tmp = swap(list, i, j)
    }
  }
  println(i + 1)
  (swap(tmp, i + 1, hi), i + 1)
}

algorithm quicksort(A, lo, hi) is
    if lo < hi then
        p := partition(A, lo, hi)
        quicksort(A, lo, p – 1)
        quicksort(A, p + 1, hi)
algorithm partition(A, lo, hi) is
    pivot := A[hi]
    i := lo        // place for swapping
    for j := lo to hi – 1 do
        if A[j] ≤ pivot then
            swap A[i] with A[j]
            i := i + 1
    swap A[i] with A[hi]
    return i

def join(lA:List[Any], lB:List[Any], pivot:Int): List[Any] = {
  if (pivot == 1) lB else List(lA.head):::join(lA.tail, lB.tail, pivot - 1)
}

def qsort(list:List[Any], compare:(Any, Any) => Boolean, p:Int, r:Int): List[Any] = {
  println(list)
  if (p < r) {
    val (l, q) = partition(list, compare, p, r)
    join(qsort(l, compare, p, q - 1), qsort(l, compare, q + 1, r), q)
  } else list
}

def quicksort(list:List[Any], compare:(Any, Any) => Boolean): List[Any] = {
  qsort(list, compare, 1, list.length)
}

quicksort(List(9, 8, 7, 6, 5, 4, 3, 2, 1), (a, b) => if (a.asInstanceOf[Int] < b.asInstanceOf[Int]) true else false)
quicksort(List(3.0, 2.9, 2.4, 4.8, 6.5, 6.7, 5.2, 9.3), (a, b) => if (a.asInstanceOf[Double] < b.asInstanceOf[Double]) true else false)
quicksort(List("hello", "world", "a", "cat", "home"), (a, b) => if (a.toString().length < b.toString().length) true else false)
quicksort(List("hello", "world", "a", "cat", "home"), (a, b) => if (a.toString() < b.toString()) true else false)
List("hello", "world", "a", "cat", "home").sortWith(_.length < _.length)
