/**
  * GameModeSpec.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.mastermind.model.GameModeComponent.GameModeBaseImpl.GameMode
import de.htwg.se.mastermind.model.GameComponent.GameBaseImpl.Field

//****************************************************************************** CLASS DEFINITION
class GameModeSpec extends AnyWordSpec {
 
   "A GameMode" should {
       "have a strategy for each difficulty" in {
         GameMode.strategy_easy shouldBe a [Field]
           //GameMode.strategy_easy should not be (null)
           //GameMode.strategy_medium should not be (null)
           //GameMode.strategy_hard should not be (null)
           //GameMode.strategy_extrem should not be (null)
       }
   }
}