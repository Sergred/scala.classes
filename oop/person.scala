class Person(private val name: String, var disguised: Boolean = true){
  val getName: () => String = () =>
    if (!disguised) s"I'm $name“
    else "I'm batman“
}

val batman = new Person("Bruce Wayne")

batman.getName // ???
batman.disguised = false
batman.getName // ???
