/**
  * Controller.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller


//********************************************************************** IMPORTS
import model.{Field, Stone, HintStone, State, Game}
import util.*


//******************************************************************** CLASS DEF
class Controller(var game: Game) extends Observable:

  // Pass on the game state to the view and the event to game
  def request(event: Event): State = {
    game.request(event)
  }
  
  def handleRequest(request: Request): Response = {
    game.handleRequest(request)
  }

  def placeGuessAndHints(stone: Vector[Stone],hints: Vector[HintStone], row: Int): Unit =
    game.field = game.field.placeGuessAndHints(stone, hints, row)
    game.setTurn()
    notifyObservers

  def update: String = {
    game.toString()
  }