package de.htwg.se.mastermind

package model
package FileIOComponent
package fileIOxmlImpl

import GameComponent.GameInterface
import GameComponent.GameBaseImpl.{Field, Stone, Matrix}


class FileIO extends FileIOInterface {
  override def load: GameInterface = ???
  override def save(game: GameInterface): Unit =
    import java.io._
    import scala.xml._

    val pw = new PrintWriter(new File("game.xml"))
    pw.write(gameToXml(game).toString())
    pw.close()

  def cellToXml(game: GameInterface, row: Int, col: Int) =
    <cell row={row.toString} col={col.toString}>
      {game.field.matrix.cell(row, col)}
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
      {matrixToXml(game)}
      {matrixToXml(game)}
      <turns>
        {game.getCurrentTurn()}
      </turns>
      <code>
        {game.getCode()}
      </code>
    </game>
}

