/**
  * TUI.scala
  * 
  * Class for the Text User Interface of the Mastermind game.
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package aview


//****************************************************************************** IMPORTS
import controller.ControllerComponent.ControllerInterface
import model.GameComponent.GameBaseImpl._
import util.Observer
import util._
import model._
import model.FileIOComponent.fileIOyamlImpl.FileIO
import scala.io.StdIn.readLine
import scala.util.{Try, Success, Failure}
import MastermindModule.{given}

//****************************************************************************** CLASS DEFINITION
class TUI(using controller: ControllerInterface) extends Observer:

  controller.add(this)

  def run(): Unit = {
    controller.request(InitStateEvent())
    inputLoop()
  }
  
  //@todo Boolean return type for testing?
  def inputLoop(): Unit = {
    
    val input = readLine(">> ")
    
    parseInput(input) match {
      case pInp: PlayerInput  =>
        inputLoop()
      case pWin: PlayerWin    =>
        print("--- Thank you for playing the game\n")
        Thread.sleep(2000)                                                      // Wait 2 seconds  @todo: reset game?
      case pLos: PlayerLose   =>
        print("--- Thank you for playing the game and see you soon\n")
        Thread.sleep(2000)                                                      // Wait 2 seconds  @todo: reset game?
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
        print(currentRequest)
          currentRequest match {
          case undo: UndoStateEvent  => {
            controller.undo
            return controller.request(PlayerInputStateEvent())
          }
          case redo: RedoStateEvent  => {
            controller.redo
            return controller.request(PlayerInputStateEvent())
          }
          case save: SaveStateEvent  => {
            val fileIO = new FileIO()
            fileIO.save(controller.game)
            return controller.request(PlayerInputStateEvent())
          }
          case load: LoadStateEvent  => {
            val fileIO = new FileIO()
            controller.game = fileIO.load(controller.game)
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
            case Failure(e)      => return controller.request(controller.game.getDefaultInputRule(input))
          }
          val hints         = controller.game.getCode().compareTo(codeVector)
          //print(hints)
          controller.placeGuessAndHints(codeVector, hints, controller.game.getCurrentTurn())
          if hints.forall(p => p.stringRepresentation.equals("R")) then
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
    //println(controller.update)
    println(controller.game.field)
    println("Remaining Turns: " + controller.game.getRemainingTurns())
  }
