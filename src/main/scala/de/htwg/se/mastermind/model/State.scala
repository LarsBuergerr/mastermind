package de.htwg.se.mastermind
package model

import util._
import scala.annotation.meta.field.apply

trait State[T] {
  def handle(): Unit
}
  
class Init extends State[Event] {
  override def handle(): Unit = {
    val row = field.rows
    println("Welcome to Mastermind")
  }
}

class Menu extends State[Event] {
  override def handle(): Unit = println("Menu:")
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