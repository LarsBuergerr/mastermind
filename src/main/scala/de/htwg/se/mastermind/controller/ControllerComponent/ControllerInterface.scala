/**
  * ControllerInterface.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller
package ControllerComponent


//****************************************************************************** IMPORTS
import model.GameComponent.GameBaseImpl.{Field, Stone, HStone, State, Game}
import model.GameComponent.GameInterface
import util.{Observable, Event, Request}


//****************************************************************************** INTERFACE DEFINITION
trait ControllerInterface extends Observable {

  var game: GameInterface

  def placeGuessAndHints(stone: Vector[Stone],hints: Vector[HStone], row: Int): Field
    
  def redo: Unit
    
  def undo: Unit
    
  def reset: Field
  
  def save: Unit

  def load: Field

  def update: String
    
  def request(event: Event): State
    
  def handleRequest(request: Request): Event
    
}

