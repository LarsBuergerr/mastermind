/**
  * 
  */
//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller
package ControllerComponent
package ControllerBaseImpl


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.GameComponent.GameBaseImpl.{Game, Stone, HintStone, HStone}
import model.FileIOComponent.fileIOjsonImpl.FileIO

//****************************************************************************** CLASS DEFINITION
class ControllerBaseImplSpec extends AnyWordSpec{
  val game = new Game()
  val defaultController = new Controller(using game, null)

  "A default controller" should {
    "contain a default field" in{
      defaultController.game.field.rows should be(10)
      defaultController.game.field.matrix.m(0).forall(p => p == Stone("E")) should be(true)
      defaultController.game.field.matrix.m(1).forall(p => p == Stone("E")) should be(true)
      defaultController.game.field.matrix.m(2).forall(p => p == Stone("E")) should be(true)
      defaultController.game.field.matrix.m(3).forall(p => p == Stone("E")) should be(true)

      defaultController.game.field.hmatrix.m(0).forall(p => p == HintStone("E")) should be(true)
      defaultController.game.field.hmatrix.m(1).forall(p => p == HintStone("E")) should be(true)
      defaultController.game.field.hmatrix.m(2).forall(p => p == HintStone("E")) should be(true)
      defaultController.game.field.hmatrix.m(3).forall(p => p == HintStone("E")) should be(true)
    }
  }
    "should allow to replace whole rows in both the normal matrix and the hint matrix" in {
      val guessVector = Vector[Stone](Stone("B"), Stone("R"), Stone("Y"), Stone("P"))
      val hintVector  = Vector[HStone](HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R"))
      defaultController.placeGuessAndHints(guessVector, hintVector, 0)
      defaultController.game.field.matrix.cell(0, 0) should be(Stone("B"))
      defaultController.game.field.matrix.cell(0, 1) should be(Stone("R"))
      defaultController.game.field.matrix.cell(0, 2) should be(Stone("Y"))
      defaultController.game.field.matrix.cell(0, 3) should be(Stone("P"))
    }
}