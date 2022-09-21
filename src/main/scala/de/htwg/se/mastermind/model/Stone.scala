package de.htwg.se.mastermind.model

/* Enum declaration for the TicTacToe stones */
enum Stone(stringRepresentation: String):
  override def toString(): String = stringRepresentation
  case Red extends Stone("R")
  case Green extends Stone("G")
  case Blue extends Stone("B")
  case Yellow extends Stone("Y")
  case White extends Stone("W")
  case Purple extends Stone("P")
  case Empty extends Stone(" ")
  
enum HintStone(stringRepresentation: String):
  override def toString(): String = stringRepresentation
  case Black extends HintStone("B")
  case White extends HintStone("W")
  case Empty extends HintStone(" ")