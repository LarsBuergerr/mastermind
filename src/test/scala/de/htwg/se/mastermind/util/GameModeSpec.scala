/**
  * GameModeSpec.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util

//********************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class GameModeSpec extends AnyWordSpec {
  
    "A GameMode" should {
        "have a strategy for each difficulty" in {
          val strategy_easy = GameMode.strategy_easy
            //GameMode.strategy_easy should not be (null)
            //GameMode.strategy_medium should not be (null)
            //GameMode.strategy_hard should not be (null)
            //GameMode.strategy_extrem should not be (null)
        }
    }
}