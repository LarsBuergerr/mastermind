/**
  * Main.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind


//********************************************************************** IMPORTS
import aview.{TUI, GUI}
import controller.Controller
import model.Game
import util.GameMode._
import scalafx.application.JFXApp3
import de.htwg.se.mastermind.util.GameMode



//******************************************************************** CLASS DEF

object starter {
  
  val game = Game(GameMode.strategy_medium)
  val controller = Controller(game)
  
  def runGUI = {
    GUI().start()
  }
  
  def runTUI = {
    TUI(controller).run()
  }
}

object MainTUI extends App {
  starter.runTUI
}

object MainGUI extends JFXApp3 {
  override def start() =
    starter.runGUI
}

