package com.knoldus

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging

class ValidationActor extends Actor{

  val log = Logging(context.system, this)
  var noOfPhones = 1000
  override def receive = {
    case user: Customer => if(noOfPhones > 0){
                            noOfPhones -= 1
                            log.info(s"Phone successfully purchased by ${user.name}")
                           }
      else
      log.error(s"Sorry ${user.name}, phone is Out Of Stock!!  Try again later")
  }
}

object ValidationActor{
  def validationProps = Props[ValidationActor]
}