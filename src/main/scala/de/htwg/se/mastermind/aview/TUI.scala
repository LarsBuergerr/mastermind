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

//********************************************************************** CLASS DEF
case class TUI(controller: Controller) extends Observer:
  
  val code = new Code(controller.game.field.cols)
  controller.add(this)

  def run(): Unit = {
    controller.request(InitStateEvent())
    println("Remaining Turns: " + controller.game.getRemainingTurns())
    //debugPrint_currentState()                                                 //@todo: remove after testing
    inputLoop()
  }
  
  def inputLoop(): Unit = {
    val input = readLine(">> ")
    
    parseInput(input) match {
      case pInp: PlayerInput  =>
        println("Remaining Turns: " + controller.game.getRemainingTurns())
        inputLoop()
      case pWin: PlayerWin    =>
        print("--- You won. Thank you for playing the game\n")
      case pLos: PlayerLose   =>
        print("--- You lost!!! Anyway thanks for playing the game\n")
      case help: Help         =>
        inputLoop()
      case menu: Menu         =>
        print("Code:" + code.toString() + "\n")
        inputLoop()
      case play: Play         =>
        println(controller.game.field.toString())
        inputLoop()    
      case quit: Quit         =>
        print("--- See you later alligator...\n")
    }
  }

  def parseInput(input: String): State = {
    val emptyVector: Vector[Stone] = Vector()
    val chars = input.toCharArray()

    chars.size match {
      case 0 => {
        // Handles no user input -> stay in current state
        val currentRequest = controller.handleRequest(SingleCharRequest(" "))
        return controller.request(currentRequest)
      }
      
      case 1 => {
        //Handles single char user input (first with CoR, then with State Pattern)
        val currentRequest = controller.handleRequest(SingleCharRequest(input))
        return controller.request(currentRequest)
      }
      
      case _ => {
        //Handles multi char user input (first with CoR, then with State Pattern)
        val currentRequest = controller.handleRequest(MultiCharRequest(input))
        if(currentRequest.isInstanceOf[PlayerAnalyzeEvent])
          val codeVector    = controller.game.buildVector(emptyVector, chars)
          if(controller.game.checkVector(codeVector))
            return controller.request(controller.game.RequestHandlerSCR.DefaultInputRule(input))
          val hints         = code.compareTo(codeVector)
          controller.placeGuessAndHints(codeVector, hints, controller.game.getCurrentTurn())
          if hints.forall(p => p == HintStone.Black) then
            return controller.request(PlayerWinStateEvent())
          else if controller.game.getRemainingTurns().equals(0) then
            return controller.request(PlayerLoseStateEvent())
          else
            return controller.request(PlayerInputStateEvent())
        else
          //Invalid input -> stay in current state
          return controller.request(currentRequest)
      }
    }
  }
  
  override def update: Unit = {
    println(controller.update)
  }
  
  def debugPrint_currentState() = {
    println("<<<debug>>>: " + controller.game.state.toString())
  }