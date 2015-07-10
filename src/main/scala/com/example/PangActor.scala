package com.example

import akka.actor.{Actor, ActorLogging, Props}

/**
 * @author John
 */
class PangActor extends Actor with ActorLogging {
  import PingActor._
  
  
  var counter = 0
  var z:Int = -1
  val pingActor = context.actorOf(PingActor.props, "pingActor")
  
  def receive = {
    case Initial(zz) =>
      z=zz
      println("initialized")
      log.info("In PangActor - starting pang-ping-pong-ping")
      
      pingActor ! PangMessage("pang") 
      
    case PingMessage(text) =>
      log.info("In PangActor - received message: {}", text)
      counter=counter+1
      if (counter == z) context.system.shutdown()
      else sender() ! PangMessage("pang")
  } 
}

object PangActor {
  val props = Props[PangActor]

}