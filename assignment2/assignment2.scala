trait Tree[+A]
case object EmptyTree extends Tree[Nothing]
case object add extends Tree[Nothing]
case object mult extends Tree[Nothing]
case object divide extends Tree[Nothing]
case class Var[A](element:String) extends Tree[A]
case class Leaf[A](element:A) extends Tree[A]
case class Add[A](root:Tree[A], left:Tree[A], right:Tree[A]) extends Tree[A]

class Complex(val re: Double, val im: Double) {
  override def toString = re + (if (im < 0) "-" + -im else "+" + im) + "*i"
  def +(that: Complex) = new Complex(re + that.re, im + that.im)
  def -(that: Complex) = new Complex(re - that.re, im - that.im)
  def *(that: Complex) = new Complex(re*that.re - im*that.im, re*that.im + im*that.re)
  def /(that: Complex) = new Complex((re*that.re + im*that.im)/(that.re*that.re + that.im*that.im), (im*that.re - re*that.im)/(that.re*that.re + that.im*that.im))
}

def preorder[A](tree:Tree[A]): List[Any] = tree match {
  case Add(key, left, right) => key match {
    case `add` => List(add):::preorder(left):::preorder(right)
    case `mult` => List(mult):::preorder(left):::preorder(right)
    case `divide` => List(divide):::preorder(left):::preorder(right)
  }
  case Var(key) => s"$key"::Nil
  case Leaf(value) => value match {
    case y:Int => s"i$y"::Nil
    case y:Double => s"f$y"::Nil
    case _ => s"${value.toString}"::Nil
  }
}

def inorder[A](tree:Tree[A]): List[Any] = tree match {
  case Add(key, left, right) => key match {
    case `add` => inorder(left):::List(add):::inorder(right)
    case `mult` => inorder(left):::List(mult):::inorder(right)
    case `divide` => inorder(left):::List(divide):::inorder(right)
  }
  case Var(key) => s"$key"::Nil
  case Leaf(value) => value match {
    case y:Int => s"i$y"::Nil
    case y:Double => s"f$y"::Nil
    case _ => s"${value.toString}"::Nil
  }
}

trait Num[A] {
  // Both arguments must be provided. Addable works with the type A, but
  // does not extend it.
  def plus(x: A, y: A): A
  def subtract(x: A, y: A): A
  def multiply(x: A, y: A): A
  def divide(x: A, y: A): A
}

// This class adds the + operator to any type A that is Addable,
// by delegating to that Addable's `plus` method.
implicit class NumOps[A](lhs: A)(implicit ev: Num[A]) {
  def +(rhs: A): A = ev.plus(lhs, rhs)
  def -(rhs: A): A = ev.subtract(lhs, rhs)
  def *(rhs: A): A = ev.multiply(lhs, rhs)
  def /(rhs: A): A = ev.divide(lhs, rhs)
}

implicit object IntNum extends Num[Integer] {
  def plus(x: Integer, y: Integer): Integer = x + y
  def subtract(x: Integer, y: Integer): Integer = x - y
  def multiply(x: Integer, y: Integer): Integer = x * y
  def divide(x: Integer, y: Integer): Integer = x / y
}

implicit object DoubleNum extends Num[Double] {
  def plus(x: Double, y: Double): Double = x + y
  def subtract(x: Double, y: Double): Double = x - y
  def multiply(x: Double, y: Double): Double = x * y
  def divide(x: Double, y: Double): Double = x / y
}

implicit object ComplexNum extends Num[Complex] {
  def plus(x: Complex, y: Complex): Complex = x + y
  def subtract(x: Complex, y: Complex): Complex = x - y
  def multiply(x: Complex, y: Complex): Complex = x * y
  def divide(x: Complex, y: Complex): Complex = x / y
}

// We use a context bound to require that A has an Addable instance.
def evaluate[A: Num](tree:Tree[A]): String = {
  lazy val go: (Tree[A] => A) = t => t match {
    case Add(key, left, right) => key match {
      case `add` => go(left) + go(right)
      case `mult` => go(left) * go(right)
      case `divide` => go(left) / go(right)
    }
    case Var(key) => throw new Exception("Variable found! Use function 'evaluateWithVariables'")
    case Leaf(value) => value
  }
  go(tree) match {
    case x: Int => s"i$x"
    case x => x.toString
  }
}

def evaluateWithVariables[A: Num](tree:Tree[A], variable:Map[String, A]): String = {
  lazy val go: ((Tree[A], Map[String, A]) => A) = (t, m) => t match {
    case Add(key, left, right) => key match {
      case `add` => go(left, m) + go(right, m)
      case `mult` => go(left, m) * go(right, m)
      case `divide` => go(left, m) / go(right, m)
    }
    case Var(key) => variable(key)
    case Leaf(value) => value
  }
  go(tree, variable) match {
    case x: Int => s"i$x"
    case x => x.toString
  }
}

val example:Tree[Integer] = Add(mult,
Add(add, Leaf(new Integer(4)), Leaf(new Integer(7))),
Add(mult, Add(add, Leaf(new Integer(2)), Leaf(new Integer(1))),
Leaf(new Integer(5))))

inorder(example) // List[Any] = List(i4, add, i7, mult, i2, add, i1, mult, i5)
preorder(example) // List[Any] = List(mult, add, i4, i7, mult, add, i2, i1, i5)
evaluate(example) // Integer = i165

val exampleVar:Tree[Integer] = Add(mult, Add(add, Var("a"), Var("b")), Add(mult, Add(add, Var("c"), Var("d")), Var("e")))

evaluate(exampleVar)
evaluateWithVariables(exampleVar, Map[String, Integer]("a" -> 4, "b" -> 7, "c" -> 2, "d" -> 1, "e" -> 5))

val exampleVar:Tree[Double] = Add(mult, Add(add, Var("a"), Var("b")), Add(mult, Add(add, Var("c"), Var("d")), Var("e")))
evaluateWithVariables(exampleVar, Map[String, Double]("a" -> 4.0, "b" -> 7.1, "c" -> 2.3, "d" -> 1.7, "e" -> 5.2))

val compex:Tree[Complex] = Add(mult, Add(add, Leaf(new Complex(4, 5)), Leaf(new Complex(7, 3))), Leaf(new Complex(1, 2)))

evaluate(compex)
