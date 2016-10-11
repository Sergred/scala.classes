object supermarket {
  import actors._
  private case object Newspaper extends Product
  private case object Thanks
  private case class Total (p: Double)
  private case class Chocolates (grams: Double) extends Product
  private case class Cheese (t: String, grams: Double) extends Product
  private case class Price (euros: Double)
  
  class Cashier (private[this] var cash: Double) extends Actor {
    def act () = {
      println ("Cashier")
      loop {
        receive {
          case Total (p) => {
            cash = cash + p
            println ("Cashier.Thanks. Total cash:"+cash)
            sender ! Thanks
          }
          case Newspaper => {
            println ("Cashier.Newspaper")
            sender ! Price (2.5)
          }
          case Chocolates (grams) => {
            println ("Cashier.Chocolate")
            sender ! Price ( grams * 0.015)
          }
          case Cheese (t, grams) => {
            println ("Cashier.Cheese")
            if (t == "Camembert") sender ! Price (grams * 0.01)
            else sender ! Price (grams*0.02)
          }
        }
      }
    }
  }

  class Customer (private[this] var products: List[Product], var name: String, cashier: Actor) extends Actor {
    def act () = {
      println ("Customer’s name:"+name)
      var total: Double = 0
      var toReceive = products.length
      products.map((x) => cashier ! x)
      while (toReceive!=0) {
        receive {
          case Price (euros) => {
            println (name+".Price:"+euros);
            total = total + euros
            toReceive = toReceive-1
          }
        }
      }
      println (name+".pay total:"+total)
      cashier ! Total(total);
      receive {
        case Thanks => println (name + ".finish")
      }
    }
  }

  def run () = {
    println("supermarket")
    val cashier = new Cashier(0)
    val customer1 = new Customer(List(Newspaper, Newspaper, Newspaper), "Customer-1", cashier)
    val customer2 = new Customer(List(Newspaper, Chocolates(1000), Cheese("Cabrales", 1000)), "Customer-2", cashier)
    cashier.start
    println("cashier started")
    customer1.start
    println("customer-1 started")
    customer2.start
    println("customer-2 started")
  }
}
