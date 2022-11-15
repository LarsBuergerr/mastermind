/**
  * Game.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model


//********************************************************************** IMPORTS
import util._


//********************************************************************** CLASS DEF
/**
  * Represents a game instance with it's current state and game field
  *
  * @param field  mastermind game field
  * @param state  state in which the game is currently
  */
case class Game(var field: Field, var state: State = Init()){
  
  private var currentTurn: Int = 0
  
  private val maxTurn: Int = field.matrix.rows
  
  //Partial function gets string and returns a event
  type PartialFunctionRule = PartialFunction[String, Event]
  
  // Defines the Chain of Responsibility (Pattern)
  val chainSCR: PartialFunctionRule = {
    RequestHandlerSCR.HelpInputRule orElse 
    RequestHandlerSCR.MenuInputRule orElse
    RequestHandlerSCR.PlayInputRule orElse
    RequestHandlerSCR.QuitInputRule
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

  
  // Defines the Chain of Responsibility (Pattern) for MultiCharRequests
  val chainMCR: PartialFunctionRule = {
    RequestHandlerMCR.DefaultInputRule
  }
  
  
  /**
    * Calls the responsible chain (either for Single or MultiCharRequests).
    *
    * @param request  request object to handle
    * @return         event object that will be passed to the state model
    */
  def handleRequest(request: Request): Event = {
    request match {
      case SingleCharRequest(userinput) => {
        //println("SingleCharRequest: " + userinput)                            //@todo remove after debugging
        chainSCR.applyOrElse(userinput, RequestHandlerSCR.DefaultInputRule)
      }
      case MultiCharRequest(userinput) => {
        //println("MultiCharRequest: " + userinput)                             //@todo remove after debugging
        chainMCR.applyOrElse(userinput, RequestHandlerMCR.ErrorInputRule)
      }
    }
  }

  
  /**
    * Handles the current state of the game (State Pattern)
    *
    * @param event  event to be handled
    * @return       new state of the game
    */
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
  
  def setTurn(): Int = {
    currentTurn = currentTurn + 1
    return currentTurn
  }
  
  
  def buildVector(vector: Vector[Stone], chars: Array[Char]): Vector[Stone] = {
    val stone = chars(vector.size) match
      case 'R'|'r'|'1'  => Stone("R")
      case 'G'|'g'|'2'  => Stone("G")
      case 'B'|'b'|'3'  => Stone("B")
      case 'Y'|'y'|'4'  => Stone("Y")
      case 'W'|'w'|'5'  => Stone("W")
      case 'P'|'p'|'6'  => Stone("P")
      case _            => Stone(" ")

      val newvector = vector.appended(stone)
      if (newvector.size < field.cols)
        buildVector(newvector, chars)
      else
        return newvector
  }
  
  def checkVector(vector: Vector[Stone]): Boolean = {
    for (stone <- vector) {
      if (stone.stringRepresentation == " ")
        return true
    }
    return false
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
  
  
  object RequestHandlerSCR {
    
    //defines the general rule for the chain
    def singleCharRule(f: String => Boolean, result: Event): PartialFunctionRule = {
      case s if f(s) => result
    }
    
    //defines the concrete rules
    val HelpInputRule: PartialFunctionRule = singleCharRule(_ == "h", HelpStateEvent())
    val MenuInputRule: PartialFunctionRule = singleCharRule(_ == "m", MenuStateEvent())
    val PlayInputRule: PartialFunctionRule = singleCharRule(_ == "p", PlayStateEvent())
    val QuitInputRule: PartialFunctionRule = singleCharRule(_ == "q", QuitStateEvent())
    
    //defines the default rule
    def DefaultInputRule(userinput: String): Event = {
      println(">>> Error: Invalid input [will be ignored]")
      getCurrentStateEvent()
    }
  }
  
  
  object RequestHandlerMCR {
    
    //defines the general rule for the chain
    def multiCharRule(f: String => Boolean, result: Event): PartialFunctionRule = {
      case s if f(s) => result
    }
    
    //defines the concrete rules
    val DefaultInputRule: PartialFunctionRule = multiCharRule(_.size.equals(field.matrix.cols), PlayerAnalyseEvent())
    
    //defines the error rule
    def ErrorInputRule(userinput: String): Event = {
      println(">>> Error: Invalid input [will be ignored]")
      getCurrentStateEvent()
    }
  }
}