package de.htwg.se.mastermind
package controller

import model.{Field, Stone, HintStone, State, Game}
import util.*

class Controller(var game: Game) extends Observable:

  def handle(event: Event) = {
    game.handle(event)
  }

  def placeGuessAndHints(stone: Vector[Stone],hints: Vector[HintStone], row: Int): Unit =
    game.field = game.field.placeGuessAndHints(stone, hints, row)
    game.setTurn()
    notifyObservers

  def update: String = {
    game.toString()
  }