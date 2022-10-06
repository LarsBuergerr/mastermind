package de.htwg.se.mastermind
package controller

import model.{Field, Stone, HintStone, State}
import util.*

class Controller(var field: Field) extends Observable:
  val state = new State()

  def this() =
    this(new Field())


  def handle(event: Event)= {
    state.handle(event)
    print(state.toString())
  }

  def placeGuessAndHints(stone: Vector[Stone],hints: Vector[HintStone], row: Int): Unit =
    field = field.placeGuessAndHints(stone, hints, row)
    notifyObservers

  def update: String =
    field.toString()