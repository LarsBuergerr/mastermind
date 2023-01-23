/**
  * GameModeSpec.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameModeComponent.GameModeBaseImpl


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.GameModeComponent.GameModeInterface
import model.GameModeComponent.GameModeBaseImpl.GameMode
import model.GameComponent.GameBaseImpl.{Field, Stone, HintStone}
import java.io.ByteArrayInputStream
import java.io.StringReader

//****************************************************************************** CLASS DEFINITION
class GameModeSpec extends AnyWordSpec {
  
  "A GameMode" should {
    val string_easy   = "easy"
    val string_medium = "medium"
    val string_hard   = "hard"
    val string_extrem = "extrem"
    val string_default = " "
    
    val inputStreamEasy     = new StringReader(string_easy.stripMargin)
    val inputStreamMedium   = new StringReader(string_medium.stripMargin)
    val inputStreamHard     = new StringReader(string_hard.stripMargin)
    val inputStreamExtrem   = new StringReader(string_extrem.stripMargin)
    val inputStreamDefault  = new StringReader(string_default.stripMargin)
    
    "have a parseInput method" in {
      Console.withIn(inputStreamMedium){
        GameMode.parseInput() should be (new Field(10, 4, Stone("E"), HintStone("E")))
      }
      Console.withIn(inputStreamEasy){
        GameMode.parseInput() should be (new Field(12, 4, Stone("E"), HintStone("E")))
      }   
      Console.withIn(inputStreamHard){
        GameMode.parseInput() should be (new Field(10, 5, Stone("E"), HintStone("E")))
      }
      Console.withIn(inputStreamExtrem){
        GameMode.parseInput() should be (new Field(8, 5, Stone("E"), HintStone("E")))
      }
      Console.withIn(inputStreamDefault){
        GameMode.parseInput() should be (new Field(10, 4, Stone("E"), HintStone("E")))
      }
    }
    
    "have a strategy for each difficulty" in {
      val strategy_easy   = GameMode.strategy_easy
      val strategy_medium = GameMode.strategy_medium
      val strategy_hard   = GameMode.strategy_hard
      val strategy_extrem = GameMode.strategy_extrem
      
      strategy_easy       should not be (null)
      strategy_medium     should not be (null)
      strategy_hard       should not be (null)
      strategy_extrem     should not be (null)
      
      strategy_easy       should be (new Field(12, 4, Stone("E"), HintStone("E")))
      strategy_medium     should be (new Field(10, 4, Stone("E"), HintStone("E")))
      strategy_hard       should be (new Field(10, 5, Stone("E"), HintStone("E")))
      strategy_extrem     should be (new Field(8,  5, Stone("E"), HintStone("E")))

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