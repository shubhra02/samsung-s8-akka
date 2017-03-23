package com.knoldus

import akka.actor.{Actor, ActorRef, Props}

class PurchaseActor(requestHandlerRef: ActorRef) extends Actor{
  override def receive = {
    case user: Customer =>  sender() ! "this is purchasing customer of samsung s8"
                            requestHandlerRef ! user

  }

}


object PurchaseActor{
  def purchaseActorProps(requestHandlerRef: ActorRef) = Props(classOf[PurchaseActor], requestHandlerRef)
}