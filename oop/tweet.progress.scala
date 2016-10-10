class Tweet {
  val text: String = "Hello tweet"
  val retweets: Int = 0
}

val tw = new Tweet
tw.text // = Hello tweet
tw.retweets // = 0

// tw.text = "Hello tweet in Scala" // =  error: reassignment to val
// tw.retweets = 1 // =  error: reassignment to val

class Tweet {
  val text: String = "Hello tweet"
  var retweets: Int = 0
  val isRetweeted: () => Boolean = () => retweets > 0
}

val tw = new Tweet
tw.isRetweeted // = false
tw.retweets = 1
tw.isRetweeted // = true

class Tweet(t: String) {
  val text: String = t
  var retweets: Int = 0
  val isRetweeted: () => Boolean = () => retweets > 0
}

// class Tweet(val text: String) {
//   var retweets: Int = 0
//   val isRetweeted: () => Boolean = () => retweets > 0
// }

val tw1 = new Tweet("My first tweet")
val tw2 = new Tweet("My second tweet")
tw1.text // = My first tweet
tw2.text // = My second tweet
