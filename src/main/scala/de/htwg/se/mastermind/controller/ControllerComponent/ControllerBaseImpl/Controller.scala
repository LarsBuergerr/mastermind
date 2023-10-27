/**
  * Controller.scala
  * Base implementation of the controller
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller
package ControllerComponent
package ControllerBaseImpl


//****************************************************************************** IMPORTS
import model.GameComponent.GameInterface
import model.GameComponent.GameBaseImpl.{State, Stone, HStone, Field, Game}
import model.FileIOComponent.FileIOInterface
import util.{Request, Event, Observable}
import de.htwg.se.mastermind.model.FileIOComponent.fileIOjsonImpl.FileIO
import play.api.libs.json.JsObject


//****************************************************************************** CLASS DEFINITION
case class Controller (var game: GameInterface, var fileIO: FileIOInterface) extends ControllerInterface:


  def this() = this(new Game(), new FileIO())

  val invoker = new Invoker
  
  // Pass on the game state to the view and the event to game
  def request(event: Event): State =
    var currState = game.request(event)
    notifyObservers
    currState
  
  def handleRequest(request: Request): Event =
    game.handleRequest(request)

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

  def save =
    fileIO.save(game)

  def load =
    game = fileIO.load(game)
    notifyObservers
    game.field

  def reset =
    game = game.resetGame()
    notifyObservers
    game.field

  def update: String =
    print("How was it possible for you to call this function?")
    ""

  def gameToJson: JsObject =
    fileIO.gameToJson(game)