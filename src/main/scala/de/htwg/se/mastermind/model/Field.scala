package de.htwg.se.mastermind.model


/* Don't wanna use the stings all the time */
private val plus = "+"
private val minus = "-"
private val verLine = "|"
private val space = " "
private val eol = sys.props("line.separator")


def bar(cellWidth: Int = 3, cellCount: Int = 3) = 
{
  /* default bar +---+---+---+ | +---+---+---+ */
  (plus + (minus * cellWidth)) * cellCount + plus + space + verLine + space + (plus + (minus * cellWidth)) * cellCount + plus + eol
}

def cells(cellWidth: Int = 3, cellCount: Int = 3) = 
{
  /* |   |   |   | | |   |   |   | */
  (verLine + (space * cellWidth)) * cellCount + verLine + space + verLine + space + (verLine + (space * cellWidth)) * cellCount + verLine + eol
}

def mesh(cellWidth: Int = 3, rows: Int = 10, colls: Int = 4) = 
{
  (bar(cellWidth, colls) + cells(cellWidth, colls)) * rows + bar(cellWidth, colls)
}

/* Return mesh as string representation */
override def toString(): String = mesh()
