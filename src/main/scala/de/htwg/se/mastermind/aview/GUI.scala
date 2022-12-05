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
import scalafx.scene.effect.Glow
import scalafx.scene.effect.Reflection

import controller.Controller
import util.*
import util.Observable
import model.Stone
import scalafx.scene.paint.Color
import javafx.application.Platform

class GUI(controller: Controller) extends JFXApp3 with Observer {
    controller.add(this)

    var currentStoneVector: Vector[Stone] = Vector.from[Stone](Array.fill[Stone](controller.game.field.matrix.cols)(Stone("E")))
    var browseColors = 0
    val selectableColors = Vector("G", "R", "B", "Y", "P", "W")


    override def start() = {
        stage = new JFXApp3.PrimaryStage {
            icons += new Image(getClass.getResource("/logo.png").toExternalForm, 100, 100, true, true)
            resizable = false
            title.value = "Mastermind"
            
            scene = refreshScene()
        }
    }

    override def update = {
        print(controller.game.field)
        print(controller.game.getCurrentStateEvent())
        print(controller.game.getCode())
        
        /* Refresh scene with a runnable which is added to the threads event queue.
           This is needed cause Java/ScalaFX Threads must not be updated/interrupted from other threads */
        Platform.runLater(new Runnable() {
            override def run() = {
                stage.scene = refreshScene()
            }
        })
    }
    
