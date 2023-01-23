package de.htwg.se.mastermind

package model
package GameComponent
package GameBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

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
    }
  }
}