/**
  * Main.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind


//********************************************************************** IMPORTS
import aview.{TUI, GUI}
import controller.Controller
import model.Game
import util.GameMode
import scalafx.application.JFXApp3


object starter {
  def runGUI = {
    GUI().start()
  }
}
//******************************************************************** CLASS DEF
object MainGUI extends JFXApp3 {
  override def start() =
    starter.runGUI
}

