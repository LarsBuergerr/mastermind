package de.htwg.se.mastermind
package model

import util._

case class State() extends Stateable:

  gamestate = Some(Init(this))  

  override def handle(event: Event): Option[GameState] =
  
    event match{
      case init: InitState => gamestate = Some(Init(this))
      case menu: MenuState => gamestate = Some(Menu(this))
    }
    gamestate

  
  override def toString(): String = {
    gamestate.get.toString()
  }