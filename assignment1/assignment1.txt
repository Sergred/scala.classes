// Exercise 1
val combination: (Int => List[List[Int]]) = number => {
  if (number < 0) throw new Exception("Negative number!")
  lazy val expand: ((List[Int], Int) => List[List[Int]]) = (l, d) => d match {
    case 0 => List(l)
    case _ => expand(l:::List(0), d - 1):::expand(l:::List(1), d - 1)
  }
  lazy val go: ((List[List[Int]], Int) => List[List[Int]]) = (l, n) => l match {
    case Nil => Nil
    case hd::tl => expand(hd, n - 1):::go(tl, n)
  }
  number match {
    case 0 => List()
    case _ => go(List(List(0), List(1)), number)
  }
}

// val combination: (Int => List[List[Int]]) = number => {
//   if (number < 0) throw new Exception("Negative number!")
//   lazy val expand: ((List[Int], Int) => List[Int]) = (l, n) => if (l.length < n) expand(0::l, n) else l
//   val convert: (Int => List[Int]) = number => {
//     lazy val go: (Int => List[Int]) = n => n match {
//       case 0 => Nil
//       case _ => go(n/2):::List(n % 2)
//     }
//     number match {
//       case 0 => List(0)
//       case _ => go(number)
//     }
//   }
//   lazy val result: (Int => List[List[Int]]) = a => a match {
//     case 0 => List(expand(List(0), number))
//     case _ => result(a - 1):::List(expand(convert(a), number))
//   }
//   number match {
//     case 0 => List()
//     case _ => result(math.pow(2, number).toInt - 1)
//   }
// }

combination(-1)
combination(0)
combination(1)
combination(2)
combination(3)
combination(4)
combination(5)
// combination(2).length == 4
// combination(3).length == 8
// combination(4).length == 16
// combination(5).length == 32

val combinationList: ((Int, List[Int]) => List[List[Int]]) = (number, list) => {
  if (number < 0) throw new Exception("Negative number!")
  if (list.length == 0) throw new Exception("Empty list!")
  lazy val expand: ((List[Int], Int) => List[List[Int]]) = (l, d) => {
    lazy val foo: (List[Int] => List[List[Int]]) = c => c match {
      case Nil => Nil
      case hd::tl => expand(l:::List(hd), d - 1):::foo(tl)
    }
    d match {
      case 0 => List(l)
      case _ => foo(list)
    }
  }
  lazy val go: ((List[List[Int]], Int) => List[List[Int]]) = (l, n) => l match {
    case Nil => Nil
    case hd::tl => expand(hd, n - 1):::go(tl, n)
  }
  lazy val listify: (List[Int] => List[List[Int]]) = list => list match {
    case Nil => Nil
    case hd::tl => List(List(hd)):::listify(tl)
  }
  number match {
    case 0 => List()
    case _ => go(listify(list), number)
  }
}

combinationList(3, List(0, 1, 2, 3))
combinationList(2, List(0, 1, 2, 3))
combinationList(2, List(0, 1, 2))
combinationList(2, List(0, 1))

combinationList(3, List(0, 1, 2, 3)).length == math.pow(4, 3)
combinationList(2, List(0, 1, 2, 3)).length == math.pow(4, 2)
combinationList(2, List(0, 1, 2)).length == math.pow(3, 2)
combinationList(2, List(0, 1)).length == math.pow(2, 2)

// Exercise 2
val baseChange: ((Int, Int) => List[Int]) = (number, base) => {
  if (number < 0) throw new Exception("Negative number!")
  if (base < 0) throw new Exception("Negative base!")
  lazy val go: ((Int, Int) => List[Int]) = (n, b) => n match {
    case 0 => Nil
    case _ => go(n/b, b):::List(n % b)
  }
  number match {
    case 0 => List(0)
    case _ => go(number, base)
  }
}

baseChange (-4532, 10)
baseChange (45, -2)

baseChange (4532, 10)
baseChange (45, 2)

(0 to 7).map(baseChange(_, 2))
(0 to 8).map(baseChange(_, 3))
