package de.htwg.se.mastermind
package controller

import model.{Stone, Game}
import util.GameMode
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.mastermind.model.HintStone

class ControllerSpec extends AnyWordSpec{
  "A default controller" should {
    //val defaultController = new Controller(new Game(GameMode.strategy_medium))
    "contain a default field" in{
      //defaultController.game.field.cols should be(4)
      //defaultController.game.field.rows should be(6)
      //defaultController.game.field.matrix.m(0).forall(p => p == Stone.Empty) should be(true)
      //defaultController.game.field.matrix.m(1).forall(p => p == Stone.Empty) should be(true)
      //defaultController.game.field.matrix.m(2).forall(p => p == Stone.Empty) should be(true)
      //defaultController.game.field.matrix.m(3).forall(p => p == Stone.Empty) should be(true)
//
      //defaultController.game.field.hmatrix.m(0).forall(p => p == HintStone.Empty) should be(true)
      //defaultController.game.field.hmatrix.m(1).forall(p => p == HintStone.Empty) should be(true)
      //defaultController.game.field.hmatrix.m(2).forall(p => p == HintStone.Empty) should be(true)
      //defaultController.game.field.hmatrix.m(3).forall(p => p == HintStone.Empty) should be(true)
    }
    "should allow to replace whole rows in both the normal matrix and the hint matrix" in {
      //val guessVector = Vector[Stone](Stone.Blue, Stone.Red, Stone.Yellow, Stone.Purple)
      //val hintVector  = Vector[HintStone](HintStone.Black, HintStone.Black, HintStone.Black, HintStone.Black)
      //defaultController.placeGuessAndHints(guessVector, hintVector, 0)
      //defaultController.game.field.matrix.cell(0, 0) should be(Stone.Blue)
      //defaultController.game.field.matrix.cell(0, 1) should be(Stone.Red)
      //defaultController.game.field.matrix.cell(0, 2) should be(Stone.Yellow)
      //defaultController.game.field.matrix.cell(0, 3) should be(Stone.Purple)
    }
  }
}