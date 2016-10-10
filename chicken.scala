((n:String)=>n.map(m=>if ("aeiouyAEIOUY" contains m) s"$mp$m" else m) mkString)("Hello")
