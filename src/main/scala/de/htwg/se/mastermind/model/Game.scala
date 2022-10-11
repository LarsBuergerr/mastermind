package de.htwg.se.mastermind
package model

import util._

case class Game(var field: Field, var state: State[Event] = Init()){
  
  private var currentTurn: Int = 0
  private val maxTurn: Int = field.matrix.rows

  def handle(event: Event) = {
    println("<<<debug>>>: handler called")
    event match{
      case init: InitState => state = Init()
      case menu: MenuState => state = Menu()
      case play: PlayState => state = Play()
    }
    state.handle()
  }
  
  override def toString(): String = field.toString
  
  def getMaxTurns() = maxTurn
  def getRemainingTurns() = maxTurn - currentTurn
  def getCurrentTurn() = currentTurn
  def setTurn(): Int = {
    currentTurn = currentTurn + 1
    return currentTurn
  }
}