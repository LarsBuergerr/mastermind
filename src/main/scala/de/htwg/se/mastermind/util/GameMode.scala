package de.htwg.se.mastermind

package util

import controller.Controller
import model.{Field, Stone, HintStone}
import scala.io.StdIn.readLine

object GameMode {

  var mode = parseInput()

  def easy = new Controller(new Field(12, 4, Stone.Empty, HintStone.Empty))

  def medium = new Controller(new Field(10, 4, Stone.Empty, HintStone.Empty))

  def hard = new Controller(new Field(10, 5, Stone.Empty, HintStone.Empty))

  def extrem = new Controller(new Field(8, 5, Stone.Empty, HintStone.Empty))

  def parseInput(): Controller =
   val in = readLine("Choose gamemode between [Easy, Medium, Hard, Extrem], default= Medium: ")

   in match {
     case "Easy" | "easy"        => return easy
     case "Medium" | "medium"    => return medium
     case "Hard" | "hard"        => return hard
     case "Extrem" | "extrem"    => return extrem
     case _                      => return medium
   }
}

