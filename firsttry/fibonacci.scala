val fibonacci: (Int => Int) = n => {
  n match {
    case 0 => 0
    case 1 => 1
    case _ => fibonacci(n-1) + fibonacci(n-2)
  }
}

fibonacci(1)
fibonacci(2)
fibonacci(3)
fibonacci(4)
fibonacci(5)
fibonacci(6)
fibonacci(7)
fibonacci(8)
fibonacci(9)
fibonacci(10)
