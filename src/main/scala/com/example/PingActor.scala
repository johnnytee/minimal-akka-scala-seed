package com.example

import akka.actor.{Actor, ActorLogging, ActorRef,Props}

class PingActor extends Actor with ActorLogging {
  import PingActor._
  

  val pongActor = context.actorOf(PongActor.props, "pongActor")
  var pangActor:Option[ActorRef] = None
  
  def receive = {
    case PangMessage(text) => {
      pangActor = Some(sender())
      log.info("In PingActor - received message: {}", text)
      pongActor ! PingMessage("ping")  
    }
  	case PongMessage(text) => {
  	  log.info("In PingActor - received message: {}", text)
    pangActor match {
        case Some(x) =>  x ! PingMessage("ping")
                  log.info("ping sent to pang")
        case None => log.info("uhhh, got a pong but no ref to pang available!")
      }
    }
  }	
}

object PingActor {
  val props = Props[PingActor]
}