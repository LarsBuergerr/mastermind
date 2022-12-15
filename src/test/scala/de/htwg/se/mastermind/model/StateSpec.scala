/**
  * StateSpec.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model
package GameComponent
package GameBaseImpl

//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


//****************************************************************************** CLASS DEFINITION
class StateSpec extends AnyWordSpec{
  
  "The State class represents the game state and" should {
    
    "have a Interface which must not be instantiated" in {
      "val state = new State()" shouldNot compile
    }
    
    "have a Init() State, a object from class State with String rep. and handle" in {
      val state = new Init()
      state shouldBe a [State]
      state.toString() should equal("State: Init")
      state.handle() shouldBe a [Init]
      Console.withOut(System.out) {
        state.handle()
      }
    }
    
    "have a Menu() State, a object from class State with String rep. and handle" in {
      val state = new Menu()
      state shouldBe a [State]
      state.toString() should equal("State: Menu")
      state.handle() shouldBe a [Menu]
    }
    
    "have a Play() State, a object from class State with String rep. and handle" in {
      val state = new Play()
      state shouldBe a [State]
      state.toString() should equal("State: Play")
      state.handle() shouldBe a [Play]
    }
    
    "have a Help() State, a object from class State with String rep. and handle" in {
      val state = new Help()
      state shouldBe a [State]
      state.toString() should equal("State: Help")
      state.handle() shouldBe a [Help]
    }
    
    "have a Quit() State, a object from class State with String rep. and handle" in {
      val state = new Quit()
      state shouldBe a [State]
      state.toString() should equal("State: Quit")
      state.handle() shouldBe a [Quit]
    }
    
    "have a PlayerInput() State, a object from class State with String rep. and handle" in {
      val state = new PlayerInput()
      state shouldBe a [State]
      state.toString() should equal("State: PlayerInput")
      state.handle() shouldBe a [PlayerInput]
    }
    
    "have a PlayerAnalyseState() State, a object from class State with String rep. and handle" in {
      val state = new PlayerAnalyseState()
      state shouldBe a [State]
      state.toString() should equal("State: PlayerAnalysis")
      state.handle() shouldBe a [PlayerAnalyseState]
    }
    
    "have a PlayerLose() State, a object from class State with String rep. and handle" in {
      val state = new PlayerLose()
      state shouldBe a [State]
      state.toString() should equal("State: PlayerLose")
      state.handle() shouldBe a [PlayerLose]
    }
    
    "have a PlayerWin() State, a object from class State with String rep. and handle" in {
      val state = new PlayerWin()
      state shouldBe a [State]
      state.toString() should equal("State: PlayerWin")
      state.handle() shouldBe a [PlayerWin]
    }
    
    "have a PlayerAnalyze() State, a object from class State with String rep. and handle" in {
      val state = new PlayerAnalyze()
      state shouldBe a [State]
      state.toString() should equal("State: PlayerAnalyzes")
      state.handle() shouldBe a [PlayerAnalyze]
    }
    
    "not be true if compared to a different state" in {
      val state1 = new PlayerWin()
      val state2 = new PlayerLose()
      state1 should not be an [PlayerLose]
    }
  }
  
}