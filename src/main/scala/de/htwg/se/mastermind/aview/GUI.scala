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


class GUI() extends JFXApp3 {
    
    
    override def start() = {
        stage = new JFXApp3.PrimaryStage {
            title.value = "Mastermind"

            scene = new Scene(1000, 1000) {
                val border = new BorderPane()
                val pane = new GridPane()
                pane.setMaxWidth(300)
                pane.setMaxHeight(300)
                pane.setAlignment(CENTER)

                for (i <- 0 to 9) {
                    for (j <- 0 to 9) {
                        val entry = new Entry(i, j)
                        pane.add(entry, i, j)
                    }
                }
                border.center = pane

                root = border
            }
        }
    }


    class Entry(x: Int, y: Int) extends StackPane {

        this.setMinSize(30, 30)
        this.setPrefSize(30, 30)
        this.setMaxSize(30, 30)
        val label = new Label("")
        label.setPrefSize(30, 30)
        label.setMinSize(30, 30)
        label.setMaxSize(30, 30)
        label.setGraphic(new ImageView(new Image("blue_square.png")))
        
        label.setOnMouseEntered(e => label.setGraphic(new ImageView(new Image("light_blue_square.png"))))
        label.setOnMouseExited(e => label.setGraphic(new ImageView(new Image("blue_square.png"))))

        this.getChildren().add(label)
    }
}
