package de.htwg.se.mastermind.model


/* Don't wanna use the stings all the time */
private val plus = "+"
private val minus = "-"
private val verLine = "|"
private val space = " "
private val rbracket = "["
private val lbracket = "]"
private val eol = sys.props("line.separator")


def bar(cellWidth: Int = 3, cellCount: Int = 4) = 
{
  (plus + (minus * cellWidth)) * cellCount + plus + eol                         /* default bar: +---+---+---+---+ */
}

def cells(cellWidth: Int = 3, cellCount: Int = 4) = 
{
  (verLine + (space * cellWidth)) * cellCount + verLine                         /* default cells :|   |   |   |   |  */
}

def hint_bar(cellWidth: Int = 3, cellCount: Int = 4) =
{
  space * 3 + rbracket + (space * cellWidth + verLine) * (cellCount - 1) + space * cellWidth + lbracket + eol
}

def mesh(cellWidth: Int = 3, rows: Int = 6, colls: Int = 4) = 
{
  (bar(cellWidth, colls) + cells(cellWidth, colls) + hint_bar(cellWidth, colls)) * rows + bar(cellWidth, colls)
}
