/**
  * Controller.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller


//********************************************************************** IMPORTS
import model.{Field, Stone, HStone, HintStone, State, Game}
import util.*


//******************************************************************** CLASS DEF
class Controller(var game: Game) extends Observable:


  val invoker = new Invoker
  // Pass on the game state to the view and the event to game
  def request(event: Event): State = {
    var currState = game.request(event)
    //notifyObservers
    currState
  }
  
  def handleRequest(request: Request): Event = {
    game.handleRequest(request)
  }


  def placeGuessAndHints(stone: Vector[Stone],hints: Vector[HStone], row: Int): Field =
    game.field = invoker.doStep(PlaceCommand(game, stone, hints, row))
    notifyObservers
    game.field

  def redo =
    game.field = invoker.redoStep.getOrElse(game.field)
    notifyObservers

  def undo =
    game.field = invoker.undoStep.getOrElse(game.field)
    notifyObservers


  def update: String = {
    //game.toString()
    print("How was it possible for you to call this function?")
    ""
  }