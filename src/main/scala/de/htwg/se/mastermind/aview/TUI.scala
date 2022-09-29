package de.htwg.se.mastermind
package aview

import controller.{Controller}
import util.Observer
import model.{Code, Field, Stone, HintStone}
import scala.io.StdIn.readLine

case class TUI(var controller: Controller) extends Observer:
  val SUCCESS_VAL = 0
  val loopCount = 0
  val code = new Code(controller.field.cols)

  controller.add(this)
  println(controller.field.toString())

  def this() =
    this(new Controller)


  def run(loopCount: Int): Unit =
    val newLoopCount = loopCount + 1
    val input = readLine(">> ")
    parseInput(input, loopCount) match {
      case SUCCESS_VAL => {
        run(newLoopCount)
      }
    }


  def parseInput(input: String, loopCount: Int): Int =
    val emptyVector: Vector[Stone] = Vector()
    val chars = input.toCharArray()

    val codeVector    = buildVector(emptyVector, chars)
    val hints         = code.compareTo(codeVector)
    controller.placeGuessAndHints(codeVector, hints, loopCount)
    return SUCCESS_VAL

  def buildVector(vector: Vector[Stone], chars: Array[Char]): Vector[Stone] =
    val stone = chars(vector.size) match
      case 'R'|'r'|'1' => Stone.Red
      case 'G'|'g'|'2' => Stone.Green
      case 'B'|'b'|'3' => Stone.Blue
      case 'Y'|'y'|'4' => Stone.Yellow
      case 'W'|'w'|'5' => Stone.White
      case 'P'|'p'|'6' => Stone.Purple

      val newvector = vector.appended(stone)
      if (newvector.size < controller.field.cols)
        buildVector(newvector, chars)
      else
        return newvector

  override def update: Unit = 
    println(controller.update)