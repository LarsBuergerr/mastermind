/**
  * RequestSpec.scala
  */

//****************************************************************************** PACKAGE
package de.htwg.se.mastermind
package util


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


//****************************************************************************** CLASS DEFINITION
class RequestSpec extends AnyWordSpec {
  
  "A Single Char Request" should {
    "be instantiated" in {
      SingleCharRequest("a") shouldBe a [SingleCharRequest]
    }
    "be a Request" in {
      SingleCharRequest("a") shouldBe a [Request]
    }
  }
  
  "A Multi Char Request" should {
    "be instantiated" in {
      MultiCharRequest("a") shouldBe a [MultiCharRequest]
    }
    "be a Request" in {
      MultiCharRequest("a") shouldBe a [Request]
    }
  }
  
  "A Response" should {
    "be instantiated" in {
      Response(SingleCharRequest("a"), true) shouldBe a [Response]
    }
  }
  
}