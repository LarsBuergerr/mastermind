/**
  * GameEvent.scala
  * Implements the STATE_PATTERN to keep track over current game state
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util


trait Event

case class MenuState()        extends Event
case class PlayState()        extends Event
case class InitState()        extends Event
case class PlayerInputState() extends Event

case class EndState()         extends Event

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