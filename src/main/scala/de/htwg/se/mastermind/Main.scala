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

object starter extends Thread {
  
  val game = Game(GameMode.strategy_medium)
  val controller = Controller(game)
  
  @main override def start(): Unit = 
    val tui = TUI(controller)
    val gui = GUI(controller)
    
    val threadGui = new Thread {
      override def run(): Unit = {
        gui.main(Array[String]())
      }
    }
    threadGui.start()
    tui.run()
}

// object MainTUI extends App {
//   starter.runTUI
// }

