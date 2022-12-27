package de.htwg.se.mastermind

package model
package FileIOComponent
package fileIOJsonImpl

import GameComponent.GameInterface
import GameComponent.GameBaseImpl.{Field, Stone, Matrix, HStone, HintStone, Code, Game}

//import json lib
import play.api.libs.json.*

class FileIO extends FileIOInterface {
  override def load: GameInterface = 
    import scala.io.Source
    val source: String = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    JsonToGame(json)
    
  
  override def save(game: GameInterface): Unit = 
    import java.io._
    import scala.xml._
    val pw = new PrintWriter(new File("game.json"))
    pw.write(gameToJson(game).toString())
    pw.close()

  def cellToJson(cell: Object, x: Int, y: Int) = 
    val cellJson = Json.obj(
      "x" -> x,
      "y" -> y,
      "value" -> cell.toString()
    )
    cellJson

  def vectorToJson(vector: Vector[Object], row: Int) =
    Json.obj(
      "row" -> row,
      "cells" -> {
        //for loop with index
        for (i <- vector.indices) yield cellToJson(vector(i), row, i)
      }
    )


  def gameToJson(game: GameInterface) =
    Json.obj(
      "matrix" -> {
        for (row <- game.field.matrix.m.indices) 
          yield vectorToJson(game.field.matrix.m(row), row)
      },
      "hmatrix" -> {
        for (row <- game.field.hmatrix.m.indices) 
          yield vectorToJson(game.field.hmatrix.m(row), row)
      },
      "turn" -> game.currentTurn,
      "code" -> vectorToJson(game.code.code.asInstanceOf[Vector[Object]], 0),
    )
  }

  def JsonToStone(cellJson: JsValue) = 
  val x = cellJson("x").as[Int]
  val y = cellJson("y").as[Int]
  val value = cellJson("value").as[String]
  val cell = Stone(value)
  cell

def JsonToHStone(cellJson: JsValue) = 
  val x = cellJson("x").as[Int]
  val y = cellJson("y").as[Int]
  val value = cellJson("value").as[String]
  val cell = HintStone(value)
  cell


def JsonToVector(vectorJson: JsValue, mtype: String) =
  val row = vectorJson("row").as[Int]
  val cells = vectorJson("cells")

  val vector = 
    if mtype == "matrix" then
      cells.as[Seq[JsValue]].map(cell => JsonToStone(cell))
    else
      cells.as[Seq[JsValue]].map(cell => JsonToHStone(cell))
  vector.toVector


def JsonToGame(gameJson: JsValue): GameInterface =
  val matrix = Matrix[Stone](gameJson("matrix").as[Seq[JsValue]].map(vector => JsonToVector(vector, "matrix")).toVector.asInstanceOf[Vector[Vector[Stone]]])
  val hmatrix = Matrix[HStone](gameJson("hmatrix").as[Seq[JsValue]].map(vector => JsonToVector(vector, "hmatrix")).toVector.asInstanceOf[Vector[Vector[HStone]]])
  val code = Code(JsonToVector(gameJson("code"), "matrix").asInstanceOf[Vector[Stone]])
  val turn = gameJson("turn").as[Int]

  Game(new Field(matrix, hmatrix), code, turn)
