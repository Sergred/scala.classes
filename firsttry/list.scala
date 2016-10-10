val length: (List[Int]=>Int) = (l) => {
  if (l == Nil) { 0 } else { 1 + length(l.tail) }
}

val sum: (List[Int] => Int) = (l) => {
  if (l == Nil) { 0 } else { l.head + sum(l.tail) }
}

val prod: (List[Int] => Int) = (l) => {
  if (l == Nil) { 1 } else { l.head * prod(l.tail) }
}

val a = 1::2::3::Nil
length(a)
sum(a)
prod(a)
