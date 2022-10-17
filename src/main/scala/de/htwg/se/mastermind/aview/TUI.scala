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
import model._
import scala.io.StdIn.readLine
import util.Event

//******************************************************************** CLASS DEF
case class TUI(controller: Controller) extends Observer:
  
  val code = new Code(controller.game.field.cols)
  controller.add(this)

  def run(): Unit = {
    controller.request(InitState())
    println("Remaining Turns: " + controller.game.getRemainingTurns())
    inputLoop()
  }
  
  //@todo Boolean return type?
  def inputLoop(): Unit = {
    
    val input = readLine(">> ")
    
    controller.handleRequest(UserInputRequest(input))
    
    //if(controller.game.state.isInstanceOf[Init]) {
    //  println("That worked!")
    //  controller.handleRequest(SingleCharRequest(input))
    //} else  {
    //  println("That didn't work!")
    //}
    
    
    //controller.handleRequest(MenuInput(input))
    //parseInput(input) match {
    //  case pInp: PlayerInput  =>
    //    println("Remaining Turns: " + controller.game.getRemainingTurns())
    //    inputLoop()
    //  case pWin: PlayerWin    =>
    //    print("--- You won. Thank you for playing the game\n")
    //  case pLos: PlayerLose   =>
    //    print("--- You lost!!! Anyway thanks for playing the game\n")
    //  case help: Help   =>
    //    inputLoop()
    //  case menu: Menu    =>
    //    //debugPrint_currentState()                                             //@todo: remove after testing
    //    inputLoop()
    //  case play: Play    =>
    //    //debugPrint_currentState()                                             //@todo: remove after testing
    //    println(controller.game.field.toString())
    //    inputLoop()
    //  case quit: Quit  =>
    //    print("--- See you later alligator...\n")
//
    //}
  }

  
  def parseInput(input: String): State = {
    
    val emptyVector: Vector[Stone] = Vector()
    val chars = input.toCharArray()

    if(chars.size.equals(0))
      print("No input!\n")
      return controller.request(QuitState())                                    //@todo what to to instead?

    if(chars.size.equals(1))
      chars(0) match {
        case 'h' | 'H' =>
          return controller.request(HelpState())
        case 'q' | 'Q' =>
          return controller.request(QuitState())
        case 'm' | 'M'  =>
          return controller.request(MenuState())
        case 'p' | 'P' =>
          return controller.request(PlayState())
      }

    if(chars.size != controller.game.field.matrix.cols)
      print("Selected Code has the wrong length!\n")
      return controller.request(QuitState())                                    //@todo what to to instead?
      
    val codeVector    = buildVector(emptyVector, chars)
    val hints         = code.compareTo(codeVector)

    controller.placeGuessAndHints(codeVector, hints, controller.game.getCurrentTurn())

    if hints.forall(p => p == HintStone.Black) then
      return controller.request(PlayerWinState())
    else if controller.game.getRemainingTurns().equals(0) then
      return controller.request(PlayerLoseState())
    else
      return controller.request(PlayerInputState())
  }
  
  
  //@todo: move declaration cause not TUI "only"   
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
  
  override def update: Unit = {
    println(controller.update)
  }
  
  def debugPrint_currentState() = {
    println("<<<debug>>>: " + controller.game.state.toString())
  }