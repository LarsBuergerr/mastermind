/**
  * Request.scala
  * Implements the Chain of Responsibility Pattern to analyze user input
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util

//****************************************************************************** IMPORTS


//****************************************************************************** CLASS DEFINITION
sealed abstract class Request

case class SingleCharRequest(userinput: String) extends Request
case class  MultiCharRequest(userinput: String) extends Request

case class Response(req: Request, handled: Boolean)