package com.example

import akka.actor.{Actor, ActorLogging, Props}

class PongActor extends Actor with ActorLogging {
  import PongActor._

  def receive = {
  	case PingMessage(text) => 
  	  log.info("In PongActor - received message: {}", text)
  	  sender() ! PongMessage("pong")
      log.info("Pong was sent")
  }	
}

object PongActor {
  val props = Props[PongActor]
  
}
