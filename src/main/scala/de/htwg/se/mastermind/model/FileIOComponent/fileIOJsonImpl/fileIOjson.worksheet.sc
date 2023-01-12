import de.htwg.se.mastermind.model.GameComponent.GameBaseImpl.{Field, Stone, Matrix, Game, Code, HStone, HintStone}

import de.htwg.se.mastermind.model.FileIOComponent.fileIOjsonImpl.FileIO

import play.api.libs.json.*

var matrix = new Matrix(2, 2, Stone("E"))
matrix = matrix.replaceCell(0, 0, Stone("R"))
matrix = matrix.replaceCell(1, 1, Stone("B"))

val fileIO = new FileIO()

val cellToJson = fileIO.cellToJson(matrix.m(0)(0), 0, 0)

val vectorToJson = fileIO.vectorToJson(matrix.m(0), 0)


var game = new Game()
game.field.matrix = game.field.matrix.replaceRow(0, Vector(Stone("R"), Stone("R"), Stone("R"), Stone("R")))
game.field.matrix = game.field.matrix.replaceRow(2, Vector(Stone("R"), Stone("R"), Stone("R"), Stone("R")))

game.field.hmatrix = game.field.hmatrix.replaceRow(0, Vector(HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R")))
game.field.hmatrix = game.field.hmatrix.replaceRow(2, Vector(HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R")))

var gameToJson = fileIO.gameToJson(game)


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


def JsonToGame(gameJson: JsValue) =
  val matrix = Matrix[Stone](gameJson("matrix").as[Seq[JsValue]].map(vector => JsonToVector(vector, "matrix")).toVector.asInstanceOf[Vector[Vector[Stone]]])
  val hmatrix = Matrix[HStone](gameJson("hmatrix").as[Seq[JsValue]].map(vector => JsonToVector(vector, "hmatrix")).toVector.asInstanceOf[Vector[Vector[HStone]]])
  val code = Code(JsonToVector(gameJson("code"), "matrix").asInstanceOf[Vector[Stone]])
  val turn = gameJson("turn").as[Int]

  Game(new Field(matrix, hmatrix), code, turn)

val vectorFromJson = JsonToVector(vectorToJson, "matrix")

val cellFromJson = JsonToStone(cellToJson)

val gameFromJson = JsonToGame(gameToJson)

fileIO.save(game)

val gameFromJson2 = fileIO.load
