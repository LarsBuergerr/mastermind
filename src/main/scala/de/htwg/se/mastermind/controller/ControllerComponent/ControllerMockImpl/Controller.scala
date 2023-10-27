/**
  * Controller.scala
  * Mock implementation of the controller
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller
package ControllerComponent
package ControllerMockImpl


//******************************************************************************* IMPORTS
import model.GameComponent.GameInterface
import model.GameComponent.GameBaseImpl.{State, Stone, HStone, Field}
import model.FileIOComponent.fileIOyamlImpl.FileIO
import util.{Request, Event, Observable}
import de.htwg.se.mastermind.model.FileIOComponent.FileIOInterface


//****************************************************************************** CLASS DEFINITION
class Controller(using var game: GameInterface, var fileIO: FileIOInterface) extends ControllerInterface:

  val invoker = new Invoker

  //temp stone vector with length of the row
  var currentStoneVector: Vector[Stone] = Vector.from[Stone](Array.fill[Stone](game.field.matrix.cols)(Stone("E")))
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
    //game.toString()
    print("How was it possible for you to call this function?")
    ""