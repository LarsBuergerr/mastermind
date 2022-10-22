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
  
  controller.add(this)

  def run(): Unit = {
    controller.request(InitState())
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
      case pLos: PlayerLose   =>
        print("--- You lost!!! Anyway thanks for playing the game\n")
      case help: Help         =>
        inputLoop()
      case menu: Menu         =>
        inputLoop()
      case play: Play         =>
        println(controller.game.field.toString())
        inputLoop()
      case quit: Quit  =>
        print("--- Thanks for playing the game\n")

    }
  }

  
  def parseInput(input: String): State = {
    
    val emptyVector: Vector[Stone] = Vector()
    val chars = input.toCharArray()

    chars.size match {
      case 0 => {
        val currentRequest = controller.handleRequest(SingleCharRequest(" "))
        return controller.request(currentRequest)
      }
      case 1 => { //SingleChar user input (first with CoR, then with State Pat.)
        val currentRequest = controller.handleRequest(SingleCharRequest(input))
        return controller.request(currentRequest)
      }
      case _ => { //MultiChar user input (first with CoR, then with State Pat.)
        val currentRequest = controller.handleRequest(MultiCharRequest(input))
        
        if(currentRequest != PlayerAnalyseEvent()){
          return controller.request(currentRequest)
        } else {
          val codeVector    = buildVector(emptyVector, chars)
          val hints         = controller.game.code.compareTo(codeVector)
          controller.placeGuessAndHints(codeVector, hints, controller.game.getCurrentTurn())
          if hints.forall(p => p == HintStone.Black) then
            return controller.request(PlayerWinState())
          else if controller.game.getRemainingTurns().equals(0) then
            return controller.request(PlayerLoseState())
          else
            return controller.request(PlayerInputState())
        }
      }
    }
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
      
      //@todo add error handling when input is not valid

      val newvector = vector.appended(stone)
      if (newvector.size < controller.game.field.cols)
        buildVector(newvector, chars)
      else
        return newvector
  }
  
  override def update: Unit = {
    println(controller.update)
  }
