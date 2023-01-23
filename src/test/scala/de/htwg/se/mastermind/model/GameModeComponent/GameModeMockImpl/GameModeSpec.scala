/**
  * GameModeSpec.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameModeComponent.GameModeMockImpl


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.GameModeComponent.GameModeInterface
import model.GameModeComponent.GameModeMockImpl.GameMode
import model.GameComponent.GameBaseImpl.{Field, Stone, HintStone}

//****************************************************************************** CLASS DEFINITION
class GameModeSpec extends AnyWordSpec {
  "A GameMode" should {
    "have a strategy for each difficulty" in {
      val strategy_easy   = GameMode.strategy_easy
      val strategy_medium = GameMode.strategy_medium
      val strategy_hard   = GameMode.strategy_hard
      val strategy_extrem = GameMode.strategy_extrem
      //GameModeBaseImpl.GameMode.strategy_easy should not be (null)
      strategy_easy       should not be (null)
      strategy_medium     should not be (null)
      strategy_hard       should not be (null)
      strategy_extrem     should not be (null)
      
      strategy_easy       should be (new Field(1, 1, Stone("E"), HintStone("E")))
      strategy_medium     should be (new Field(1, 1, Stone("E"), HintStone("E")))
      strategy_hard       should be (new Field(1, 1, Stone("E"), HintStone("E")))
      strategy_extrem     should be (new Field(1, 1, Stone("E"), HintStone("E")))
    }
    
    "have a parseInput method" in {
      val parseInput = GameMode.parseInput()
      parseInput should not be (null)
      parseInput should be (new Field(1, 1, Stone("E"), HintStone("E")))
    }
    
    "have a String representation" in {
      val string = GameMode.toString()
      string should not be (null)
      string should be ("GameMode")
    }
    
    "should only be able to be created once" in {
      val gameMode = GameMode
      gameMode should not be (null)
      gameMode should be (GameMode)
    }
    
    "should be a instance of GameModeInterface" in {
      val gameMode = GameMode
      gameMode should not be (null)
      gameMode should be (a [GameModeInterface])
    }
  }
}