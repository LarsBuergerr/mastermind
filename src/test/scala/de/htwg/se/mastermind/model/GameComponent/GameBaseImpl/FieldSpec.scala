/**
  * FieldSpec.scala
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
class FieldSpec extends AnyWordSpec{
  "A default Field" should {//---------------------------------------------------------------------- Test default Values
    val defaultField = new Field()
    "have a bar as String of form '+---+---+---+---+" in {
      defaultField.bar() should be("+---+---+---+---+" + eol)
    }
    "have cells as String of form '| E | E | E | E |   [ E | E | E | E ]'" in {
      defaultField.cells() should be("| E | E | E | E |   [ E | E | E | E ]" + eol)
    }
    "have a mesh in form of a Mastermind field" in {
      defaultField.mesh() should be
      ("""+---+---+---+---+
          | E | E | E | E |   [ E | E | E | E ]
          +---+---+---+---+
          | E | E | E | E |   [ E | E | E | E ]
          +---+---+---+---+
          | E | E | E | E |   [ E | E | E | E ]
          +---+---+---+---+
          | E | E | E | E |   [ E | E | E | E ]
          +---+---+---+---+
          | E | E | E | E |   [ E | E | E | E ]
          +---+---+---+---+
          | E | E | E | E |   [ E | E | E | E ]
          +---+---+---+---+""")
    }
    "have a String representation in form of the mesh" in {
      defaultField.toString() should be(defaultField.mesh())
    }
  }
  "The smallest Field" should{
    val smallField = new Field(1,1)
    "have a bar as String of form '+---+" in {
      smallField.bar() should be("+---+" + eol)
    }
    "have cells as String of form '| E |   [ E ]'" in {
      smallField.cells() should be("| E |   [ E ]" + eol)
    }
    "have a mesh in form of a Mastermind field" in {
      smallField.mesh() should be
      ("""+---+
          | E |   [ E ]
          +---+""")
    }
    "have a String representation in form of the mesh" in {
      smallField.toString() should be(smallField.mesh())
    }
  }
  "A Field" should {
    "be scalable" in {
      for(i <- 1 to 10){
        for(j <- 1 to 10)
          val stoneMatrix = new Matrix(i, j, Stone.apply("E"))
          val hintMatrix = new Matrix(i, j, HintStone.apply("E"))
          
          val scalableField = new Field(stoneMatrix, hintMatrix)
          
          scalableField.rows should be(i)
          scalableField.cols should be(j)
          scalableField.matrix should be(stoneMatrix)
          scalableField.hmatrix should be(hintMatrix)
      }
    }
  }
}