/**
  * Game.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model


//********************************************************************** IMPORTS
import util._


//******************************************************************** CLASS DEF
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
}