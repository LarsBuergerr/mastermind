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
import scalafx.application.Platform

//******************************************************************** CLASS DEF

object starter extends Thread {
  
  val gamemode = GameMode.selectMode
  val game = Game(gamemode)
  val controller = Controller(game)
  
  @main override def start(): Unit = 
    val tui = TUI(controller)
    val gui = GUI(controller)
    
    val threadGui = new Thread {
      override def run(): Unit = {
        gui.main(Array[String]())
      }
    }
    threadGui.setDaemon(true) //Does this help to fix the problem?
    threadGui.start()
    Thread.sleep(1000)
    tui.run()
}