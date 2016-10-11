class Person(var name: String) {
  def set(changeName: String) {
    this.synchronized {
      name = changeName
    }
  }
}
