/**
  * TUI.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package aview


//********************************************************************** IMPORTS
import controller.{Controller}
import util.Observer
import util._
import model.{Code, Field, Stone, HintStone, State, Menu}
import scala.io.StdIn.readLine
import util.Event

//******************************************************************** CLASS DEF
case class TUI(controller: Controller) extends Observer:
  
  val MENU_VAL    = 4
  val PLAY_VAL    = 5
  val WIN_VAL     = 2
  val LOOSE_VAL   = 3
  val EXIT_VAL    = 1
  val ERROR_VAL   = -1
  val SUCCESS_VAL = 0
  
  val code = new Code(controller.game.field.cols)
  controller.add(this)

  def run(): Unit = {
    controller.handle(InitState())
    println("Remaining Turns: " + controller.game.getRemainingTurns())
    debugPrint_currentState() //@todo: remove after testing
    inputLoop()
  }
  
  def inputLoop(): Unit = {
    val input = readLine(">> ")
    
    parseInput(input) match {
      case SUCCESS_VAL =>
        println("Remaining Turns: " + controller.game.getRemainingTurns())
        inputLoop()
      case ERROR_VAL   =>
        inputLoop()
      case EXIT_VAL    =>
        print("Exiting...\n")
      case WIN_VAL     =>
        print("You won. Thank you for playing the game\n")
      case LOOSE_VAL   =>
        print("You lost!!!")
      case MENU_VAL    =>
        controller.handle(MenuState())
        debugPrint_currentState() //@todo: remove after testing
        inputLoop()
      case PLAY_VAL    =>
        controller.handle(PlayState())
        debugPrint_currentState() //@todo: remove after testing
        println(controller.game.field.toString())
        inputLoop()
    }
  }

  def parseInput(input: String): Int = {
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
        case 'm' | 'M' =>
          return MENU_VAL
        case 'p' | 'P' =>
          return PLAY_VAL
      }

    if(chars.size != controller.game.field.matrix.cols)
      print("Selected Code has the wrong length!\n")
      return ERROR_VAL

    val codeVector    = buildVector(emptyVector, chars)
    val hints         = code.compareTo(codeVector)

    controller.placeGuessAndHints(codeVector, hints, controller.game.getCurrentTurn())

    if hints.forall(p => p == HintStone.Black) then
      return WIN_VAL
    else if controller.game.getRemainingTurns().equals(0) then
      return LOOSE_VAL
    else
      return SUCCESS_VAL
  }
  
      
  def buildVector(vector: Vector[Stone], chars: Array[Char]): Vector[Stone] = {
    val stone = chars(vector.size) match
      case 'R'|'r'|'1' => Stone.Red
      case 'G'|'g'|'2' => Stone.Green
      case 'B'|'b'|'3' => Stone.Blue
      case 'Y'|'y'|'4' => Stone.Yellow
      case 'W'|'w'|'5' => Stone.White
      case 'P'|'p'|'6' => Stone.Purple

      val newvector = vector.appended(stone)
      if (newvector.size < controller.game.field.cols)
        buildVector(newvector, chars)
      else
        return newvector
  }
  
  
  def printHelp() = {
    println("Userinput example at Codelength 4: 'rgby' would indicate a Code with the Colors Red, Green, Blue, Yellow\n")
  }
  
  override def update: Unit = {
    println(controller.update)
  }
  
  def debugPrint_currentState() = {
    println("<<<debug>>>: " + controller.game.state.toString())
  }