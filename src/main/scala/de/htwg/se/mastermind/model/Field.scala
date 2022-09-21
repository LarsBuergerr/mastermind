package de.htwg.se.mastermind.model


/* Don't wanna use the stings all the time */
private val plus = "+"
private val minus = "-"
private val verLine = "|"
private val space = " "
private val rbracket = "["
private val lbracket = "]"
private val eol = sys.props("line.separator")

case class Field(matrix: Matrix[Stone], hmatrix: Matrix[HintStone]):

  def this(size: Int, filling: Stone, hfilling: HintStone) = this(new Matrix(8, 4, filling), new Matrix(8, 4, hfilling))

  val rows = matrix.rows
  val cols = matrix.cols

  def bar(cellWidth: Int = 3, cellCount: Int = 4): String = 
  {
    (plus + (minus * cellWidth)) * cellCount + plus + eol                         /* default bar: +---+---+---+---+ */
  }

  def cells(row: Int, cellWidth: Int = 3, cellCount: Int = 4): String = 
  {
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + 
    space * 3 + hmatrix.row(row).map(_.toString).map(" " + _ + " ").mkString("[", "|", "]") + eol
    //(verLine + (space * cellWidth)) * cellCount + verLine                         /* default cells :|   |   |   |   |  */
  }

  def mesh(cellWidth: Int = 3, rows: Int = 6, colls: Int = 4):String = 
  {
    //(bar(cellWidth, colls) + cells(cellWidth, colls) + hint_bar(cellWidth, colls)) * rows + bar(cellWidth, colls)
    (0 until rows).map(cells(_)).mkString(bar(cellWidth, colls), bar(cellWidth, colls), bar(cellWidth, colls))
  } 
  override def toString = mesh()

