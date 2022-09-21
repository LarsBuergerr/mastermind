package de.htwg.se.mastermind

import model._


@main def main: Unit = 

  val matrix = new Matrix(8, 8, Stone.Empty)
  val hmatrix = new Matrix(8, 8, HintStone.Empty)

  val new_matrix = matrix.replaceCell(3, 3, Stone.Blue)
  val new_hmatrix = hmatrix.fill(HintStone.Black)

  val field = new Field(new_matrix, new_hmatrix)

  val welcome = "Welcome to Mastermind!\n"
  
  val output = field.mesh(3, matrix.rows, matrix.cols) + "\n"
 
  print(output)







