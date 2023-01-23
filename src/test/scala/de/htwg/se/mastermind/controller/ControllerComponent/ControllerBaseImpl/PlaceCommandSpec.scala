package de.htwg.se.mastermind

package controller
package ControllerComponent
package ControllerBaseImpl

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

import model.GameComponent.GameBaseImpl.{Game, Stone, HintStone, HStone}

class PlaceCommandSpec extends AnyWordSpec {
  "A PlaceCommand" when {
    "new" should {
      val game = new Game()
      val placeCommand = new PlaceCommand(game, Vector[Stone](Stone("B"), Stone("R"), Stone("Y"), Stone("P")), Vector[HStone](HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R")), 0)
      "have a execute function that places the current vector and hints" in {
        val field = placeCommand.execute
        field.matrix.cell(0, 0) should equal(Stone("B"))
        field.matrix.cell(0, 1) should be(Stone("R"))
        field.matrix.cell(0, 2) should be(Stone("Y"))
        field.matrix.cell(0, 3) should be(Stone("P"))

        field.hmatrix.cell(0, 0) should be(HintStone("R"))
        field.hmatrix.cell(0, 1) should be(HintStone("R"))
        field.hmatrix.cell(0, 2) should be(HintStone("R"))
        field.hmatrix.cell(0, 3) should be(HintStone("R"))
      }
      "have a undoStep function that undo's the last step" in {
        val undoField = placeCommand.undoStep
        undoField.matrix.cell(0, 0) should be(Stone("E"))
        undoField.matrix.cell(0, 1) should be(Stone("E"))
        undoField.matrix.cell(0, 2) should be(Stone("E"))
        undoField.matrix.cell(0, 3) should be(Stone("E"))

        undoField.hmatrix.cell(0, 0) should be(HintStone("E"))
        undoField.hmatrix.cell(0, 1) should be(HintStone("E"))
        undoField.hmatrix.cell(0, 2) should be(HintStone("E"))
        undoField.hmatrix.cell(0, 3) should be(HintStone("E"))
      }
      "have a redoStep function that redo's the last step on the stack" in {
        val redoField = placeCommand.redoStep
        redoField.matrix.cell(0, 0) should be(Stone("B"))
        redoField.matrix.cell(0, 1) should be(Stone("R"))
        redoField.matrix.cell(0, 2) should be(Stone("Y"))
        redoField.matrix.cell(0, 3) should be(Stone("P"))

        redoField.hmatrix.cell(0, 0) should be(HintStone("R"))
        redoField.hmatrix.cell(0, 1) should be(HintStone("R"))
        redoField.hmatrix.cell(0, 2) should be(HintStone("R"))
        redoField.hmatrix.cell(0, 3) should be(HintStone("R"))
      }
    }
  }
}


