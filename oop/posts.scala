class Content(val text: String) {
  private var interactions: Int = 0
  val addInteraction: () => Unit = () => interactions = interactions + 1
}

class Tweet(text: String) extends Content(text) {
  var retweetCount: Int = 0
  val retweet: () => Unit = () => {
    retweetCount = retweetCount + 1
    addInteraction
  }
}

class FacebookPost(text: String) extends Content(text) {
  var likeCount: Int = 0
  val like: () => Unit = () => {
    likeCount = likeCount + 1
    addInteraction
  }
}
