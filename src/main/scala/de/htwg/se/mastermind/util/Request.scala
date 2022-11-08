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
  * Defines the possible requests.
  */
case class SingleCharRequest(userinput: String) extends Request
case class  MultiCharRequest(userinput: String) extends Request

/**
 * 
 */
case class Response(req: Request, handled: Boolean)