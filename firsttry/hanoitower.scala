def doTowers(n:Int, from:String, inter:String, to:String): Unit = {
  if (n == 1) println(s"Disk 1 from $from to $to") else {
    doTowers(n - 1, from, to, inter)
    println(s"Disk $n from $from to $to")
    doTowers(n - 1, inter, from, to)
  }
}

doTowers(5, "A", "B", "C")
