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
import scala.util.{Try, Success, Failure}

//********************************************************************** CLASS DEF
case class TUI(controller: Controller) extends Observer:

  controller.add(this)

  def run(): Unit = {
    controller.request(InitStateEvent())
    println("Remaining Turns: " + controller.game.getRemainingTurns())
    inputLoop()
  }
  
  //@todo Boolean return type for testing?
  def inputLoop(): Unit = {
    
    val input = readLine(">> ")
    
    parseInput(input) match {
      case pInp: PlayerInput  =>
        println("Remaining Turns: " + controller.game.getRemainingTurns())
        inputLoop()
      case pWin: PlayerWin    =>
        print("--- Thank you for playing the game\n")
      case pLos: PlayerLose   =>
        print("--- Thank you for playing the game and see you soon\n")
      case help: Help         =>
        inputLoop()
      case menu: Menu         =>
        print("Code:" + controller.game.getCode().toString() + "\n")
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
      case 0 => {// Handles no user input -> stay in current state
        val currentRequest = controller.handleRequest(SingleCharRequest(" "))
        return controller.request(currentRequest)
      }
      case 1 => { //Handles single char user input (first with CoR, then with State Pattern)
        val currentRequest = controller.handleRequest(SingleCharRequest(input))
        currentRequest match {
          case undo: UndoStateEvent  => {
            controller.undo
            return controller.request(PlayerInputStateEvent())
          }
          case redo: RedoStateEvent  => {
            controller.redo
            return controller.request(PlayerInputStateEvent())
          }
          case _ => return controller.request(currentRequest)
        }
        
      }
      case _ => { //Handles multi char user input
        val currentRequest = controller.handleRequest(MultiCharRequest(input))
        if(currentRequest.isInstanceOf[PlayerAnalyzeEvent])
          var codeVector = Vector[Stone]()
          Try (controller.game.buildVector(emptyVector, chars)) match {
            case Success(vector) => codeVector = vector.asInstanceOf[Vector[Stone]]
            case Failure(e)      => return controller.request(controller.game.RequestHandlerSCR.DefaultInputRule(input))
          }
          val hints         = controller.game.getCode().compareTo(codeVector)
          controller.placeGuessAndHints(codeVector, hints, controller.game.getCurrentTurn())
          if hints.forall(p => p == HintStone.Black) then
            return controller.request(PlayerWinStateEvent())
          else if controller.game.getRemainingTurns().equals(0) then
            return controller.request(PlayerLoseStateEvent())
          else
            return controller.request(PlayerInputStateEvent())
        else  //Invalid input -> stay in current state
          return controller.request(currentRequest)
      }
    }
  }
  
  override def update: Unit = {
    println("Tada")
    //println(controller.update)
  }
