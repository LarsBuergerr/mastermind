import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._ // if you don't supply your own protocol

//import game
import de.htwg.se.mastermind.model.GameComponent.GameBaseImpl.{Game, Field, Code, Matrix, Stone, HStone, HintStone}
import de.htwg.se.mastermind.model.GameComponent.GameInterface

case class Person(name: String, value: Int)

//-------------------------------Person yaml protocol--------------------------------
//only to test if this yaml wrapper works with scala 3

val PersonProtocol = new YamlFormat[Person] {
  
  //read yaml and return Person
  def read(yaml: YamlValue) =
    yaml.asYamlObject.getFields(YamlString("name"), YamlString("value")) match {
      case Seq(YamlString(name), YamlNumber(value)) =>
        Person(name, value.toInt)
      case _ => deserializationError("Person expected")
    }
    

  def write(obj: Person) =
    YamlObject(
      YamlString("name") -> YamlString(obj.name),
      YamlString("value") -> YamlNumber(obj.value))
}


val pers = Person("Hans", 42)

val yaml = pers.toYaml(PersonProtocol)

val persFromYaml = yaml.convertTo[Person](PersonProtocol)


//-------------------------------Code yaml protocol--------------------------------

val CodeProtocol = new YamlFormat[Code] {
  
  //write Code array to yaml
  def write(obj: Code): YamlValue = 
    YamlObject(
      YamlString("code") -> YamlArray(obj.code.map(_.toYaml(StoneProtocol)))
    )


  def read(yaml: YamlValue) =
    val yaml_data = yaml.asYamlObject.getFields(YamlString("code"))
    yaml_data match {
      case Seq(YamlArray(code)) => Code(code.map(_.convertTo[Stone](StoneProtocol)))
      case _ => deserializationError("Code expected")
    }
}

//-------------------------------Stone yaml protocol--------------------------------
val StoneProtocol = new YamlFormat[Stone] {
  
  //write Stone to yaml
  def write(obj: Stone): YamlValue = 
    YamlObject(
      YamlString("stone") -> YamlString(obj.toString())
    )

  def read(yaml: YamlValue) =
    val yaml_data = yaml.asYamlObject.getFields(YamlString("stone"))
    yaml_data match {
      case Seq(YamlString(stone)) => Stone(stone)
      case _ => deserializationError("Stone expected")
    }
}

//-------------------------------HStone yaml protocol--------------------------------
val HStoneProtocol = new YamlFormat[HStone] {
  
  //write HStone to yaml
  def write(obj: HStone): YamlValue = 
    YamlObject(
      YamlString("hstone") -> YamlString(obj.toString())
    )

  def read(yaml: YamlValue) =
    val yaml_data = yaml.asYamlObject.getFields(YamlString("hstone"))
    yaml_data match {
      case Seq(YamlString(hstone)) => HintStone(hstone)
      case _ => deserializationError("HStone expected")
    }
}

//-------------------------------Vector yaml protocol--------------------------------
val VectorProtocol = new YamlFormat[Vector[Object]] {
    
    //write Vector to yaml
    def write(obj: Vector[Object]): YamlValue = 
      YamlObject(
        obj(0) match {
          case _: Stone => 
            print("Using StoneProtocol")
            YamlString("vector") -> YamlArray(obj.map(_.toYaml(StoneProtocol.asInstanceOf[YamlFormat[Object]])))
          case _: HStone =>
            print("Using HStoneProtocol")
            YamlString("vector") -> YamlArray(obj.map(_.toYaml(HStoneProtocol.asInstanceOf[YamlFormat[Object]])))
        }
      )

    def read(yaml: YamlValue) = 
      val yaml_data = yaml.asYamlObject.getFields(YamlString("vector"))
      yaml_data match {
        case Seq(YamlArray(vector)) => {

          val keys = vector(0).asYamlObject.fields.keys
          keys.head match {
            case YamlString("stone") => vector.map(_.convertTo[Stone](StoneProtocol.asInstanceOf[YamlFormat[Stone]]))
            case YamlString("hstone") => vector.map(_.convertTo[HStone](HStoneProtocol.asInstanceOf[YamlFormat[HStone]]))
            case _ => deserializationError("Vector expected")
          }          
        }
          
        case _ => deserializationError("Vector expected")
      }
}

