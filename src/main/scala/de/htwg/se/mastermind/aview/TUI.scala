package de.htwg.se.mastermind
package aview

import controller.{Controller}
import util.Observer
import model.{Code, Field, Stone, HintStone}
import scala.io.StdIn.readLine

case class TUI(var controller: Controller) extends Observer:
  val WIN_VAL     = 2
  val LOOSE_VAL   = 3
  val EXIT_VAL    = 1
  val ERROR_VAL   = -1
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
      case SUCCESS_VAL =>
        run(newLoopCount)
      case ERROR_VAL   =>
        run(loopCount)
      case EXIT_VAL    =>
        print("Exiting...\n")
      case WIN_VAL     =>
        print("You won. Thank you for playing the game\n")
      case LOOSE_VAL   =>
        print("You lost!!!")
    }


  def parseInput(input: String, loopCount: Int): Int =
    val emptyVector: Vector[Stone] = Vector()
    val chars = input.toCharArray()

    if(chars.size == 0)
      print("No input!\n")
      return ERROR_VAL

    if(chars.size == 1)
      chars(0) match {
        case 'h' | 'H' =>
          printHelp()
          return ERROR_VAL
        case 'q' | 'Q' =>
          return EXIT_VAL
      }

    if(chars.size != controller.field.matrix.cols)
      print("Selected Code has the wrong length!\n")
      return ERROR_VAL

    val codeVector    = buildVector(emptyVector, chars)
    val hints         = code.compareTo(codeVector)

    controller.placeGuessAndHints(codeVector, hints, loopCount)

    if hints.forall(p => p == HintStone.Black) then
      return WIN_VAL
    else if loopCount == controller.field.matrix.rows - 1 then
      return LOOSE_VAL
    else
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

  def printHelp() =
    println("Userinput example at Codelength 4: 'rgby' would indicate a Code with the Colors Red, Green, Blue, Yellow\n")

  
  override def update: Unit = 
    println(controller.update)