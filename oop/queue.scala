abstract class IntQueue {
  def get(): Int
  def put(n: Int)
}

class SimpleIntQueue extends IntQueue {
  private val arr = new scala.collection.mutable.ArrayBuffer[Int]
  override def get(): Int = arr.get(0)
  override def put(n: Int): Unit = arr.put(n)
}

trait DoubleIntQueue extends IntQueue {
  abstract override def put(n: Int): Unit = super.put(n * 2)
  // dynamically bound part of a trait
}

val squeue = new SimpleIntQueue
val dqueue = new SimpleIntQueue with DoubleIntQueue

squeue.put(2)
squeue.get() // = 2
dqueue.put(2)
dqueue.get() // = 4
