package de.htwg.se.mastermind

package model
package FileIOComponent
package fileIOxmlImpl

import GameComponent.GameInterface
import GameComponent.GameBaseImpl.{Field, Stone, Matrix}
import GameComponent.GameBaseImpl.Game

import java.io._
import scala.xml._

class FileIO extends FileIOInterface {
  override def load: Matrix[Stone] = 
    import java.io._
    import scala.xml._

    val source = scala.io.Source.fromFile("game.xml")
    val xml = XML.loadString(source.mkString)
    source.close()

    val rows = (xml \ "rows").text.trim.toInt
    val cols = (xml \ "cols").text.trim.toInt
    val turns = (xml \ "turns").text.trim.toInt
    val code = (xml \ "code").text.trim

    var matrix = new Matrix(rows, cols, Stone(" "))
    val all_rows = (xml \ "matrix" \ "row")
    all_rows.map(row => {
      val row_num = (row \ "@row").text.trim.toInt
      val all_cells = (row \ "cell")
      all_cells.map(cell => {
        val cell_y = (cell \ "@col").text.trim.toInt
        val cell_x = (cell \ "@row").text.trim.toInt
        val cell_value = (cell \ "value").text.trim

        matrix = matrix.replaceCell(cell_x, cell_y, Stone(cell_value))
      })
    })
    print(matrix)

    return matrix

  override def save(game: GameInterface): Unit =
    import java.io._
    import scala.xml._

    val pw = new PrintWriter(new File("game.xml"))
    pw.write(gameToXml(game).toString())
    pw.close()

  def cellToXml(game: GameInterface, row: Int, col: Int) =
    <cell row={row.toString} col={col.toString}>
      <value>
        {game.field.matrix.cell(row, col)}
      </value>
    </cell>

  def rowToXml(game: GameInterface, row: Int) =
    <row row={row.toString}>
      {
        for (col <- 0 until game.field.matrix.cols)
          yield cellToXml(game, row, col)
      }
    </row>

  def matrixToXml(game: GameInterface) =
    <matrix>
      {
        for (row <- 0 until game.field.matrix.rows)
          yield rowToXml(game, row)
      }
    </matrix>

  def gameToXml(game: GameInterface) =
    <game>
      <rows>
        {game.field.matrix.rows}
      </rows>
      <cols>
        {game.field.matrix.cols}
      </cols>
      {matrixToXml(game)}
      <turns>
        {game.getCurrentTurn()}
      </turns>
      <code>
        {game.getCode()}
      </code>
    </game>
}

