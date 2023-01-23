package de.htwg.se.mastermind

package model
package GameComponent
package GameBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import util._

class GameSpec extends AnyWordSpec {
  "A game is the playing field for the mastermind game." when {
    "new" should {
      val game = new Game()
      "have a grid with 10 rows and 4 columns" in {
        game.field.matrix.m.size should be(10)
        game.field.matrix.m(0).length should be(4)
      }
      "should have the Init State" in {
        game.state should be(Init())
      }
      "should have a maxTurn of 10" in {
        game.getMaxTurns() should be(10)
      }
      "should have a request function that return the current state" in {
        game.handleRequest(SingleCharRequest("h")) should be(HelpStateEvent())
        game.handleRequest(SingleCharRequest("m")) should be(MenuStateEvent())
        game.handleRequest(SingleCharRequest("p")) should be(PlayStateEvent())
        game.handleRequest(SingleCharRequest("q")) should be(QuitStateEvent())
        game.handleRequest(SingleCharRequest("u")) should be(UndoStateEvent())
        game.handleRequest(SingleCharRequest("r")) should be(RedoStateEvent())
        game.handleRequest(SingleCharRequest("s")) should be(SaveStateEvent())
        game.handleRequest(SingleCharRequest("l")) should be(LoadStateEvent())

        game.handleRequest(MultiCharRequest("1234")) should be(PlayerAnalyzeEvent())
      }
      "should have setter and getter for Current Turn, Turn and Code" in {
        game.getCurrentTurn() should be(0)
        game.setTurn() should be (1)
        game.undoTurn() should be(0)

        game.getRemainingTurns() should be(10)
        
        val curr_code = game.getCode()
        curr_code.size should be(4)
      }
      "should a a reset game function that resets the current game" in {
        val resetted_game = game.resetGame()
        resetted_game shouldBe a [Game]
      }
    }
  }
}