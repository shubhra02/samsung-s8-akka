package com.knoldus.test

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.knoldus.{Customer, RequestHandler}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

import scala.collection.mutable.ListBuffer


class RequestHandlerSpec extends TestKit(ActorSystem("samsung-system")) with WordSpecLike with BeforeAndAfterAll
  with MustMatchers with ImplicitSender {

  override protected def afterAll(): Unit = {
    system.terminate()
  }

   "request handler" must {
     "reply with  'request for phone purchase accepted' when phone number is not in list" in {
       val requestRef = TestActorRef[RequestHandler]
       requestRef ! Customer("shubhra", "noida", 111, "12232334")
       expectMsg("request for phone purchase accepted")
     }

     "reply with'already purchased' when phone number is already available in list" in {
       val requestRef = TestActorRef[RequestHandler]
       requestRef.underlyingActor.phoneNumbers = ListBuffer(111,333)
       requestRef ! Customer("shubhra", "noida", 111, "12232334")
       expectMsg("already purchased")
     }
   }

}
