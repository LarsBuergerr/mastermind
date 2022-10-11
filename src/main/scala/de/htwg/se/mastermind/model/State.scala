package de.htwg.se.mastermind
package model

import util._
import util.TUIElements

val eol            = sys.props("line.separator")
val line           = "----------------------------------------------------------------" + eol  

trait State[T] {
  def handle(): Option[State[T]]
  override def toString(): String
}
  
class Init extends State[Event] {
  override def handle() = {
    val welcomeMessage = "------------------ Welcome to Mastermind -----------------------" + eol
    printf(eol + line + welcomeMessage + line)
    return Some(this)
  }
  
  override def toString(): String = "State: Init"
}

class Menu extends State[Event] {
  override def handle() = {
    val line           = "--- Menu: ------------------------------------------------------" + eol  
    printf(line)
    return Some(this)
  }
  
  override def toString(): String = "State: Menu"
}

class Play extends State[Event] {
  
  override def handle() = {
    val line            = "--- Play: ------------------------------------------------------" + eol
    printf(line)
    return Some(this)
  }
  
  override def toString(): String = "State: Play"
}


class Quit extends State[Event] {
  override def handle() = {
    val line            = "--- Game quit---------------------------------------------------" + eol
    printf(line)
    return Some(this)
  }
  
  override def toString(): String = "State: Quit"
}