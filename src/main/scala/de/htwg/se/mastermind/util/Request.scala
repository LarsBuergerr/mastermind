/**
  * Request.scala
  * Implements the Chain of Responsibility Pattern to analyze user input
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package util

/**
  * 
  */
sealed abstract class Request

/**
  * 
  */
case class SingleCharRequest(userinput: String) extends Request
case class  MultiCharRequest(userinput: String) extends Request

//case class MenuInput(userinput: String)         extends Request
//case class PlayInput(userinput: String)         extends Request
//case class HelpInput(userinput: String)         extends Request
//case class QuitInput(userinput: String)         extends Request
//case class EndInput(userinput: String)          extends Request
//case class IllegalRequest(userinput: String)    extends Request

/**
 *
 */
case class Response(req: Request, handled: Boolean)