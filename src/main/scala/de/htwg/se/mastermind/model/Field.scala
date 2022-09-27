/**
  * Field.scala
  */

//********************************************************************** PACKAGE
package de.htwg.se.mastermind
package model

//********************************************************************** IMPORTS


/* Don't wanna use the stings all the time */
private val plus = "+"
private val minus = "-"
private val verLine = "|"
private val space = " "
private val rbracket = "["
private val lbracket = "]"
private val eol = sys.props("line.separator")


/**
  * A Mastermind field
  *
  * @param matrix   Matrix with the actual player stones
  * @param hmatrix  Matrix with the hint stones
  */
case class Field(matrix: Matrix[Stone], hmatrix: Matrix[HintStone]):
  
  

  def this(rows: Int = 6, cols: Int = 4, filling: Stone = Stone.Empty, hfilling: HintStone = HintStone.Empty) = {
    this(new Matrix(rows, cols, filling), new Matrix(rows, cols, hfilling))
  }
  
  val rows = matrix.rows
  val cols = matrix.cols

  def bar(cellWidth: Int = 3, cellCount: Int = this.cols): String = {
    (plus + (minus * cellWidth)) * cellCount + plus + eol
  }

  def cells(row: Int = 0, cellWidth: Int = 3, cellCount: Int = this.cols): String = {
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + 
    space * 3 + hmatrix.row(row).map(_.toString).map(" " + _ + " ").mkString("[", "|", "]") + eol
  }

  def mesh(cellWidth: Int = 3, rows: Int = this.rows, colls: Int = this.cols):String = 
  {
    (0 until rows).map(cells(_)).mkString(bar(cellWidth, colls), bar(cellWidth, colls), bar(cellWidth, colls))
  }
  
  def put(stone: Vector[Stone], row: Int) = copy(matrix.replaceRow(row, stone), hmatrix)

  def placeHints(stone: Vector[HintStone], row: Int) = copy(matrix, hmatrix.replaceRow(row, stone))

  override def toString = mesh(3, rows, cols)



