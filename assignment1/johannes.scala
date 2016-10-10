object Assignment1 {
  def main(args: Array[String]): Unit = {
    println(combination(3))
    println(combinationList(3, List(0, 1, 2)))
  }

  val combination: (Int => List[List[Any]]) = _nInt => combinationList(_nInt, List('a', 'b'))

  lazy val combinationList: ((Int, List[Any]) => List[List[Any]]) = (_nInt, _setList) => {
    lazy val makeFullList: ((Int, List[Any]) => List[List[Any]]) = (_nInt, _fullList) => {
      if (_nInt == 0) {
        Nil
      } else {
        List(_fullList.head) :: makeFullList(_nInt - 1, _fullList.tail)
      }
    }
    lazy val concatLists: ((Int, List[List[Any]], List[Any], List[Any]) => List[List[Any]]) = (_addInt, _addFullList, _setImList, _setList) => {
      val expandList: ((Any, List[Any]) => List[Any]) = (_value, _expList) => _expList ::: List(_value)
      lazy val addElementToList: ((List[List[Any]], List[Any]) => List[List[Any]]) = (_bigList, _smallList) => {
        if (_smallList.tail != Nil) {
          List(expandList(_smallList.head, _bigList.head)) ::: addElementToList(_bigList, _smallList.tail)
        } else {
          List(expandList(_smallList.head, _bigList.head))
        }
      }
      if (_addFullList.tail != Nil) {
        addElementToList(_addFullList, _setList) ::: concatLists(_addInt, _addFullList.tail, _setImList, _setImList)
      } else {
        addElementToList(_addFullList, _setList)
      }
    }
    lazy val go: ((List[List[Any]], Int) => List[List[Any]]) = (l, n) => n match {
      case 2 => concatLists(_nInt, l, _setList, _setList)
      case _ => go(concatLists(_nInt, l, _setList, _setList), n - 1)
    }
    if (_nInt == 1) {
      Nil
    } else {
      go(concatLists(_nInt, makeFullList(_setList.length, _setList), _setList, _setList), _nInt - 1)
    }
  }
}