//-------------------------------Matrix yaml protocol--------------------------------
val GameProtocol = new YamlFormat[GameInterface] {
  
  //write Game to yaml
  def write(obj: GameInterface): YamlValue = 
    YamlObject(
      YamlString("matrix") -> YamlArray(obj.field.matrix.m.map(_.toYaml(VectorProtocol.asInstanceOf[YamlFormat[Vector[Object]]]))),
      YamlString("hmatrix") -> YamlArray(obj.field.hmatrix.m.map(_.toYaml(VectorProtocol.asInstanceOf[YamlFormat[Vector[Object]]]))),
      YamlString("code") -> obj.code.toYaml(CodeProtocol),
      YamlString("turn") -> YamlNumber(obj.currentTurn)
    )

  def read(yaml: YamlValue) = 
    val yaml_data = yaml.asYamlObject.getFields(YamlString("matrix"), YamlString("hmatrix"), YamlString("code"), YamlString("turn"))
    yaml_data match {
      case Seq(YamlArray(matrix), YamlArray(hmatrix), code, YamlNumber(turn)) =>
        val m = Matrix(matrix.map(_.convertTo[Vector[Object]](VectorProtocol.asInstanceOf[YamlFormat[Vector[Object]]])))
        val hm = Matrix(hmatrix.map(_.convertTo[Vector[Object]](VectorProtocol.asInstanceOf[YamlFormat[Vector[Object]]])))
        val c = code.convertTo[Code](CodeProtocol)
        val s = turn.toInt
        return new Game(new Field(m.asInstanceOf[Matrix[Stone]], hm.asInstanceOf[Matrix[HStone]]), c.asInstanceOf[Code], s)
      case _ => 
        print(yaml_data(0))
        return deserializationError("Game expected")
    }
}


val code = Code(Vector(Stone("E"), Stone("R"), Stone("R"), Stone("E")))

val yamlCode = code.toYaml(CodeProtocol)
print(yamlCode.prettyPrint)

val codeFromYaml = yamlCode.convertTo[Code](CodeProtocol)

print(codeFromYaml)


//test to see if the loaded matrix is the same as the original
var matrix = new Matrix(4, 4, Stone("E"))
val changed_m = matrix.replaceRow(2, Vector(Stone("R"), Stone("R"), Stone("R"), Stone("R")))

var hmatrix = new Matrix(4, 4, HintStone("E"))
val changed_hm = hmatrix.replaceRow(2, Vector(HintStone("R"), HintStone("R"), HintStone("R"), HintStone("R")))

val game = new Game(new Field(matrix, hmatrix), code, 1)

val changed_game = new Game(new Field(changed_m, changed_hm), code, 1)

val yamlGame = game.toYaml(GameProtocol)
val yamlChangedGame = changed_game.toYaml(GameProtocol)

print(yamlGame.prettyPrint)
print(yamlChangedGame.prettyPrint)

val gameFromYaml = yamlGame.convertTo[GameInterface](GameProtocol)
val changedGameFromYaml = yamlChangedGame.convertTo[GameInterface](GameProtocol)

//saving yamlChangedGame variable to file
val file = new java.io.PrintWriter("game.yaml")
file.write(yamlChangedGame.prettyPrint)
file.close

//loading yamlChangedGame variable from file
val source = scala.io.Source.fromFile("game.yaml")
val lines = try source.mkString finally source.close()
val yamlChangedGameFromFile = lines.parseYaml
val gameFromFile = yamlChangedGameFromFile.convertTo[GameInterface](GameProtocol)