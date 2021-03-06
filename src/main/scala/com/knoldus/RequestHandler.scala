package com.knoldus

import akka.actor.{Actor, ActorRef, Props}

import scala.collection.mutable.ListBuffer
import akka.event.Logging

class RequestHandler extends Actor{

  val log = Logging(context.system, this)
  var phoneNumbers = ListBuffer[Int]()
  val validationRef = context.actorOf(ValidationActor.validationProps)

  override def receive = {

    case user: Customer => if(phoneNumbers.contains(user.mobileNumber)){
      log.error(s"${user.name}, You have already purchased Samsung S8, cannot purchase more!!")
      sender() ! "already purchased"
                            }

                            else {
                              phoneNumbers += user.mobileNumber
                              validationRef ! user
      sender() ! "request for phone purchase accepted"
                            }
  }

}


object RequestHandler{
  def requestProps = Props[RequestHandler]
}