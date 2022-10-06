/**
  * GameEvent.scala
  * Implements the STATE_PATTERN to keep track over current game state
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util


trait GameEvent

case class MenuState()        extends GameEvent
case class PlayState()        extends GameEvent
case class InitState()        extends GameEvent
case class PlayerInputState() extends GameEvent

case class EndState()  extends GameEvent

//object GameState{
//  
//  var state = introState
//  
//  def handle(event: Event) = {
//    
//    event match{
//      case intro:   IntroState => state = introState
//      
//      case menu:    MenuState => state = menuState
//      
//      case play:    PlayState => state = playState
//    }
//    state
//  }
//  
//  def introState = {
//    println("----------------------------------------------------------------")
//    println("------------------ Welcome to Mastermind -----------------------")
//    println("----------------------------------------------------------------")
//  }
//  
//  def menuState = {
//    println("----------------------------------------------------------------")
//    println("Mastermind Menu:")
//    println("- gamemode | Change Gamemode")
//    val input = readLine(">> ")
//    
//    input match {
//      case "gamemode" => 
//        GameMode
//    }
//  }
//  
//  def playState = {
//    println("Lets play a round, can you guess the code?")
//    Controller.field.toString
//  }
//  
//}