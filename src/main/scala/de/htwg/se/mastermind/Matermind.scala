/**
  * ███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗ ███╗   ███╗██╗███╗   ██╗██████╗ 
  * ████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██╔══██╗
  * ██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝██╔████╔██║██║██╔██╗ ██║██║  ██║
  * ██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗██║╚██╔╝██║██║██║╚██╗██║██║  ██║
  * ██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██████╔╝
  * ╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝ 
  *                                                                                  
  * Mastermind.scala
  * Created by: LarsBuergerr & Smokey95
  * 
  * Project is part of the course "Software Engineering" at HTWG Konstanz
  * 
  * This is the main class of the Mastermind game.
  * 
  * © LarsBuergerr & Smokey95
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind


//****************************************************************************** IMPORTS
import aview.{TUI, GUI}
import controller.ControllerComponent.ControllerBaseImpl.Controller
import model.GameComponent.GameBaseImpl.Game
import util.GameModeComponent.GameModeBaseImpl.GameMode


//****************************************************************************** MAIN
object mastermind extends Thread {
  
  val controller = Controller(Game(GameMode.selectMode))
  
  @main override def start(): Unit = 
    val tui = TUI(controller)
    val gui = GUI(controller)
    
    val threadGui = new Thread {
      override def run(): Unit = {
        gui.main(Array[String]())
      }
    }

    threadGui.start()
    Thread.sleep(1000)
    tui.run()
}