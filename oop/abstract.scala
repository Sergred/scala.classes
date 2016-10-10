abstract class Content(val text: String) {
val getInteractionsCount: () => Int
}

class FacebookPost(text: String, val image: String) extends Content(text) {
  var shareCount: Int = 0
  var likeCount: Int = 0
  var dislikeCount: Int = 0
  val getInteractionsCount: () => Int = () => shareCount + likeCount + dislikeCount
  val share: () => Unit = () => shareCount = shareCount + 1
  val like: () => Unit = () => likeCount = likeCount + 1
  val dislike: () => Unit = () => dislikeCount = dislikeCount + 1
}

val content = new Content("Some text") // Error: cannot instantiate abstract class
