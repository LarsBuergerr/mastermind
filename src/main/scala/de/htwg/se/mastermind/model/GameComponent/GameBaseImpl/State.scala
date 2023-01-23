/**
  * State.scala
  * Implements the STATE_PATTERN to keep track over current game state
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameComponent
package GameBaseImpl


//****************************************************************************** IMPORTS
import util._


//****************************************************************************** INTERFACE DEFINITION
trait State {
  def handle(): State
  override def toString(): String

  override def equals(obj: Any): Boolean = {
    obj match {
      case that: State => this.toString == that.toString
      case _ => false
    }
  }
}


//****************************************************************************** CLASS DEFINITIONS
val horizontalLine = "----------------------------------------------------------------" + eol
 
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
    val line      = "--- Help: [Input] : Function-----------------------------------" + eol
    val linePlay  = "---       [p    ] : starts the game" + eol
    val lineMenu  = "---       [m    ] : opens the menu" + eol
    val lineQuit  = "---       [q    ] : quits the game" + eol
    val lineHelp  = "---       [h    ] : shows this help" + eol
    val lineGame  = "---               : Select Stone ------------------------------" + eol
    val redLine   = "---       [R/r/1] : red" + eol 
    val greenLine = "---       [G/g/2] : green" + eol
    val blueLine  = "---       [B/b/3] : blue" + eol
    val yellowLine= "---       [Y/y/4] : yellow" + eol
    val whiteLine = "---       [W/w/5] : white" + eol
    val purpleLine= "---       [P/p/6] : purple" + eol
    val lineInput = "---       [Enter] : Enters input" + eol
    printf(line + linePlay + lineMenu + lineQuit + lineHelp + lineGame + redLine + greenLine + blueLine + yellowLine + whiteLine + purpleLine + lineInput + horizontalLine)
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

class PlayerAnalyze extends State {
  override def handle(): State = {
    val line      = "--- Player analyzes ---------------------------------------------" + eol
    printf(line)
    return this
  }
  override def toString(): String = "State: PlayerAnalyzes"
}