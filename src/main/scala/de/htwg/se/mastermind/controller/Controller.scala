package de.htwg.se.mastermind
package controller

import model.{Field, Stone, HintStone}
import util.*

class Controller(var field: Field) extends Observable:

    def this() =
        this(new Field())
    
				
    def handle(event: Event): Unit = {
			
    }    

		
    def placeGuessAndHints(stone: Vector[Stone],hints: Vector[HintStone], row: Int): Unit =
        field = field.placeGuessAndHints(stone, hints, row)
        notifyObservers

    def update: String =
        field.toString()