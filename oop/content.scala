class Content {
  var interactions: Int = 0
  val addInteraction: () => Unit = () => interactions = interactions + 1
}

class Text(val text: String) extends Content
class Image(val size: Int) extends Content

val c = new Content
val t = new Text("Some text content")
val i = new Image(5)

c.interactions // = 0
t.text // = Some text content
t.addInteraction
t.interactions // = 1
i.size // = 5
i.interactions // = 0

// class Content {
//   private var interactions: Int = 0
//   val addInteraction: () => Unit = () => interactions = interactions + 1
// }
//
// class Text(val text: String) extends Content {
//   val getInteractions: () => Int = () => interactions // Error
// }

// class Content {
//   protected var interactions: Int = 0
//   val addInteraction: () => Unit = () => interactions = interactions + 1
// }
//
// class Text(val text: String) extends Content {
//   val getInteractions: () => Int = () => interactions
// }
//
// val t = new Text("Some text content")
// t.interactions // Error
