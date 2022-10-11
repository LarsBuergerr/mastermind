package de.htwg.se.mastermind
package model

import de.htwg.se.mastermind.util.Event
//import State._

//trait Stateable:
//
// var gamestate: Option[GameState] = None
// def handle(event: Event): Option[GameState]


//trait GameState:
//  def toString(): String
//
//case class Init(state: State) extends GameState:
//  val eol            = sys.props("line.separator")
//  val line           = "----------------------------------------------------------------" + eol  
//  val welcomeMessage = "------------------ Welcome to Mastermind -----------------------" + eol
//  override def toString = eol + line + welcomeMessage + line + eol
//
//case class Menu(state: State) extends GameState:
//  override def toString = "Menu:"
