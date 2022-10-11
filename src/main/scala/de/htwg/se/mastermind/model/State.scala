package de.htwg.se.mastermind
package model

import util._
import util.TUIElements

trait State[T] {
  def handle(): Unit
}
  
class Init extends State[Event] {
  override def handle(): Unit = {
    val eol            = sys.props("line.separator")
    val line           = "----------------------------------------------------------------" + eol  
    val welcomeMessage = "------------------ Welcome to Mastermind -----------------------" + eol
    printf(eol + line + welcomeMessage + line)
  }
  
  override def toString(): String = "State: Init"
}

class Menu extends State[Event] {
  override def handle(): Unit = {
    val eol            = sys.props("line.separator")
    val line           = "--- Menu: ------------------------------------------------------" + eol  
    printf(line)
  }
  
  override def toString(): String = "State: Menu"
}
//object StateContext extends State{
//
//  var state = init()
//
//  override def handle(event: Event) = {
//    event match{
//      case init: InitState => state = init
//      case menu: MenuState => state = menu
//    }
//    state
//  }
//  
//  def init() = {
//    val eol            = sys.props("line.separator") + this.game.toString
//    val line           = "----------------------------------------------------------------" + eol  
//    val welcomeMessage = "------------------ Welcome to Mastermind -----------------------" + eol
//    printf(eol + line + welcomeMessage + line + eol)
//  }
//  
//  def menu() =  "Menu:"
//    
//  override def toString(): String = {
//    //gamestate.get.toString()
//    "Hello World"
//  }
//}