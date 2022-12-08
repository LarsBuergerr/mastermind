/**
  * FieldSpec.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model

//********************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


//class FieldSpec extends AnyWordSpec{
//  "A default Field" should {//---------------------------------------------------------------------- Test default Values
//    val defaultField = new Field()
//    "have a bar as String of form '+---+---+---+---+" in {
//      defaultField.bar() should be("+---+---+---+---+" + eol)
//    }
//    "have cells as String of form '|   |   |   |   |   [   |   |   |   ]'" in {
//      defaultField.cells() should be("|   |   |   |   |   [   |   |   |   ]" + eol)
//    }
//    "have a mesh in form of a Mastermind field" in {
//      defaultField.mesh() should be
//      ("""+---+---+---+---+
//          |   |   |   |   |   [   |   |   |   ]
//          +---+---+---+---+
//          |   |   |   |   |   [   |   |   |   ]
//          +---+---+---+---+
//          |   |   |   |   |   [   |   |   |   ]
//          +---+---+---+---+
//          |   |   |   |   |   [   |   |   |   ]
//          +---+---+---+---+
//          |   |   |   |   |   [   |   |   |   ]
//          +---+---+---+---+
//          |   |   |   |   |   [   |   |   |   ]
//          +---+---+---+---+""")
//    }
//    "have a String representation in form of the mesh" in {
//      defaultField.toString() should be(defaultField.mesh())
//    }
//  }
//  "The smallest Field" should{
//    val smallField = new Field(1,1)
//    "have a bar as String of form '+---+" in {
//      smallField.bar() should be("+---+" + eol)
//    }
//    "have cells as String of form '|   |   [   ]'" in {
//      smallField.cells() should be("|   |   [   ]" + eol)
//    }
//    "have a mesh in form of a Mastermind field" in {
//      smallField.mesh() should be
//      ("""+---+
//          |   |   [   ]
//          +---+""")
//    }
//    "have a String representation in form of the mesh" in {
//      smallField.toString() should be(smallField.mesh())
//    }
//  }
//  "A Field" should {
//    "be scalable" in {
//      for(i <- 1 to 10){
//        for(j <- 1 to 10)
//          val stoneMatrix = new Matrix(i, j, Stone.Empty)
//          val hintMatrix = new Matrix(i, j, HintStone.Empty)
//          
//          val scalableField = new Field(stoneMatrix, hintMatrix)
//          
//          scalableField.rows should be(i)
//          scalableField.cols should be(j)
//          scalableField.matrix should be(stoneMatrix)
//          scalableField.hmatrix should be(hintMatrix)
//      }
//    }
//  }
//}