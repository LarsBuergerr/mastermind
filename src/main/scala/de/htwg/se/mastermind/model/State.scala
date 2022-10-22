/**
  * State.scala
  * Implements the STATE_PATTERN to keep track over current game state
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model


//********************************************************************** IMPORTS
import util._


//******************************************************************** CLASS DEF
val horizontalLine = "----------------------------------------------------------------" + eol

trait State {
  def handle(): State
  override def toString(): String
}
 
 
class Init extends State {
  override def handle(): State = {
    val welcomeMessage = "------------------ Welcome to Mastermind -----------------------" + eol
    printf(eol + horizontalLine + welcomeMessage + horizontalLine)
    return this
  }
  override def toString(): String = "State: Init"
}


class Menu extends State {
  override def handle(): State = {
    val line           = "--- Menu: ------------------------------------------------------" + eol  
    printf(line)
    return this
  }
  override def toString(): String = "State: Menu"
}


class Play extends State {
  override def handle(): State = {
    val line      = "--- Play: ------------------------------------------------------" + eol
    printf(line)
    return this
  }
  override def toString(): String = "State: Play"
}


class Help extends State {
  override def handle(): State = {
    val line      = "--- Help: [Input] : Function ----------------------------------" + eol
    val lineMenu  = "---       [m | M] : starts the menu" + eol
    val linePlay  = "---       [p | P] : starts the game" + eol
    val lineEnd   = "---       [q | Q] : quits the game" + eol
    printf(line + lineMenu + linePlay + lineEnd + horizontalLine)
    return this    
  }
  override def toString(): String = "State: Help"
}


class Quit extends State {
  override def handle(): State = {
    val line      = "--- Game quit---------------------------------------------------" + eol
    printf(line)
    return this
  }
  override def toString(): String = "State: Quit"
}


class PlayerInput extends State {
  override def handle(): State = {
    return this
  }
  override def toString(): String = "State: PlayerInput"
}


class PlayerAnalyseState() extends State {
  override def handle(): State = {
    return this
  }
  override def toString(): String = "State: PlayerAnalysis"
}


class PlayerLose extends State {
  override def handle(): State = {
    val line      = "--- You lost ---------------------------------------------------" + eol
    printf(line)
    return this
  }
  override def toString(): String = "State: PlayerLose"
}


class PlayerWin extends State {
  override def handle(): State = {
    val line      = "--- You won ----------------------------------------------------" + eol
    printf(line)
    return this
  }
  override def toString(): String = "State: PlayerWin"
}