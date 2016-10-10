def executionTime[A](f: => A) = {
	val start = System.nanoTime
	val result = f
	val time = (System.nanoTime - start) / 1e6
	(result, time)
}

def meanExecutionTime[A](n: Int, f: => A) = {
	(1 to n).map(_ => executionTime(f)._2).reduce((x1, x2) => x1 + x2) / n
}
