/**
  * Main.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind


//********************************************************************** IMPORTS
import aview.TUI
import controller.Controller
import model.Game
import util.GameMode


//******************************************************************** CLASS DEF
@main def main: Unit = new TUI(new Controller(new Game(GameMode.selectMode))).run()