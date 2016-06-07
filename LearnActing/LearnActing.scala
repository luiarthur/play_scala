import scala.actors._

object SillyActor extends Actor {
  def act() {
    for (i <- 1 to 5) {
      println("I'm acting!")
      Thread.sleep(1000)
    }
  }
}

object SeriousActor extends Actor {
  def act() {
    for (i <- 1 to 5) {
      println("To be or not to be.")
      Thread.sleep(1000)
    }
  }
}


SillyActor.start()
SeriousActor.start()

SillyActor.start(); SeriousActor.start()


val seriousActor2 = Actor.actor {
  for (i <- 1 to 5)
    println("That is the question.")
    Thread.sleep(1000)
}

seriousActor2

SillyActor ! "Hi there"

val echoActor = Actor.actor {
  while (true) {
    Actor.receive {
      case msg =>
        println("received message: "+ msg)
    }
  }
}

SillyActor ! "Hello there!"
SillyActor ! 15

val intActor = Actor.actor {
  Actor.receive {
    case x: Int => // I only want Ints
      println("Got an Int: "+ x)
  }
}

intActor ! "Chips Ahoy!"
intActor ! 2
