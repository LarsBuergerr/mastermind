package de.htwg.se.mastermind

package model
package FileIOComponent
package fileIOyamlImpl


import GameComponent.GameBaseImpl.{Field, Code, Matrix, Stone, HStone, HintStone, Game}
import de.htwg.se.mastermind.model.GameComponent.GameInterface

import net.jcazevedo.moultingyaml._
import MastermindModule.{given}

class FileIO extends FileIOInterface {
  
  override def load(game: GameInterface): GameInterface = 
    val source = scala.io.Source.fromFile("game.yaml")
    val yaml = source.mkString.parseYaml
    source.close()
    yaml.convertTo[GameInterface](GameProtocol)

  
  override def save(game: GameInterface): Unit = 
    import java.io._
    import scala.xml._
    val pw = new PrintWriter(new File("game.yaml"))
    pw.write(game.toYaml(GameProtocol).prettyPrint)
    pw.close()
  
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
              YamlString("vector") -> YamlArray(obj.map(_.toYaml(StoneProtocol.asInstanceOf[YamlFormat[Object]])))
            case _: HStone =>
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
}