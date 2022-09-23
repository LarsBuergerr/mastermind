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
    var vector: Vector[Stone] = Vector()
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray

        for (i <- 0 to (chars.size-1))
          val stone = chars(i) match
            case 'R'|'r' => vector = vector.appended(Stone.Red)
            case 'G'|'g' => vector = vector.appended(Stone.Green)
            case 'B'|'b' => vector = vector.appended(Stone.Blue)
            case 'Y'|'y' => vector = vector.appended(Stone.Yellow)
            case 'W'|'w' => vector = vector.appended(Stone.White)
            case 'P'|'p' => vector = vector.appended(Stone.Purple)
        
        print(vector)
        Some(field.put(vector, 0))
      }

