/**
  * Stone.scala
  * Implements the Factory Pattern to create different stones
  */

//****************************************************************************** PACKAGE
package de.htwg.se.mastermind
package model
package GameComponent
package GameBaseImpl


//****************************************************************************** IMPORTS
import scala.util.Random


//****************************************************************************** INTERFACE DEFINITION
trait Stone {
  val stringRepresentation: String
  override def toString(): String = stringRepresentation

  override def equals(obj: Any): Boolean = obj match {
    case that: Stone => this.stringRepresentation.equals(that.stringRepresentation)
    case _ => false
  }
}

trait HStone {
  val stringRepresentation: String
  override def toString(): String = stringRepresentation

  override def equals(obj: Any): Boolean = obj match {
    case that: HStone => this.stringRepresentation.equals(that.stringRepresentation)
    case _ => false
  }
}


//****************************************************************************** CLASS DEFINITIONS
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
  val stringRepresentation = "E"
  override def toString(): String = stringRepresentation
}

private class HRed extends HStone {
  val stringRepresentation = "R"
  override def toString(): String = stringRepresentation
}

private class HWhite extends HStone {
  val stringRepresentation = "W"
  override def toString(): String = stringRepresentation
}

private class HEmpty extends HStone {
  val stringRepresentation = "E"
  override def toString(): String = stringRepresentation
}

//****************************************************************************** OBJECT DEFINITION
object Stone {
  def apply(stringRepresentation: String): Stone = stringRepresentation match {
    case "R" => new Red
    case "G" => new Green
    case "B" => new Blue
    case "Y" => new Yellow
    case "W" => new White
    case "P" => new Purple
    case "E" => new Empty
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
      case _ => "E"
    })
  } 
}


object HintStone {
  def apply(stringRepresentation: String): HStone = stringRepresentation match {
    case "R" => new HRed
    case "W" => new HWhite
    case "E" => new HEmpty
    case _   => new HEmpty
  }
}