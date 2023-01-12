package de.htwg.se.mastermind

package model
package FileIOComponent
package fileIOxmlImpl

import GameComponent.GameInterface
import GameComponent.GameBaseImpl.{Field, Stone, Matrix, HintStone, HStone, Code}
import GameComponent.GameBaseImpl.Game

import java.io._
import scala.xml._

class FileIO extends FileIOInterface {

  override def load(game: GameInterface): GameInterface = 
    import java.io._
    import scala.xml._

    val source = scala.io.Source.fromFile("game.xml")
    val xml = XML.loadString(source.mkString)
    source.close()

    val curr_turn = (xml \ "turns").text.trim.toInt
    val code = Code((xml \ "code").text.trim.map(c => Stone(c.toString)).toVector)
    val matrix = loadMatrix(xml, "matrix").asInstanceOf[Matrix[Stone]]
    val hint_matrix = loadMatrix(xml, "hint_matrix").asInstanceOf[Matrix[HStone]]

    val game = new Game(new Field(matrix, hint_matrix), code, curr_turn)

    return game

  
  def loadMatrix(xml: NodeSeq, m_type: String): Matrix[Object] =
    val rows = (xml \ "rows").text.trim.toInt
    val cols = (xml \ "cols").text.trim.toInt

    var matrix = if (m_type == "matrix") new Matrix(rows, cols, Stone(" ")) else new Matrix(rows, cols, HintStone(" "))
    val all_rows = (xml \ m_type \ "row")
    all_rows.map(row => {
      val row_num = (row \ "@row").text.trim.toInt
      val all_cells = (row \ "cell")
      all_cells.map(cell => {
        val cell_y = (cell \ "@col").text.trim.toInt
        val cell_x = (cell \ "@row").text.trim.toInt
        val cell_value = (cell \ "value").text.trim


        matrix = if (m_type == "matrix") matrix.replaceCell(cell_x, cell_y, Stone(cell_value)) else matrix.replaceCell(cell_x, cell_y, HintStone(cell_value))
      })
    })

    return matrix

  override def save(game: GameInterface): Unit =
    import java.io._
    import scala.xml._
    val pw = new PrintWriter(new File("game.xml"))
    pw.write(gameToXml(game).toString())
    pw.close()

  def cellToXml(matrix: Matrix[Object], row: Int, col: Int) =
    <cell row={row.toString} col={col.toString}>
      <value>
        {matrix.cell(row, col)}
      </value>
    </cell>

  def rowToXml(matrix: Matrix[Object], row: Int) =
    <row row={row.toString}>
      {
        for (col <- 0 until matrix.cols)
          yield cellToXml(matrix, row, col)
      }
    </row>

  def matrixToXml(matrix: Matrix[Object]) =
    <matrix>
      {
        for (row <- 0 until matrix.rows)
          yield rowToXml(matrix, row)
      }
    </matrix>

  def hmatrixToXml(hmatrix: Matrix[Object]) =
    <hint_matrix>
      {
        for (row <- 0 until hmatrix.rows)
          yield rowToXml(hmatrix, row)
      }
    </hint_matrix>

  def gameToXml(game: GameInterface) =
    <game>
      <rows>
        {game.field.matrix.rows}
      </rows>
      <cols>
        {game.field.matrix.cols}
      </cols>
      {matrixToXml(game.field.matrix.asInstanceOf[Matrix[Object]])}
      {hmatrixToXml(game.field.hmatrix.asInstanceOf[Matrix[Object]])}
      <turns>
        {game.getCurrentTurn()}
      </turns>
      <code>
        {game.getCode().code}
      </code>
    </game>
}