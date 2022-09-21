package de.htwg.se.mastermind

import model._
import scala.io.StdIn.readLine

@main def main: Unit = 

  println("Welcome to Mastermind!")
  val field = new Field(8, 5, Stone.Empty, HintStone.Empty)

  println(field.mesh(3, 8, 5))
  getInputAndPrintLoop(field)




def getInputAndPrintLoop(field: Field): Unit =
  val input = readLine
  parseInput(input) match
    case None => field
    case Some(newfield) =>
      println(newfield.mesh(3, 8, 5))
      getInputAndPrintLoop(newfield)

  def parseInput(input: String): Option[Field] =
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        val stone = chars(0) match
          case 'R' => Stone.Red
          case 'r' => Stone.Red
          case 'G' => Stone.Green
          case 'g' => Stone.Green
          case 'B' => Stone.Blue
          case 'b' => Stone.Blue
          case _   => Stone.Empty
        val x = chars(1).toString.toInt
        val y = chars(2).toString.toInt
        Some(field.put(stone, x, y))
      }