/**
  * Event.scala
  * Implements the STATE_PATTERN to keep track over current game state
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util


//****************************************************************************** INTERFACE DEFINITION
trait Event


//****************************************************************************** CLASS DEFINITIONS
case class MenuStateEvent()         extends Event
case class PlayStateEvent()         extends Event
case class InitStateEvent()         extends Event

case class PlayerInputStateEvent()  extends Event
case class PlayerWinStateEvent()    extends Event
case class PlayerLoseStateEvent()   extends Event
case class PlayerAnalyzeEvent()     extends Event

case class HelpStateEvent()         extends Event
case class EndStateEvent()          extends Event
case class QuitStateEvent()         extends Event

case class UndoStateEvent()         extends Event
case class RedoStateEvent()         extends Event
case class SaveStateEvent()         extends Event
case class LoadStateEvent()         extends Event
