/**
  * GameMode.scala
  * 
  * Mock implementation of the GameModeInterface
  * Implements the STRATEGY_PATTERN to select different game modes
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameModeComponent
package GameModeMockImpl

//****************************************************************************** IMPORTS
import model.GameComponent.GameBaseImpl.{Field, Stone, HintStone}
import scala.io.StdIn.readLine


//****************************************************************************** OBJECT DEFINITION
object GameMode extends GameModeInterface{

  var selectMode = parseInput()

  def strategy_easy =   new Field(1, 1, Stone("E"), HintStone("E"))
  
  def strategy_medium = strategy_easy
  
  def strategy_hard =   strategy_easy
  
  def strategy_extrem = strategy_easy
  
  def parseInput(): Field = {
   
   val in = print("You are in mock mode so you have no choice (Muhahaha): ")

   return strategy_medium
  }
  
  override def toString(): String = "GameMode"
}

