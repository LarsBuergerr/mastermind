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
  
  type PartialFunctionRule = PartialFunction[String, Int]
  
  // Defines the Chain of Responsibility (Pattern)
  val chainSCR: PartialFunctionRule = {
    RequestHandlerSCR.HelpInputRule orElse 
    RequestHandlerSCR.MenuInputRule
  }
  
  /**
    * Calls the responsible chain
    *
    * @param request
    * @return
    */
  def handleRequest(request: Request): Unit = {
    request match {
      case SingleCharRequest(userinput) => {
        println("SingleCharRequest: " + userinput)                              //@todo remove after debugging
        println(chainSCR.applyOrElse(userinput, RequestHandlerSCR.DefaultInputRule))
      }
      case MultiCharRequest(userinput) => {
        println("MultiCharRequest: " + userinput)                               //@todo remove after debugging
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
    //println("<<<debug>>>: handler called")                                    //@todo remove after debugging
    event match{
      case init: InitState        => state = Init()
      case menu: MenuState        => state = Menu()
      case play: PlayState        => state = Play()
      case quit: QuitState        => state = Quit()
      case help: HelpState        => state = Help()
      
      case pInp: PlayerInputState => state = PlayerInput()
      case pLos: PlayerLoseState  => state = PlayerLose()
      case pWin: PlayerWinState   => state = PlayerWin()
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
  
  
  object RequestHandlerSCR {
    
    //defines the general rule for the chain
    def singleCharRule(f: String => Boolean, result: Int): PartialFunctionRule = {
      case s if f(s) => result
    }
    
    //defines the concrete rules
    val HelpInputRule: PartialFunctionRule = singleCharRule(_ == "h", 1)
    val MenuInputRule: PartialFunctionRule = singleCharRule(_ == "m", 2)
    //val IllegalInputRule: PartialFunctionRule = singleCharRule(_ == "i", true)
    
    //defines the default rule
    def DefaultInputRule(userinput: String): Int = -1
  }
}