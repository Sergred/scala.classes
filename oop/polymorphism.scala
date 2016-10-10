abstract class Content(val text: String)

trait Likable {
  def like()
}

class Tweet(t: String) extends Content(t) with Likable{
  var retweets: Int = 0
  override def like(): Unit = retweets = retweets + 1
}

val t1: Content = new Tweet("First tweet")
t1.text // = First tweet
t1.retweets // = Error: superclass cannot see attributes of subclass

val t2: Likable = new Tweet("Second tweet")
t2.text // = Error: trait cannot see attributes of class
t2.like // retweets + 1
