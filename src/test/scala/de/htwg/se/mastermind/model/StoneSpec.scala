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
    val objStone1 = Stone
    val objStone2 = Stone
    "be only instanced one time (singleton pattern)" in {
      objStone1 should be(objStone2)
    }
    "have a data variable containing a array of all available colors" in {
      objStone1.color should be(objStone2.color)
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
      HintStone.Empty.toString() should be(" ")
    }  
  }
}