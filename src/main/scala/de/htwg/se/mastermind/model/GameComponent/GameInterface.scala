/**
  * GameInterface.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameComponent


//****************************************************************************** IMPORTS
import GameBaseImpl.{Code, Field, State, Stone}
import util.{Event, Request}


//****************************************************************************** INTERFACE DEFINITION
trait GameInterface() {

  var field: Field

  val code: Code

  var currentTurn: Int

  def state: State

  def request(event: Event): State

  def getMaxTurns(): Int
  
  def getRemainingTurns(): Int

  def getCurrentTurn(): Int
  
  def setTurn(): Int

  def undoTurn(): Int

  def getCode(): Code

  def buildVector(vector: Vector[Stone], chars: Array[Char]): Vector[Stone]

  def getCurrentStateEvent(): Event

  def handleRequest(request: Request): Event

  def getDefaultInputRule(input: String): Event
  
  def resetGame() : GameInterface

}
