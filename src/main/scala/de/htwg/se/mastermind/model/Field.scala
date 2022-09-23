/**
  * Represents a mastermind field
  */

package de.htwg.se.mastermind.model


/* Don't wanna use the stings all the time */
private val plus = "+"
private val minus = "-"
private val verLine = "|"
private val space = " "
private val rbracket = "["
private val lbracket = "]"
private val eol = sys.props("line.separator")

/**
  * 
  *
  * @param matrix
  * @param hmatrix
  */
case class Field(matrix: Matrix[Stone], hmatrix: Matrix[HintStone]):

  def this(rows: Int = 6, cols: Int = 4, filling: Stone = Stone.Empty, hfilling: HintStone = HintStone.Empty) = {
    this(new Matrix(rows, cols, filling), new Matrix(rows, cols, hfilling))
  }
  
  val rows = matrix.rows
  val cols = matrix.cols

  def bar(cellWidth: Int = 3, cellCount: Int = 4): String = {
    (plus + (minus * cellWidth)) * cellCount + plus + eol
  }

  def cells(row: Int = 0, cellWidth: Int = 3, cellCount: Int = 4): String = {
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + 
    space * 3 + hmatrix.row(row).map(_.toString).map(" " + _ + " ").mkString("[", "|", "]") + eol
  }

  def mesh(cellWidth: Int = 3, rows: Int = 6, colls: Int = 4):String = 
  {
    (0 until rows).map(cells(_)).mkString(bar(cellWidth, colls), bar(cellWidth, colls), bar(cellWidth, colls))
  }
   
  override def toString = mesh()

  def put(stone: Stone, row: Int, col: Int) = copy(matrix.replaceCell(row, col, stone))

