/**
  * PlaceCommand.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package controller


//********************************************************************** IMPORTS
import model.{Field, Stone, HStone, HintStone, State, Game}
import util.*


//******************************************************************** CLASS DEF
case class PlaceCommand(game: Game, stone: Vector[Stone], hints: Vector[HStone], row: Int) extends Command():
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
        if(newfield != oldfield){
            game.setTurn()
        }
        newfield
