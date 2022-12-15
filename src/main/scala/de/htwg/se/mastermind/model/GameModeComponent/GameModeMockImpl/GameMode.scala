/**
  * GameMode.scala
  * Implements the STRATEGY_PATTERN to select different game modes
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameModeComponent
package GameModeMockImpl

//****************************************************************************** IMPORTS
import model.GameComponent.GameBaseImpl.{Field, Stone, HintStone}
import GameModeComponent.GameModeInterface
import scala.io.StdIn.readLine


//****************************************************************************** OBJECT DEFINITION
object GameMode extends GameModeInterface{

  var selectMode = parseInput()

  def strategy_easy =   new Field(1, 1, Stone("E"), HintStone("E"))
  
  def strategy_medium = new Field(1, 1, Stone("E"), HintStone("E"))
  
  def strategy_hard =   new Field(1, 1, Stone("E"), HintStone("E"))
  
  def strategy_extrem = new Field(1, 1,  Stone("E"), HintStone("E"))
  
  def parseInput(): Field = {
   
   val in = readLine("You are in mock mode so you have no choice (Muhahaha): ")

   in match {
     case "Easy" | "easy"        => return strategy_easy
     case "Medium" | "medium"    => return strategy_medium
     case "Hard" | "hard"        => return strategy_hard
     case "Extrem" | "extrem"    => return strategy_extrem
     case _                      => return strategy_medium
   }
  }
  
  override def toString(): String = "GameMode"
}

