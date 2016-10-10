import scala.collection.mutable.ListBuffer

class Tweet(val text: String) {
  private val retweets: Int = 0
}

object Tweet {
  val tweets: ListBuffer[Tweet] = ListBuffer.empty

  val create: String => Tweet = text => {
    val newTweet = new Tweet(text)
    tweets += newTweet
    newTweet
  }

  val mostRetweeted: () => Tweet = () => tweets.reduce(
    (t1, t2) => if (t1.retweets > t2.retweets) t1 else t2
  )
}