    def refreshScene() : Scene = {
        new Scene(1000, 1000) {
            this.setOnScroll(e => {
                if (e.getDeltaY < 0) {
                    browseColors = (browseColors + 1) % selectableColors.length
                    this.setCursor(new ImageCursor(new Image(getClass.getResource("/coursers/courser_" + selectableColors(browseColors) + ".png").toExternalForm, 300, 300, true, true)))
                }
            })
            this.setCursor(new ImageCursor(new Image(getClass.getResource("/coursers/courser_" + selectableColors(browseColors) + ".png").toExternalForm, 300, 300, true, true)))
            
            val border = new GridPane()
            val stone_matrix = new GridPane()
            val img = new ImageView(new Image(getClass.getResource("/mastermind_logo.png").toExternalForm(), 750, 100, true, true))
            //get total witdh of gridpane
            val hint_stone_matrix = new GridPane()
            hint_stone_matrix.setMaxWidth(300)
            hint_stone_matrix.setMaxHeight(300)
            hint_stone_matrix.setAlignment(CENTER)
            hint_stone_matrix.setHgap(10)
            hint_stone_matrix.setVgap(10)
            hint_stone_matrix.setPadding(Insets(10, 10, 10, 10))
            hint_stone_matrix.setStyle("-fx-background-color: #202225; -fx-background-radius: 10;")
            for (i <- 0 to controller.game.field.matrix.cols-1) {
                for (j <- 0 to controller.game.field.matrix.rows-1) {
                    val stone = controller.game.field.cells(j, i)
                    val entry = new Hints(i, j)
                    hint_stone_matrix.add(entry, i, j)
                }
            }
            stone_matrix.setMaxWidth(300)
            stone_matrix.setMaxHeight(300)
            stone_matrix.setAlignment(CENTER)
            stone_matrix.setHgap(10)
            stone_matrix.setVgap(10)
            stone_matrix.setPadding(Insets(10, 10, 10, 10))
            stone_matrix.setStyle("-fx-background-color: #202225; -fx-background-radius: 10;")
            for (i <- 0 to controller.game.field.hmatrix.cols-1) {
                for (j <- 0 to controller.game.field.hmatrix.rows-1) {
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
            val button = new Button("CheckCode") {
                this.setOnMouseClicked(e => {
                    //check if currentstonevector has no empty stones
                    val check = currentStoneVector.filter(stone => stone.stringRepresentation == "E")
                    if (check.length == 0) {
                        print("Checking code and placing hints")
                        val hints = controller.game.getCode().compareTo(currentStoneVector)
                        val tmp = currentStoneVector
                        for (i <- 0 until controller.game.field.matrix.cols) {
                            currentStoneVector = currentStoneVector.updated(i, Stone("E"))
                        }
                        print(currentStoneVector)
                        controller.placeGuessAndHints(tmp, hints, controller.game.getCurrentTurn())
                        //check if game is over
                    }
                })  
            }
            
            val buttonStyle_default = s"""
                    -fx-background-color: 
                        #202225,
                        linear-gradient(#38424b 0%, #1f2429 20%, #202225 100%),
                        linear-gradient(#20262b, #202225),
                        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));
                    -fx-background-radius: 5,4,3,5;
                    -fx-background-insets: 0,1,2,0;
                    -fx-text-fill: black;
                    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );
                    -fx-text-fill: linear-gradient(red, blue);
                    -fx-font-size: 20px;
                    -fx-padding: 10 20 10 20;
                """
            
            val buttonStyle_hover = s"""
                    -fx-background-color: 
                        #202225,
                        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),
                        linear-gradient(#20262b, #191d22),
                        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));
                    -fx-background-radius: 5,4,3,5;
                    -fx-background-insets: 0,1,2,0;
                    -fx-text-fill: black;
                    -fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );
                    -fx-text-fill: linear-gradient(red, blue);
                    -fx-font-size: 20px;
                    -fx-padding: 10 20 10 20;
            """
            
            //#dark-blue Text {
            //        -fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );
            //    }
            /* Using css for styling as example */
            //button.setStyle("-fx-background-color: #202225 ; -fx-text-fill: white; -padding: 10px;")
            button.style = buttonStyle_default
            //button.setOnMouseEntered(e => {
            //    button.style = buttonStyle_hover
            //})
            //button.setPadding(Insets(10, 10, 10, 10))
            //#p1 {background-color: #ff0000;}
            
            border.setStyle("-fx-background-color: #2f3136;") 
            border.add(button, 0, 2, 1, 1)
        
            val labelCurrentTurn = new Label("Turns left: " + controller.game.getRemainingTurns())
            
            //Default Label Style
            val labelStyle_default = s"""
                    -fx-text-fill: linear-gradient(red, #0000FF);
                    -fx-font-size: 30px;
                    -fx-font-weight: bold;
                    -fx-padding: 2 4 2 4;
                    -fx-border-color: #202225;
                    -fx-border-width: 4px;
                    -fx-border-style: solid;
                    -fx-border-radius: 5px;
                """
            labelCurrentTurn.style = labelStyle_default
            border.add(labelCurrentTurn, 1, 2, 1, 1)
            
            root = border
            }
        }


    class Entry(x: Int, y: Int) extends StackPane {
        val circle_size = 60
        val image_size = circle_size - 5
        
        //var glow = new Glow()
        //glow.setLevel(0.5)

        this.setOnMouseClicked(e => {
                if(controller.game.getCurrentTurn() == y) then
                    currentStoneVector = currentStoneVector.updated(x, Stone(selectableColors(browseColors)))
                    label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_" + selectableColors(browseColors) + ".png").toExternalForm, image_size, image_size, true, true)))
            }
        )

        
        this.setMinSize(circle_size, circle_size)
        this.setPrefSize(circle_size, circle_size)
        this.setMaxSize(circle_size, circle_size)
        val label = new Label("")
        label.setPrefSize(circle_size, circle_size)
        label.setMinSize(circle_size, circle_size)
        label.setMaxSize(circle_size, circle_size)


        if y == controller.game.getCurrentTurn() then
            label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_animation.gif").toExternalForm(), image_size, image_size, true, true)))
            //label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_" + currentStoneVector(x).stringRepresentation + ".png").toExternalForm, image_size, image_size, true, true)))
            //label.setEffect(glow)
        else if y == (controller.game.getCurrentTurn() + 1) then
            //label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_test.gif").toExternalForm(), image_size, image_size, true, true)))
            label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_" + currentStoneVector(x).stringRepresentation + ".png").toExternalForm, image_size, image_size, true, true)))
        else 
            label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_" + controller.game.field.matrix.cell(y, x).stringRepresentation + ".png").toExternalForm, image_size, image_size, true, true)))
        
        // label.setOnMouseEntered(e => label.setGraphic(new ImageView(new Image("circle_back_light_512.png", image_size, image_size, true, true))))
        // label.setOnMouseExited(e => label.setGraphic(new ImageView(new Image("circle_back_dark_512.png", image_size, image_size, true, true))))
        // label.setOnMouseClicked(e => label.setGraphic(new ImageView(new Image("test.gif", image_size, image_size, true, true))))

        this.getChildren().add(label)
    }

    class Hints(x: Int, y: Int) extends StackPane {
        val circle_size = 60
        val image_size = circle_size - 5
        
        //val reflection = new Reflection()
        //reflection.setBottomOpacity(0.0)
        //reflection.setTopOpacity(0.5)
        //reflection.setFraction(0.5)
        //reflection.setTopOffset(-1.0)
        

        this.setMinSize(circle_size, circle_size)
        this.setPrefSize(circle_size, circle_size)
        this.setMaxSize(circle_size, circle_size)
        val label = new Label("")
        label.setPrefSize(circle_size, circle_size)
        label.setMinSize(circle_size, circle_size)
        label.setMaxSize(circle_size, circle_size)


        label.setGraphic(new ImageView(new Image(getClass.getResource("/hintstones/hstone_" + controller.game.field.hmatrix.cell(y, x).stringRepresentation + ".png").toExternalForm(), image_size, image_size, true, true)))
        //label.setEffect(reflection)
        // label.setOnMouseEntered(e => label.setGraphic(new ImageView(new Image("circle_back_light_512.png", image_size, image_size, true, true))))
        // label.setOnMouseExited(e => label.setGraphic(new ImageView(new Image("circle_back_dark_512.png", image_size, image_size, true, true))))
        // label.setOnMouseClicked(e => label.setGraphic(new ImageView(new Image("test.gif", image_size, image_size, true, true))))

        this.getChildren().add(label)
    }
    
    override def stopApp(): Unit = {
        controller.request(QuitStateEvent())
    }
}
