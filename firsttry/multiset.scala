val union:((List[Any], List[Any]) => List[Any]) = (listA, listB) => listB match {
  case Nil => listA
  case hd::tl => union(listA:::List(hd), tl)
}

val intersection:((List[Any], List[Any]) => List[Any]) = (listA, listB) => {
  val find: ((Any, List[Any]) => (Boolean, List[Any])) = (item, list) => {
    def go(before:List[Any], item:Any, after:List[Any], another:Any): (Boolean, List[Any]) = {
      if (item == another) (true, before:::after) else after match {
        case Nil => (false, before:::List(item))
        case hd::tl => go(before:::List(item), hd, tl, another)
      }
    }
    go(List(), list.head, list.tail, item)
  }
  listA match {
    case Nil => Nil
    case hd::tl => if (find(hd, listB)._1) hd::intersection(listA.tail, find(hd, listB)._2) else intersection(tl, listB)
  }
}

// val intersection:((List[Any], List[Any]) => List[Any]) = (listA, listB) => {
//   val go: ((List[Any], Any) => List[Any]) = (list, el) => list match {
//     case Nil => Nil
//     case hd::tl => if (hd == el) List(hd) else foo(tl, el)
//   }
//   listA match {
//     case Nil => Nil
//     case hd::tl => foo(listB, hd):::intersection(tl, listB)
//   }
// }

val count:((List[Any], Any) => Int) = (list, element) => list match {
  case Nil => 0
  case hd::tl => if (hd == element) 1 + count(tl, element) else count(tl, element)
}

union(List(1, 2, 3), List(3, 4, 5))
intersection(List(1, 1, 2, 3), List(1, 2, 2, 2, 3, 4))
count(List(1, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5), 5)
