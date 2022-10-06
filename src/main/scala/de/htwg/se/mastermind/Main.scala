package de.htwg.se.mastermind
import aview.TUI
import controller.Controller
import model.{Game}
import util.{GameMode}

@main def main: Unit = 
  val game = new Game(GameMode.selectMode)
  val controller = new Controller(game)
  val tui = new TUI(controller)
  tui.run()