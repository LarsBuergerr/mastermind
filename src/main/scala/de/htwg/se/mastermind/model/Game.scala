package de.htwg.se.mastermind
package model

import util._

case class Game(var field: Field){
  
  var turn: Int = 0
  
  override def toString(): String = field.toString
}