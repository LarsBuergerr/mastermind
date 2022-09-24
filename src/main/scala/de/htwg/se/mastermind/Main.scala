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
    val vector: Vector[Stone] = Vector()
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        
        if(chars.size != field.cols)
          print("Not enough values!\n")
          return None
        buildVector(vector, chars) match
          case None => None
          case Some(newvector) =>
            Some(field.put(newvector, 0))
                
      }

  
  def buildVector(vector: Vector[Stone], chars: Array[Char]): Option[Vector[Stone]] =
    val stone = chars(vector.size) match
      case 'R'|'r' => Stone.Red
      case 'G'|'g' => Stone.Green
      case 'B'|'b' => Stone.Blue
      case 'Y'|'y' => Stone.Yellow
      case 'W'|'w' => Stone.White
      case 'P'|'p' => Stone.Purple

      val newvector = vector.appended(stone)
      if (newvector.size < field.cols){
        buildVector(newvector, chars)
      }
      else
      {
        return Some(newvector)
      }

        


      

      



