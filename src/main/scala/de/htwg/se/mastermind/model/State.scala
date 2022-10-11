package de.htwg.se.mastermind
package model

import util._
import util.TUIElements

val eol            = sys.props("line.separator")
val line           = "----------------------------------------------------------------" + eol  

trait State[T] {
  def handle(): Unit
  override def toString(): String
}
  
class Init extends State[Event] {
  override def handle(): Unit = {
    val welcomeMessage = "------------------ Welcome to Mastermind -----------------------" + eol
    printf(eol + line + welcomeMessage + line)
  }
  
  override def toString(): String = "State: Init"
}

class Menu extends State[Event] {
  override def handle(): Unit = {
    val line           = "--- Menu: ------------------------------------------------------" + eol  
    printf(line)
  }
  
  override def toString(): String = "State: Menu"
}

class Play extends State[Event] {
  
  override def handle(): Unit = {
    val line            = "--- Play: ------------------------------------------------------" + eol
    printf(line)
  }
  
  override def toString(): String = "State: Play"
}