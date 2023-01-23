/**
  * FILENAME.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameComponent
package GameBaseImpl


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


//****************************************************************************** CLASS DEFINITION
class StoneSpec extends AnyWordSpec{
  "A Stone object" should {
    "be only instanced one time (singleton pattern)" in {
      Stone should be(Stone)
    }
    "have a function to generate random stones that are not Empty" in {
      for(i <- 1 to 1000){
        Stone.random should not be(Stone("E"))
      }
    }
    "have a string representation" in {
      Stone("R").toString() should be(Stone("R").stringRepresentation)
    }
  }
  "A HintStone object" should {
    "be only instanced one time (singleton pattern)" in {
      HintStone should be(HintStone)
    }
    "have a string representation" in {
      HintStone("R").toString() should be(HintStone("R").stringRepresentation)
    }
  }
  
  "A Game-Stone" should {
    "have a String representation of its color [Initial letter]" in {
      Stone.apply("R").toString() should be("R")
      Stone.apply("G").toString() should be("G")
      Stone.apply("B").toString() should be("B")
      Stone.apply("Y").toString() should be("Y")
      Stone.apply("P").toString() should be("P")
      Stone.apply("W").toString() should be("W")
      Stone.apply(" ").toString() should be("E")
      Stone.apply("E").toString() should be("E")
    }
  }
  "A Hint-Stone" should {
    "have a String representation of its color [Initial letter]" in {
      HintStone.apply("R").toString() should be("R")
      HintStone.apply("W").toString() should be("W")
      HintStone.apply("E").toString() should be("E")
      HintStone.apply(" ").toString() should be("E")
    }  
  }
}