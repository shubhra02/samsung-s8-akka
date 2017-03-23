package com.knoldus.test

import akka.actor.{Actor, ActorSystem}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.knoldus.{Customer, ValidationActor}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}


class ValidationActorSpec extends TestKit(ActorSystem("samsung-system")) with WordSpecLike with BeforeAndAfterAll
  with MustMatchers with ImplicitSender {

  override protected def afterAll(): Unit = {
    system.terminate()
  }

  "ValidationActor" must {

    "reply 'phone booked' when phones are in stock" in{
      val validRef = TestActorRef[ValidationActor]
      validRef ! Customer("shubhra", "Noida", 882610, "09753468753")
      expectMsg("phone booked")
    }

    "reply 'out of stock' when phones are out of stock" in{
      val validRef = TestActorRef[ValidationActor]
      validRef.underlyingActor.noOfPhones = 0
      validRef ! Customer("shubhra", "Noida", 882610, "09753468753")
      expectMsg("out of stock")
    }

  }


}
