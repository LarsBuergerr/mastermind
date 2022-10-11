package de.htwg.se.mastermind
package model

import util._

case class Game(var field: Field, var state: State[Event] = Init()){
  
  var turn: Int = 0

  def handle(event: Event) = {
    println("<<<debug>>>: handler called\n")
    event match{
      case init: InitState => state = Init()
      case menu: MenuState => state = Menu()
    }
    state.handle()
  }
  
  override def toString(): String = field.toString
}