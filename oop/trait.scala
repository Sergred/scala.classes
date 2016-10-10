// Traits:
// Used to mix in different attributes and actions into classes.
// A class can inherit from more than one trait at a time.
// It differs from classes in that:
// It doesn’t receive class parameters.
// Super calls are dynamically bound i.e. they’re determined when mixed in into a
// concrete class.

trait Likable {
  val like: () => Unit
}

trait Editable {
  val update: String => Unit
}

abstract class Content (protected var text: String)

class Tweet(text: String) extends Content(text) with Likable {
  var likeCount: Int = 0
  override val like: () => Unit = () => likeCount = likeCount + 1
}

class FacebookPost(t: String) extends Content(t) with Likable with Editable {
  var likeCount: Int = 0
  override val like: () => Unit = () => likeCount = likeCount + 1
  override val update: (String) => Unit = newText => text = newText
}
