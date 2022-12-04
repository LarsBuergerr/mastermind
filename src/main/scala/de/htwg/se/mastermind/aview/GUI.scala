package de.htwg.se.mastermind
package aview

import scalafx.scene.layout.{HBox, StackPane, BorderPane, Pane, VBox, Background, BackgroundFill, CornerRadii}
import scalafx.scene.paint.{Stops, LinearGradient}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.text.{Text, Font}
import scalafx.scene.shape.Polygon
import scalafx.application.JFXApp3
import scalafx.scene.paint.Color._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.ImageCursor
import scalafx.geometry.Insets
import scalafx.geometry.Pos._
import scalafx.scene.Scene
import scalafx.stage.Stage
import scalafx.Includes._
import javafx.scene.layout.GridPane
import scalafx.scene.shape.Circle
import javafx.scene.paint.ImagePattern
import scalafx.scene.effect.DropShadow

import controller.Controller
import util.*
import util.Observable
import model.Stone

class GUI(controller: Controller) extends JFXApp3 with Observer {
    controller.add(this)

    var currentStoneVector: Vector[Stone] = Vector(Stone(" "), Stone(" "), Stone(" "), Stone(" "))
    override def start() = {
        stage = new JFXApp3.PrimaryStage {
            icons += new Image(getClass.getResource("/logo.png").toExternalForm, 100, 100, true, true)
            resizable = false
            title.value = "Mastermind"

            scene = new Scene(1000, 1000) {
                this.setCursor(new ImageCursor(new Image(getClass.getResource("/mouse.png").toExternalForm, 100, 100, true, true)))
                
                val border = new GridPane()
                val stone_matrix = new GridPane()
                val img = new ImageView(new Image(getClass.getResource("/mastermind_logo.jpg").toExternalForm, 640, 640, true, true))

                val hint_stone_matrix = new GridPane()
                hint_stone_matrix.setMaxWidth(300)
                hint_stone_matrix.setMaxHeight(300)
                hint_stone_matrix.setAlignment(CENTER)

                for (i <- 0 to 3) {
                    for (j <- 0 to 3) {
                        val stone = controller.game.field.cells(j, i)
                        val entry = new Entry(i, j)
                        hint_stone_matrix.add(entry, i, j)
                    }
                }

                stone_matrix.setMaxWidth(300)
                stone_matrix.setMaxHeight(300)
                stone_matrix.setAlignment(CENTER)

                for (i <- 0 to 3) {
                    for (j <- 0 to 3) {
                        val stone = controller.game.field.cells(j, i)
                        val entry = new Entry(i, j)
                        stone_matrix.add(entry, i, j)
                    }
                }

                border.setAlignment(CENTER)
                border.gridLinesVisibleProperty().setValue(true)
                border.setVgap(20)
                border.setHgap(20)

                border.add(img, 0, 0, 2, 1)

                border.add(stone_matrix, 0, 1)
                border.add(hint_stone_matrix, 1, 1)

                root = border
            }
          
        }
    }

    override def update = {
        start()
    }


    class Entry(x: Int, y: Int) extends StackPane {
        val circle_size = 80
        val image_size = circle_size - 5

        this.setOnMouseClicked(e => {
                if(controller.game.getCurrentTurn() == y)
                    currentStoneVector.appended(Stone.random)
            }
        )
        this.setMinSize(circle_size, circle_size)
        this.setPrefSize(circle_size, circle_size)
        this.setMaxSize(circle_size, circle_size)
        val label = new Label("")
        label.setPrefSize(circle_size, circle_size)
        label.setMinSize(circle_size, circle_size)
        label.setMaxSize(circle_size, circle_size)

        controller.game.field.matrix.cell(x, y).stringRepresentation match {
            case " " => label.setStyle("-fx-background-color: #000000")
            case "R" => label.setStyle("-fx-background-color: #FF0000")
            case "G" => label.setStyle("-fx-background-color: #00FF00")
            case "B" => label.setStyle("-fx-background-color: #0000FF")
            case "Y" => label.setStyle("-fx-background-color: #FFFF00")
            case "P" => label.setStyle("-fx-background-color: #FF00FF")
            case "C" => label.setStyle("-fx-background-color: #00FFFF")
            case _ => label.setStyle("-fx-background-color: green")
        }
        label.setGraphic(new ImageView(new Image("circle_back_dark_512.png", image_size, image_size, smooth = true, preserveRatio = true)))
        
        // label.setOnMouseEntered(e => label.setGraphic(new ImageView(new Image("circle_back_light_512.png", image_size, image_size, true, true))))
        // label.setOnMouseExited(e => label.setGraphic(new ImageView(new Image("circle_back_dark_512.png", image_size, image_size, true, true))))
        // label.setOnMouseClicked(e => label.setGraphic(new ImageView(new Image("test.gif", image_size, image_size, true, true))))

        this.getChildren().add(label)
    }
}
