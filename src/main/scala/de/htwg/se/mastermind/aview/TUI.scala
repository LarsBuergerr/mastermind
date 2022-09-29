package de.htwg.se.mastermind
package aview

import controller.{Controller}
import util.Observer
import model.{Code, Field, Stone, HintStone}
import scala.io.StdIn.readLine

case class TUI(var controller: Controller) extends Observer:
  controller.add(this)
  println(controller.field.toString())
  val code = new Code(controller.field.cols)
  val loopCount = 0
  val input = readLine(">> ")
  this.run(input, loopCount)

  def this() =
    this(new Controller)

  def run(input: String, loopCount: Int): Unit =
    val emptyVector: Vector[Stone] = Vector()
    
    val chars = input.toCharArray()

    val codeVector    = buildVector(emptyVector, chars)
    val hints         = code.compareTo(codeVector)
    val newLoopCount  = loopCount + 1

    controller.placeGuessAndHints(codeVector, hints, loopCount)


    val newInput = readLine(">> ")
    run(newInput, newLoopCount)

  
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