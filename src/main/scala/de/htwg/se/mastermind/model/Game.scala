/**
  * Game.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model


//********************************************************************** IMPORTS
import util._


//******************************************************************** CLASS DEF
/**
  * Represents a game instance with it's current state and game field
  *
  * @param field  mastermind game field
  * @param state  state in which the game is currently
  */
case class Game(var field: Field, var state: State = Init()){
  
  private var currentTurn: Int = 0
  private val maxTurn: Int = field.matrix.rows

  def request(event: Event): State = {
    println("<<<debug>>>: handler called")
    event match{
      case init: InitState        => state = Init()
      case menu: MenuState        => state = Menu()
      case play: PlayState        => state = Play()
      case quit: QuitState        => state = Quit()
      case help: HelpState        => state = Help()
      
      case pInp: PlayerInputState => state = PlayerInput()
      case pLos: PlayerLoseState  => state = PlayerLose()
      case pWin: PlayerWinState   => state = PlayerWin()
    }
    return state.handle()
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