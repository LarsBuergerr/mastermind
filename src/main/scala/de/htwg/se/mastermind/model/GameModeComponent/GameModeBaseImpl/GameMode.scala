/**
  * GameMode.scala
  * 
  * Base implementation of the GameModeInterface
  * Implements the STRATEGY_PATTERN to select different game modes
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameModeComponent
package GameModeBaseImpl

//****************************************************************************** IMPORTS
import model.GameComponent.GameBaseImpl.{Field, Stone, HintStone}
import scala.io.StdIn.readLine


//****************************************************************************** OBJECT DEFINITION
object GameMode extends GameModeInterface {

  var selectMode = parseInput()

  def strategy_easy =   new Field(12, 4, Stone("E"), HintStone("E"))
  
  def strategy_medium = new Field(10, 4, Stone("E"), HintStone("E"))
  
  def strategy_hard =   new Field(10, 5, Stone("E"), HintStone("E"))
  
  def strategy_extrem = new Field(8, 5,  Stone("E"), HintStone("E"))
  
  def parseInput(): Field = {
   
   val in = readLine("Choose gamemode (Easy, Medium [default] , Hard, Extrem): ")

   in match {
     case "Easy"    | "easy"        => return strategy_easy
     case "Medium"  | "medium"      => return strategy_medium
     case "Hard"    | "hard"        => return strategy_hard
     case "Extrem"  | "extrem"      => return strategy_extrem
     case _                         => return strategy_medium
   }
  }
  
  override def toString(): String = "GameMode"
}

