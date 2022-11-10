/**
  * Stone.scala
  * Implements the Factory Pattern to create different stones
  */

//********************************************************************** PACKAGE
package de.htwg.se.mastermind
package model

//********************************************************************** IMPORTS
import scala.util.Random

trait Stone {
  val stringRepresentation: String
  override def toString(): String = stringRepresentation
}

private class Red extends Stone {
  val stringRepresentation = "R"
  override def toString(): String = stringRepresentation
}

private class Green extends Stone {
  val stringRepresentation = "G"
  override def toString(): String = stringRepresentation
}

private class Blue extends Stone {
  val stringRepresentation = "B"
  override def toString(): String = stringRepresentation
}

private class Yellow extends Stone {
  val stringRepresentation = "Y"
  override def toString(): String = stringRepresentation
}

private class White extends Stone {
  val stringRepresentation = "W"
  override def toString(): String = stringRepresentation
}

private class Purple extends Stone {
  val stringRepresentation = "P"
  override def toString(): String = stringRepresentation
}

private class Empty extends Stone {
  val stringRepresentation = " "
  override def toString(): String = stringRepresentation
}

object Stone {
  def apply(stringRepresentation: String): Stone = stringRepresentation match {
    case "R" => new Red
    case "G" => new Green
    case "B" => new Blue
    case "Y" => new Yellow
    case "W" => new White
    case "P" => new Purple
    case " " => new Empty
    case _   => new Empty
  }
  
  def random: Stone = {
    Stone.apply(Random.nextInt(6) match {
      case 0 => "R"
      case 1 => "G"
      case 2 => "B"
      case 3 => "Y"
      case 4 => "W"
      case 5 => "P"
      case _ => " "
    })
  }
  
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