/**
  * Game.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameComponent
package GameMockImpl


//****************************************************************************** IMPORTS
import util._
import model._
import model.GameComponent.GameInterface
import model.GameComponent.GameBaseImpl._


//****************************************************************************** CLASS DEFINITION
/**
  * Represents a game instance with it's current state and game field
  *
  * @param field  mastermind game field
  * @param state  state in which the game is currently
  */
case class Game(var field: Field, val code: Code, var currentTurn: Int) extends GameInterface {
    
  var state: State = Init()
    
  val maxTurn: Int = 1
  
  //Partial function gets string and returns a event
  type PartialFunctionRule = PartialFunction[String, Event]
  
  // Defines the Chain of Responsibility (Pattern)
  val chainSCR: PartialFunctionRule = {
    RequestHandlerSCR.HelpInputRule orElse 
    RequestHandlerSCR.MenuInputRule orElse
    RequestHandlerSCR.PlayInputRule orElse
    RequestHandlerSCR.QuitInputRule orElse
    RequestHandlerSCR.UndoInputRule orElse
    RequestHandlerSCR.RedoInputRule orElse
    RequestHandlerSCR.SaveInputRule orElse
    RequestHandlerSCR.LoadInputRule
  }

  
  /**
    * Calls the responsible chain
    *
    * @param request
    * @return
    */
  def handleRequest(request: Request): Event = {
    request match {
      case SingleCharRequest(userinput) => {
        //println("SingleCharRequest: " + userinput)                              //@todo remove after debugging
        chainSCR.applyOrElse(userinput, RequestHandlerSCR.DefaultInputRule)
      }
      case MultiCharRequest(userinput) => {
        //println("MultiCharRequest: " + userinput)                               //@todo remove after debugging
        if(userinput.size != field.matrix.cols)
          return RequestHandlerSCR.DefaultInputRule(userinput)
        else
          return PlayerAnalyzeEvent()
      }
    }
  }

  def request(event: Event): State = {
    event match{
      case init: InitStateEvent         => state = Init()
      case menu: MenuStateEvent         => state = Menu()
      case play: PlayStateEvent         => state = Play()
      case quit: QuitStateEvent         => state = Quit()
      case help: HelpStateEvent         => state = Help()
      
      case pInp: PlayerInputStateEvent  => state = PlayerInput()
      case pLos: PlayerLoseStateEvent   => state = PlayerLose()
      case pWin: PlayerWinStateEvent    => state = PlayerWin()
      case pAna: PlayerAnalyzeEvent     => state = PlayerAnalyze()
       
    }
    return state.handle()
  }
  
  override def toString(): String = field.toString
  
  def getMaxTurns() = maxTurn
  
  def getRemainingTurns() = maxTurn - currentTurn
  
  def getCurrentTurn() = currentTurn
  
  def setTurn(): Int = currentTurn
  
  def undoTurn(): Int = currentTurn

  def getCode(): Code = code
  
  def resetGame(): GameInterface = this
  
  def buildVector(vector: Vector[Stone], chars: Array[Char]): Vector[Stone] = {
    val stone = chars(vector.size) match
      case 'R'|'r'|'1'  => Stone("R")
      case 'G'|'g'|'2'  => Stone("G")
      case 'B'|'b'|'3'  => Stone("B")
      case 'Y'|'y'|'4'  => Stone("Y")
      case 'W'|'w'|'5'  => Stone("W")
      case 'P'|'p'|'6'  => Stone("P")

      val newvector = vector.appended(stone)
      if (newvector.size < field.cols)
        buildVector(newvector, chars)
      else
        return newvector
  }
  
  
  /**
    * Return the event that is needed to trigger the current state and 
    * can be used to stay in the current state
    * @return event that triggers the current state
    */
  def getCurrentStateEvent(): Event = {
    state match {
      case init:Init        => HelpStateEvent()
      case menu:Menu        => MenuStateEvent()
      case play:Play        => PlayStateEvent()
      case quit:Quit        => QuitStateEvent()
      case help:Help        => HelpStateEvent()
      case pInp:PlayerInput => PlayerInputStateEvent()
    }
  }
  
  def getDefaultInputRule(input: String): Event = {
    RequestHandlerSCR.DefaultInputRule(input)
  }
  
  object RequestHandlerSCR {
    
    //defines the general rule for the chain
    def singleCharRule(f: String => Boolean, result: Event): PartialFunctionRule = {
      case s if f(s) => 
        print(">>> " + result + " [will be processed] ")
        result
    }
    
    //defines the concrete rules
    val HelpInputRule: PartialFunctionRule = singleCharRule(_ == "h", HelpStateEvent())
    val MenuInputRule: PartialFunctionRule = singleCharRule(_ == "m", MenuStateEvent())
    val PlayInputRule: PartialFunctionRule = singleCharRule(_ == "p", PlayStateEvent())
    val QuitInputRule: PartialFunctionRule = singleCharRule(_ == "q", QuitStateEvent())
    val UndoInputRule: PartialFunctionRule = singleCharRule(_ == "u", UndoStateEvent())
    val RedoInputRule: PartialFunctionRule = singleCharRule(_ == "r", RedoStateEvent())
    val SaveInputRule: PartialFunctionRule = singleCharRule(_ == "s", SaveStateEvent())
    val LoadInputRule: PartialFunctionRule = singleCharRule(_ == "l", LoadStateEvent())
    
    //defines the default rule
    def DefaultInputRule(userinput: String): Event = {
      println(">>> Error: Invalid input [will be ignored]")
      getCurrentStateEvent()
    }
  }
}