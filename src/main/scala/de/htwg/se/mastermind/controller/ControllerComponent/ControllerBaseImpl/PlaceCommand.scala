/**
  * PlaceCommand.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller


//****************************************************************************** IMPORTS
import model.GameComponent.GameBaseImpl.{Field, Stone, HStone, State, Game}
import model.GameComponent.GameInterface
import util.*


//****************************************************************************** CLASS DEFINITION
case class PlaceCommand(game: GameInterface, stone: Vector[Stone], hints: Vector[HStone], row: Int) extends Command():
  val oldfield = game.field
  var newfield = game.field
    
  override def execute: Field =
    newfield = game.field.placeGuessAndHints(stone, hints, row)
    game.setTurn()
    newfield
    
  override def undoStep: Field = 
    game.undoTurn()
    oldfield
    
  override def redoStep: Field = 
    if(newfield != oldfield)
      game.setTurn()
    newfield
