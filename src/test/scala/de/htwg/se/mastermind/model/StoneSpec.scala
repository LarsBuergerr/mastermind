/**
  * FILENAME.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model

//********************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class StoneSpec extends AnyWordSpec{
  "A Stone object" should {
    "be only instanced one time (singleton pattern)" in {
      Stone should be(Stone)
    }
    "have a function to generate random stones that are not Empty" in {
      for(i <- 1 to 1000){
        Stone.random should not be(Stone.Empty)
      }
    }
  }
  "A Game-Stone" should {
    "have a String representation of its color [Initial letter]" in {
      Stone.Red.toString() should be("R")
      Stone.Green.toString() should be("G")
      Stone.Blue.toString() should be("B")
      Stone.Yellow.toString() should be("Y")
      Stone.Purple.toString() should be("P")
      Stone.White.toString() should be("W")
      Stone.Empty.toString() should be(" ")
    }
  }
  "A Hint-Stone" should {
    "have a String representation of its color [Initial letter]" in {
      HintStone.Black.toString() should be("B")
      HintStone.White.toString() should be("W")
      HintStone.Empty.toString() should be("E")
    }  
  }
}