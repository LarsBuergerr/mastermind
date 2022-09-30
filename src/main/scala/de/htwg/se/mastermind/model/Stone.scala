/**
  * Stone.scala
  */

//********************************************************************** PACKAGE
package de.htwg.se.mastermind
package model

//********************************************************************** IMPORTS
import scala.util.Random

/**
  * Enum declaration for the mastermind game stones
  *
  * @param stringRepresentation
  */
enum Stone(stringRepresentation: String):
  override def toString(): String = stringRepresentation
  case Red extends Stone("R")
  case Green extends Stone("G")
  case Blue extends Stone("B")
  case Yellow extends Stone("Y")
  case White extends Stone("W")
  case Purple extends Stone("P")
  case Empty extends Stone(" ")

  
/**
  * Object declaration for random Stone function
  * @Important: size - 1 to avoid empty stones in random generated codes
  */
object Stone:
  
  def random: Stone = {
    Stone.values(Random.nextInt(Stone.values.size - 1))
  }

  
/**
  * Enum declaration for the mastermind hint stones (Black and White)
  *
  * @param stringRepresentation
  */  
enum HintStone(stringRepresentation: String):
  override def toString(): String = stringRepresentation
  case Black extends HintStone("B")
  case White extends HintStone("W")
  case Empty extends HintStone(" ")