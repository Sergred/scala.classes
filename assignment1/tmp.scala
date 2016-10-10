val combination: (Int => List[List[Int]]) = limit => {
	lazy val bar: ((Int, Int) => List[Int]) = (n, m) =>
		if (m > 1) bar(n/2, m - 1):::List(n%2) else n%2::Nil
	lazy val foo: ((Int, Int) => List[List[Int]]) = (n, limit) => 
		if (n < math.pow(2, limit).toInt) List(bar(n, limit)):::foo(n + 1, limit) else Nil
	foo(0, limit)
}

combination(1)
combination(2)
combination(3)
combination(4)