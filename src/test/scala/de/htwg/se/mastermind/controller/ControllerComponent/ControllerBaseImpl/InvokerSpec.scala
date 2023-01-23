package de.htwg.se.mastermind

package controller
package ControllerComponent
package ControllerBaseImpl

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

import model.GameComponent.GameBaseImpl.{Game, Stone, HintStone, HStone, Field}

class InvokerSpec extends AnyWordSpec {
  "A Invoker" when {
    "new" should {
      val invoker = new Invoker()
      val game = new Game()
      val placeCommand = new PlaceCommand(game, Vector[Stone](Stone("B"), Stone("R"), Stone("Y"), Stone("P")), Vector[HStone](HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R")), 0)
      "have a doStep function that executes the last command" in {
        val field = invoker.doStep(placeCommand)
        field.matrix.cell(0, 0) should equal(Stone("B"))
        field.matrix.cell(0, 1) should be(Stone("R"))
        field.matrix.cell(0, 2) should be(Stone("Y"))
        field.matrix.cell(0, 3) should be(Stone("P"))

        field.hmatrix.cell(0, 0) should be(HintStone("R"))
        field.hmatrix.cell(0, 1) should be(HintStone("R"))
        field.hmatrix.cell(0, 2) should be(HintStone("R"))
        field.hmatrix.cell(0, 3) should be(HintStone("R"))
      }
    }
  }
}

