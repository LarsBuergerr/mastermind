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
            icons += new Image(getClass.getResource("/logo.png").toExternalForm, 100, 100, true, true)
            resizable = false
            title.value = "Mastermind"

            scene = new Scene(400, 800) {
                this.setCursor(new ImageCursor(new Image(getClass.getResource("/mouse.png").toExternalForm, 100, 100, true, true))) 
                
                val border = new BorderPane()
                val pane = new GridPane()
                pane.setMaxWidth(300)
                pane.setMaxHeight(300)
                pane.setAlignment(CENTER)

                for (i <- 0 to 3) {
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
      
        val circle_size = 80
        val image_size = circle_size - 5

        this.setMinSize(circle_size, circle_size)
        this.setPrefSize(circle_size, circle_size)
        this.setMaxSize(circle_size, circle_size)
        val label = new Label("")
        label.setPrefSize(circle_size, circle_size)
        label.setMinSize(circle_size, circle_size)
        label.setMaxSize(circle_size, circle_size)
        label.setGraphic(new ImageView(new Image("circle_back_dark_512.png", image_size, image_size, smooth = true, preserveRatio = true)))
        
        label.setOnMouseEntered(e => label.setGraphic(new ImageView(new Image("circle_back_light_512.png", image_size, image_size, true, true))))
        label.setOnMouseExited(e => label.setGraphic(new ImageView(new Image("circle_back_dark_512.png", image_size, image_size, true, true))))
        label.setOnMouseClicked(e => label.setGraphic(new ImageView(new Image("test.gif", image_size, image_size, true, true))))

        this.getChildren().add(label)
    }
}
