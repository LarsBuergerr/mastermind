package de.htwg.se.mastermind

import model._
import scala.io.StdIn.readLine

@main def main: Unit = 

  println("Welcome to Mastermind!")
  val field = new Field(8, 5, Stone.Empty, HintStone.Empty)
  val code  = new Code(field.cols)
  val loopCount = 0

  println(field.mesh(3, 8, 5))
  getInputAndPrintLoop(field, code, loopCount)


def getInputAndPrintLoop(field: Field, code: Code, loopCount: Int): Unit =
  val input = readLine
  parseInput(input, loopCount) match
    case None =>
      getInputAndPrintLoop(field, code, loopCount)
    case Some(newfield) =>
      println(newfield.mesh(3, 8, 5))
      val newLoopCount = loopCount + 1
      getInputAndPrintLoop(newfield, code, newLoopCount)
      

  def parseInput(input: String, loopCount: Int): Option[Field] =
    val vector: Vector[Stone] = Vector()
    input match
      case "q" => None
      case _ => 
        val chars = input.toCharArray
        
        if(chars.size != field.cols)
          print("Code length not correct please insert code that matches the hidden code length\n")
          return None
        buildVector(vector, chars) match
          case None => None
          case Some(newvector) =>

            val newHints = code.compareTo(newvector)
            print(code)
            print('\n')
            Some(field.put(newvector, loopCount).placeHints(newHints, loopCount))

  
  def buildVector(vector: Vector[Stone], chars: Array[Char]): Option[Vector[Stone]] =
    val stone = chars(vector.size) match
      case 'R'|'r'|'1' => Stone.Red
      case 'G'|'g'|'2' => Stone.Green
      case 'B'|'b'|'3' => Stone.Blue
      case 'Y'|'y'|'4' => Stone.Yellow
      case 'W'|'w'|'5' => Stone.White
      case 'P'|'p'|'6' => Stone.Purple

      val newvector = vector.appended(stone)
      if (newvector.size < field.cols)
        buildVector(newvector, chars)
      else
        return Some(newvector)