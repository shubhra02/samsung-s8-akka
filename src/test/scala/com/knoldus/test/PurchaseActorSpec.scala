package com.knoldus.test

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.knoldus.{Customer, PurchaseActor, RequestHandler}
import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

class PurchaseActorSpec extends TestKit(ActorSystem("samsung-system")) with WordSpecLike with BeforeAndAfterAll
  with MustMatchers with ImplicitSender {

  override protected def afterAll(): Unit = {
    system.terminate()
  }

  "purchaseactor" must {
    "reply with 'this is purchasing customer of samsung s8' when the customer requests for purchasing phone" in {
      val purchaseRef = TestActorRef(PurchaseActor.purchaseActorProps(TestActorRef(RequestHandler.requestProps)))
      purchaseRef ! Customer("shubhra", "Noida", 882454, "09753468753")
      expectMsg("this is purchasing customer of samsung s8")
    }
  }
}
