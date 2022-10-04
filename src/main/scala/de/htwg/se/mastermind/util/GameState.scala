package de.htwg.se.mastermind

package util

trait Event

case class MenuState() extends Event
case class PlayState() extends Event

object GameState{
  
  var state = menuState
  
  def handle(event: Event) = {
    event match{
      case menu: MenuState => state = menuState
      
      case play: PlayState => state = playState
    }
    state
  }
  
  def menuState = {
    println("Welcome to menu")
  }
  
  def playState = {
    println("Lets play a round, can you guess the code?")
  }
}