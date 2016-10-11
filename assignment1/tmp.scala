val combination: ((Int, List[Int]) => List[List[Int]]) = (limit, list) => {
	lazy val mapper: ((Int, List[Int]) => Int) = (m, l) => if (m == 0) l.head else mapper(m - 1, l.tail)
	lazy val bar: ((Int, Int) => List[Int]) = (n, m) =>	if (m > 1) bar(n/list.length, m - 1):::List(mapper(n%list.length, list)) else mapper(n%list.length, list)::Nil
	lazy val foo: ((Int, Int) => List[List[Int]]) = (n, limit) => if (n < math.pow(list.length, limit).toInt) List(bar(n, limit)):::foo(n + 1, limit) else Nil
	foo(0, limit)
}

combination(1, List(7, 8, 9))
combination(2, List(7, 8, 9))
combination(3, List(7, 8, 9))
// combination(4, List(7, 8, 9))
