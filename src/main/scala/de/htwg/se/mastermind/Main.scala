package de.htwg.se.mastermind
import aview.TUI
import model.GameMode

@main def main: Unit = 
  print("Welcome to Mastermind\n\n")
  val mode = GameMode.mode
  val tui = new TUI(mode)
  tui.run(0)