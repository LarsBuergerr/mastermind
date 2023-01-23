/**
  * EventSpec.scala
  */

//****************************************************************************** PACKAGE
package de.htwg.se.mastermind
package util


//****************************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


//****************************************************************************** CLASS DEFINITION
class EventSpec extends AnyWordSpec {
    "The Event Interface" should {
        "not be able to be instantiated" in {
            //Event() should be (null)
        }
    }
    "Every Event class" should {
      "be instantiated" in {
        MenuStateEvent() shouldBe a [MenuStateEvent]
        PlayStateEvent() shouldBe a [PlayStateEvent]
        HelpStateEvent() shouldBe a [HelpStateEvent]
        QuitStateEvent() shouldBe a [QuitStateEvent]
        InitStateEvent() shouldBe a [InitStateEvent]
        PlayerInputStateEvent() shouldBe a [PlayerInputStateEvent]
        PlayerWinStateEvent() shouldBe a [PlayerWinStateEvent]
        PlayerLoseStateEvent() shouldBe a [PlayerLoseStateEvent]
        PlayerAnalyzeEvent() shouldBe a [PlayerAnalyzeEvent]
        EndStateEvent() shouldBe a [EndStateEvent]
        UndoStateEvent() shouldBe a [UndoStateEvent]
        RedoStateEvent() shouldBe a [RedoStateEvent]
        LoadStateEvent() shouldBe a [LoadStateEvent]
        SaveStateEvent() shouldBe a [SaveStateEvent]
      }
      "a instance of Event" in {
        MenuStateEvent() shouldBe a [Event]
        PlayStateEvent() shouldBe a [Event]
        HelpStateEvent() shouldBe a [Event]
        QuitStateEvent() shouldBe a [Event]
        InitStateEvent() shouldBe a [Event]
        PlayerInputStateEvent() shouldBe a [Event]
        PlayerWinStateEvent() shouldBe a [Event]
        PlayerLoseStateEvent() shouldBe a [Event]
        PlayerAnalyzeEvent() shouldBe a [Event]
        EndStateEvent() shouldBe a [Event]
        UndoStateEvent() shouldBe a [Event]
        RedoStateEvent() shouldBe a [Event]
        LoadStateEvent() shouldBe a [Event]
        SaveStateEvent() shouldBe a [Event]
      }
    }
}