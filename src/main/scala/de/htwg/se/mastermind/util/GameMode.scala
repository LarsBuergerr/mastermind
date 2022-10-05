/**
  * GameMode.scala
  * Implements the STRATEGY_PATTERN to select different game modes
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util

//********************************************************************** IMPORTS
import controller.Controller
import model.{Field, Stone, HintStone}
import scala.io.StdIn.readLine


object GameMode {

  var selectMode = parseInput()

  def strategy_easy =   new Controller(new Field(12, 4, Stone.Empty, HintStone.Empty))
  def strategy_medium = new Controller(new Field(10, 4, Stone.Empty, HintStone.Empty))
  def strategy_hard =   new Controller(new Field(10, 5, Stone.Empty, HintStone.Empty))
  def strategy_extrem = new Controller(new Field(8, 5, Stone.Empty, HintStone.Empty))

  def parseInput(): Controller =
   
   val in = readLine("Choose gamemode (Easy, Medium [default] , Hard, Extrem): ")

   in match {
     case "Easy" | "easy"        => return strategy_easy
     case "Medium" | "medium"    => return strategy_medium
     case "Hard" | "hard"        => return strategy_hard
     case "Extrem" | "extrem"    => return strategy_extrem
     case _                      => return strategy_medium
   }
}