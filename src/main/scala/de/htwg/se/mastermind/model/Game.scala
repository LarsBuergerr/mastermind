package de.htwg.se.mastermind
package model

import util._

case class Game(var field: Field, var state: State[Event] = Init()){
  
  var turn: Int = 0
  
  //def handle(event: Event) = {
  //  StateContext.handle(event)
  //}
  
  //def handle(event: Event) = {
  //  state.handle(event)
  //}

  def handle(event: Event) = {
    println("---debug: handler called")
    event match{
      case init: InitState => state = Init()
      case menu: MenuState => state = Menu()
    }
    state.handle()
  }
  //
  //def init = {
  //  val eol            = sys.props("line.separator")
  //  val line           = "----------------------------------------------------------------" + eol  
  //  val welcomeMessage = "------------------ Welcome to Mastermind -----------------------" + eol
  //  printf(eol + line + welcomeMessage + line + eol)
  //}
  //
  //def menu = {
  //  println("Menu:")
  //}
  
  override def toString(): String = field.toString
}