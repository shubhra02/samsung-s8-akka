package com.knoldus

import akka.actor.ActorSystem
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

object SamsungS8 extends App{
  val user = ConfigFactory.parseString(
    """
      |akka.actor.deployment {
      | /poolRouter {
      | router = round-robin-pool
      | nr-of-instances = 200
      | }
      |}
    """.stripMargin
  )

  val system = ActorSystem("SamsungStoreSystem", user)
  val requestRef = system.actorOf(RequestHandler.requestProps)
  val userRequest = system.actorOf(FromConfig.props(PurchaseActor.purchaseActorProps(requestRef)),"poolRouter")

  for(iterator <- 1 to 1050)
  userRequest ! Customer(s"Shubhra$iterator", s"Noida$iterator", iterator , s"093734767789479$iterator")



  userRequest ! Customer("Shubhra1", "Noida1", 1, "0937347677894791")



}
