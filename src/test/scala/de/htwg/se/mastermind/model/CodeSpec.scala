/**
  * CodeSpec.scala
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
class CodeSpec extends AnyWordSpec{
  "A default Code for single player mode" should {
    val defaultCode = new Code()
    "have a size of 4" in{
      defaultCode.size should be(4)
    }
    //@todo: this is not guaranteed
    "is random and therefore not equal" in{
      defaultCode should not be(new Code())
    }
    "have a compare method which returns a 4 Red Hint Stones Vector when same code is compared" in {
      val redHintStone = HintStone.apply("R")
      //defaultCode.compareTo(defaultCode.code) should contain (redHintStone)	
      defaultCode.compareTo(defaultCode.code) shouldBe a [Vector[HStone]]
      defaultCode.compareTo(defaultCode.code)(0) shouldBe a [HStone]
      
      redHintStone shouldBe a [HStone]
      redHintStone.stringRepresentation should be("R")
      
      defaultCode.compareTo(defaultCode.code)(0).stringRepresentation should be("R")
      defaultCode.compareTo(defaultCode.code).toString() should be("Vector(R, R, R, R)")
      
      // Why is this not working?
      //defaultCode.compareTo(defaultCode.code) should be(Vector(HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R")))
    }
  }
  
  "A Code [Red, Red, Blue, Yellow] generated for multiplayer mode" should {
    val solutionCode = new Code(Vector(Stone.apply("R"), Stone.apply("R"), Stone.apply("B"), Stone.apply("Y")))
    val wrongCode = new Code(Vector(Stone.apply("R"), Stone.apply("B"), Stone.apply("P"), Stone.apply("R")))
    "have a size of 4" in{
      solutionCode.size should be(4)
    }
    "should have a String representation 'R | R | B | Y'" in {
      solutionCode.toString() should be("R | R | B | Y")
    }
    //@todo: How could this be automated to test many different codes?
    "should return a hint code [Red, White, White, Empty] when compared with user code [Red, Blue, Purple, Red]" in {
      solutionCode.compareTo(wrongCode.code) should be(Vector(HintStone("R"), HintStone("W"), HintStone("W"), HintStone("E")))
    }
  }
  "The Code ................................................. R | R | R | R" should{
   val codeCompare1  = new Code(Vector(Stone("R"), Stone("R"), Stone("R"), Stone("R")))
   "have HintStones[B | B | B | B] when compared with R | R | R | R" in {
     (codeCompare1.compareTo(Vector(Stone("R"), Stone("R"), Stone("R"), Stone("R"))) 
     should equal (Vector(HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R"))))
   }
  }
  "The Code ................................................. R | R | B | Y" should{
   val codeCompare2  = new Code(Vector(Stone("R"), Stone("R"), Stone("B"), Stone("Y")))
   "have HintStones[B | W |   |  ] when compared with R | G | R | P" in {
     (codeCompare2.compareTo(Vector(Stone("R"), Stone("G"), Stone("R"), Stone("P"))) 
     should equal (Vector(HintStone("R"), HintStone("W"), HintStone("E"), HintStone("E"))))
   }
  }
  "The Code ................................................. R | R | B | Y" should{
   val codeCompare3  = new Code(Vector(Stone("R"), Stone("R"), Stone("B"), Stone("Y")))
   "have HintStones[B | W | W |  ] when compared with R | G | R | B" in {
     (codeCompare3.compareTo(Vector(Stone("R"), Stone("G"), Stone("R"), Stone("B"))) 
     should equal (Vector(HintStone("R"), HintStone("W"), HintStone("W"), HintStone("E"))))
   }
  }
  "The Code ................................................. R | R | B | Y" should{
   val codeCompare4  = new Code(Vector(Stone("R"), Stone("R"), Stone("B"), Stone("Y")))
   "have HintStones[W | W | W |  ] when compared with B | B | R | R" in {
     (codeCompare4.compareTo(Vector(Stone("B"), Stone("B"), Stone("R"), Stone("R"))) 
     should equal (Vector(HintStone("W"), HintStone("W"), HintStone("W"), HintStone("E"))))
   }
  }
  "The Code ................................................. R | R | B | Y" should{
   val codeCompare5  = new Code(Vector(Stone("R"), Stone("R"), Stone("B"), Stone("Y")))
   "have HintStones[W |   |   |  ] when compared with G | G | R | G" in {
     (codeCompare5.compareTo(Vector(Stone("G"), Stone("G"), Stone("R"), Stone("G"))) 
     should equal (Vector(HintStone("W"), HintStone("E"), HintStone("E"), HintStone("E"))))
   }
  }
}