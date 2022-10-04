package de.htwg.se.mastermind

package util

import scala.io.StdIn.readLine

trait Event

case class MenuState()  extends Event
case class PlayState()  extends Event
case class IntroState() extends Event

object GameState{
  
  var state = introState
  
  def handle(event: Event) = {
    
    event match{
      case intro:   IntroState => state = introState
      
      case menu:    MenuState => state = menuState
      
      case play:    PlayState => state = playState
    }
    
    //state
  }
  
  def introState = {
    println("----------------------------------------------------------------")
    println("------------------ Welcome to Mastermind -----------------------")
    println("----------------------------------------------------------------")
  }
  
  def menuState = {
    println("----------------------------------------------------------------")
    println("Mastermind Menu:")
    println("- gamemode | Change Gamemode")
    val input = readLine(">> ")
    
    input match {
      case "gamemode" => 
        GameMode
    }
  }
  
  def playState = {
    println("Lets play a round, can you guess the code?")
  }
  
}