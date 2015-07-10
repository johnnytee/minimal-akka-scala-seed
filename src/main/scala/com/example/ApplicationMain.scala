package com.example

import akka.actor.ActorSystem



object ApplicationMain extends App {
  val system = ActorSystem("MyActorSystem")
  val pangActor = system.actorOf(PangActor.props, "pangActor")
  println("pangActor created")
  pangActor ! Initial(5)
  // This example app will ping pong 3 times and thereafter terminate the ActorSystem - 
  // see counter logic in PingActor
  system.awaitTermination()
}

case class PangMessage(text:String)
case class PingMessage(text:String)
case class PongMessage(text:String)
case class Initial(z:Int)
