/**
  * Command.scala
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util


//****************************************************************************** IMPORTS
import model.GameComponent.GameBaseImpl.Field


//****************************************************************************** INTERFACE DEFINITION
trait Command:
  def execute:  Field
  def undoStep: Field
  def redoStep: Field