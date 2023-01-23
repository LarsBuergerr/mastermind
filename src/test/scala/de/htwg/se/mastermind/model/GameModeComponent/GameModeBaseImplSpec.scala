/**
  * GameModeBaseImplSpec.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameModeComponent
package GameModeBaseImpl


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.GameModeComponent.GameModeInterface
import model.GameModeComponent.GameModeBaseImpl.GameMode
import model.GameComponent.GameBaseImpl.{Field, Stone, HintStone}

//****************************************************************************** CLASS DEFINITION
class GameModeBaseImplSpec extends AnyWordSpec {
  print("Hello World")
  val testGameMode_easy = GameMode.strategy_easy
  "A GameMode" should {
    "have a strategy for each difficulty" in {
      testGameMode_easy shouldBe(new Field(12, 4, Stone("E"), HintStone("E")))
      //val strategy_easy   = GameMode.strategy_easy
      //val strategy_medium = GameMode.strategy_medium
      //val strategy_hard   = GameMode.strategy_hard
      //val strategy_extrem = GameMode.strategy_extrem
      //GameModeBaseImpl.GameMode.strategy_easy should not be (null)
      //GameMode.strategy_medium should not be (null)
      //GameMode.strategy_hard should not be (null)
      //GameMode.strategy_extrem should not be (null)
    }
  }
}