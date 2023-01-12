/**
  * GUI.scala
  * 
  * Class for the GUI of the Mastermind game.
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind
package aview


//****************************************************************************** IMPORTS
import scalafx.application.JFXApp3
import scalafx.application.Platform
import scalafx.scene.layout.{StackPane, CornerRadii, GridPane}
import scalafx.scene.control.{Button, Label, Tooltip}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.Scene
import scalafx.stage.{Stage, Popup, FileChooser}
import scalafx.scene.ImageCursor
import scalafx.geometry.Insets
import scalafx.geometry.Pos._
import scalafx.Includes._

import controller.ControllerComponent.ControllerInterface
import model.GameComponent.GameBaseImpl._
import util._


//****************************************************************************** CLASS DEFINITION
class GUI(using controller: ControllerInterface) extends JFXApp3 with Observer {
    
  controller.add(this)

  var currentStoneVector: Vector[Stone] = Vector.from[Stone](Array.fill[Stone](controller.game.field.matrix.cols)(Stone("E")))
  var browseColors = 0
  val selectableColors = Vector("G", "R", "B", "Y", "P", "W")


  override def start() = {
    stage = new JFXApp3.PrimaryStage {
      icons += new Image(getClass.getResource("/logo.png").toExternalForm())
      resizable = false
      title.value = "Mastermind"
      scene = refreshScene()
    }
  }

  override def update = {
    /* Refresh scene with a runnable which is added to the threads event queue.
       This is needed cause Java/ScalaFX Threads must not be updated/interrupted from other threads */
    Platform.runLater(new Runnable() {
      override def run() = {
        stage.scene = refreshScene()
      }
    })
  }
    
  def refreshScene() : Scene = {
    
    new Scene(800, 960) {
      
      this.setOnScroll(e => {
        if (e.getDeltaY < 0) {
          browseColors = (browseColors + 1) % selectableColors.length
          this.setCursor(new ImageCursor(new Image(getClass.getResource("/coursers/courser_" + selectableColors(browseColors) + ".png").toExternalForm, 300, 300, true, true)))
        }
      })
      
      this.setCursor(new ImageCursor(new Image(getClass.getResource("/coursers/courser_" + selectableColors(browseColors) + ".png").toExternalForm, 300, 300, true, true)))
            
      val border = new GridPane()
      val stone_matrix = new GridPane()
            
      //get total width of grid-pane
      val hint_stone_matrix = new GridPane()
      hint_stone_matrix.setMaxWidth(300)
      hint_stone_matrix.setMaxHeight(300)
      hint_stone_matrix.setAlignment(CENTER)
      hint_stone_matrix.setHgap(10)
      hint_stone_matrix.setVgap(10)
      hint_stone_matrix.setPadding(Insets(10, 10, 10, 10))
      hint_stone_matrix.setStyle("-fx-background-color: #202225; -fx-background-radius: 10;")
            
      //@TODO: Realize as FOREACH loop
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
            
      //@TODO: Realize as FOREACH loop
      for (i <- 0 to controller.game.field.hmatrix.cols-1) {
        for (j <- 0 to controller.game.field.hmatrix.rows-1) {
          val stone = controller.game.field.cells(j, i)
          val entry = new Entry(i, j)
          stone_matrix.add(entry, i, j)
        }
      }
                  
      border.setAlignment(CENTER)
      border.gridLinesVisibleProperty().setValue(false)
      border.setVgap(20)
      border.setHgap(20)
            
      border.add(stone_matrix, 0, 1)
      border.add(hint_stone_matrix, 1, 1)
 
      border.setStyle("-fx-background-color: #2f3136;")
            
      //********************************************************** Buttons
      val checkButton = new Button_MasterMind("Check Code", checkCode_Button_Handler)
      checkButton.button.setMinWidth(stone_matrix.getMaxWidth()) 
      checkButton.button.setTooltip(new Tooltip("Check your code and get a hint"))
      border.add(checkButton.button, 0, 2, 1, 1)
            
      //*** Undo / Redo / Reset Buttons
      val urr_Grid = new GridPane()
      val urr_buttonSize = 96
            
      val undoButton = new Button_MasterMind("undo.png", undoCode_Button_Handler)
      undoButton.button.setMinWidth(urr_buttonSize)
      undoButton.alignmentInParent = CENTER_LEFT
      undoButton.button.setTooltip(new Tooltip("Undo your last move"))
      urr_Grid.add(undoButton.button, 0, 0)
            
      val spaceLabel_1 = new Label("  ")
      urr_Grid.add(spaceLabel_1, 1, 0)
            
      val redoButton = new Button_MasterMind("redo.png", redoCode_Button_Handler)
      redoButton.button.setMinWidth(urr_buttonSize)
      redoButton.alignmentInParent = CENTER_RIGHT
      redoButton.button.setTooltip(new Tooltip("Redo your last move"))
      urr_Grid.add(redoButton.button, 2, 0)
                                   
      val spaceLabel_2 = new Label("  ")
      urr_Grid.add(spaceLabel_2, 3, 0)
            
      val resetButton = new Button_MasterMind("reset.png", reset_Button_Handler)
      resetButton.button.setMinWidth(urr_buttonSize)
      resetButton.alignmentInParent = CENTER_LEFT
      resetButton.button.setTooltip(new Tooltip("Reset the game"))
      urr_Grid.add(resetButton.button, 4, 0)
            
      border.add(urr_Grid, 0, 3)
            
      //*** Help / Save / Load Buttons
      val hsl_Grid = new GridPane()
      val hsl_buttonSize = 96
            
      val helpButton = new Button_MasterMind("help.png", help_Button_Handler)
      helpButton.button.setMinWidth(hsl_buttonSize)
      helpButton.alignmentInParent = CENTER_RIGHT
      helpButton.button.setTooltip(new Tooltip("Get help"))
      hsl_Grid.add(helpButton.button, 0, 0)
            
      val spaceLabel_3 = new Label("  ")
      hsl_Grid.add(spaceLabel_3, 1, 0)
            
      val saveButton = new Button_MasterMind("save.png", save_Button_Handler)
      saveButton.button.setMinWidth(hsl_buttonSize)
      saveButton.alignmentInParent = CENTER_LEFT
      saveButton.button.setTooltip(new Tooltip("Save the game"))
      hsl_Grid.add(saveButton.button, 2, 0)
            
      val spaceLabel_4 = new Label("  ")
      hsl_Grid.add(spaceLabel_4, 3, 1)
            
      val loadButton = new Button_MasterMind("load.png", load_Button_Handler)
      loadButton.button.setMinWidth(hsl_buttonSize)
      loadButton.alignmentInParent = CENTER_RIGHT
      loadButton.button.setTooltip(new Tooltip("Load the game"))
      hsl_Grid.add(loadButton.button, 4, 0)
            
      border.add(hsl_Grid, 1, 3)
            
      // *** Label Current Turn
      val labelCurrentTurn = new Label("Remaining Turns: " + controller.game.getRemainingTurns())
            
      //Default Label Style
      val labelStyle_default = s"""
          -fx-text-fill: linear-gradient(#da1e28, #2625ff);
          -fx-font-size: 25px;
          -fx-font-weight: normal;
          -fx-padding: 7 9 7 9;
          -fx-background-color: #202225;
          -fx-background-radius: 10px;
          -fx-font-alignment: center; """
      labelCurrentTurn.style = labelStyle_default
      labelCurrentTurn.setAlignment(CENTER)
      labelCurrentTurn.setMinWidth(hint_stone_matrix.getMaxWidth())
      border.add(labelCurrentTurn, 1, 2)
            
      //************************************************************* Logo
      val img = new ImageView(new Image(getClass.getResource("/mastermind_logo.png").toExternalForm(), 
                              hint_stone_matrix.getMaxWidth() + stone_matrix.getMaxWidth(), 
                              100, true, true))
      img.alignmentInParent = CENTER
            
            
      val img_won = new ImageView(new Image(getClass.getResource("/won.png").toExternalForm(), 
                              hint_stone_matrix.getMaxWidth() + stone_matrix.getMaxWidth(), 
                              100, true, true))
            
      val img_loose = new ImageView(new Image(getClass.getResource("/loose.png").toExternalForm(), 
                              hint_stone_matrix.getMaxWidth() + stone_matrix.getMaxWidth(), 
                              100, true, true))

      // Change header image if game is won or lost
      if(controller.game.state.isInstanceOf[PlayerWin]) {
        border.add(img_won, 0, 0, 2, 1)
        checkButton.button.setDisable(true)
        undoButton.button.setDisable(true)
        redoButton.button.setDisable(true)
        helpButton.button.setDisable(true)
      } else if(controller.game.state.isInstanceOf[PlayerLose]) {
        border.add(img_loose, 0, 0, 2, 1)
        checkButton.button.setDisable(true)
        undoButton.button.setDisable(true)
        redoButton.button.setDisable(true)
        helpButton.button.setDisable(true)
      } else {
        border.add(img, 0, 0, 2, 1)
        checkButton.button.setDisable(false)
        undoButton.button.setDisable(false)
        redoButton.button.setDisable(false)
        helpButton.button.setDisable(false)
      }

      root = border
    }
  }
    
  //************************************************************ Button Handlers
        
  /**
    * This method is called when the check button is clicked
    */    
  def checkCode_Button_Handler() : Unit = {
    val check = currentStoneVector.filter(stone => stone.stringRepresentation == "E")
    if (check.length == 0) {
      val hints = controller.game.getCode().compareTo(currentStoneVector)
      val tmp = currentStoneVector
      for (i <- 0 until controller.game.field.matrix.cols) {
        currentStoneVector = currentStoneVector.updated(i, Stone("E"))
      }
      controller.placeGuessAndHints(tmp, hints, controller.game.getCurrentTurn())
            
      if hints.forall(p => p.stringRepresentation.equals("R")) then
        return controller.request(PlayerWinStateEvent())
      else if controller.game.getRemainingTurns().equals(0) then
        return controller.request(PlayerLoseStateEvent())
      else
        return controller.request(PlayerInputStateEvent())
    }
  }
    
  /**
    * This method is called when the undo button is clicked
    */
  def undoCode_Button_Handler() : Unit = {
    controller.undo
  }
    
  /**
    * This method is called when the redo button is clicked
    */
  def redoCode_Button_Handler() : Unit = {
    controller.redo
  }
    
  /**
   * This method is called when the help button is clicked
   */
  def help_Button_Handler() : Unit = {
    controller.request(HelpStateEvent())
    val popup = new Popup()
        
    val helpGrid = new GridPane()
    helpGrid.setPrefWidth(600)
    helpGrid.setStyle("-fx-background-color: #202225; " +
                      "-fx-padding: 7 9 7 9; " +
                      "-fx-background-radius: 10px; " +
                      "-fx-border-style: solid; " +
                      "-fx-border-width: 2; " +
                      "-fx-border-color: linear-gradient(#da1e28, #2625ff); " +
                      "-fx-border-radius: 10px; " +
                      "-fx-font-alignment: center;")
        
    val helpLabel = new Label("You need some help? Here you go!")
    helpLabel.setAlignment(CENTER)
    helpLabel.setStyle("-fx-background-color: #202225; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 7 9 7 9; -fx-background-radius: 10px; -fx-font-alignment: center;")
                          
    val scrollGrid = new GridPane()
    val imgScroll = new ImageView(new Image(getClass.getResource("/info/scroll.png").toExternalForm(), 50, 50, true, true))
    val labelScroll = new Label("Scroll to change the color of the courser to place \ndifferent stones")
    labelScroll.setStyle("-fx-background-color: #202225; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: normal; -fx-padding: 7 9 7 9; -fx-background-radius: 10px; -fx-font-alignment: center;")
        
    scrollGrid.add(imgScroll, 0, 0)
    scrollGrid.add(labelScroll, 1, 0)
        
    val clickGrid = new GridPane()
    val imgClick = new ImageView(new Image(getClass.getResource("/info/left-click.png").toExternalForm(), 50, 50, true, true))
    val labelClick = new Label("To place a stone, click on a black field \n(You will make it, we believe in you) ")
    labelClick.setStyle("-fx-background-color: #202225; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: normal; -fx-padding: 7 9 7 9; -fx-background-radius: 10px; -fx-font-alignment: center;")
    clickGrid.add(imgClick, 0, 0)
    clickGrid.add(labelClick, 1, 0)
        
    val blackStoneGrid = new GridPane()
    val imgBlackStone = new ImageView(new Image(getClass.getResource("/stones/stone_A.png").toExternalForm(), 50, 50, true, true))
    val labelBlackStone = new Label("These stones show the current row in which you \nmay place stones")
    labelBlackStone.setStyle("-fx-background-color: #202225; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: normal; -fx-padding: 7 9 7 9; -fx-background-radius: 10px; -fx-font-alignment: center;")
    blackStoneGrid.add(imgBlackStone, 0, 0)
    blackStoneGrid.add(labelBlackStone, 1, 0)
        
    val whiteHintStoneGrid = new GridPane()
    val imgWhiteHintStone = new ImageView(new Image(getClass.getResource("/hintstones/hstone_W.png").toExternalForm(), 50, 50, true, true))
    val labelWhiteHintStone = new Label("Show there is a stone of the right color in the current \nrow but not in the right position")
    labelWhiteHintStone.setStyle("-fx-background-color: #202225; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: normal; -fx-padding: 7 9 7 9; -fx-background-radius: 10px; -fx-font-alignment: center;")
    whiteHintStoneGrid.add(imgWhiteHintStone, 0, 0)
    whiteHintStoneGrid.add(labelWhiteHintStone, 1, 0)
        
    val redHintStoneGrid = new GridPane()
    val imgRedHintStone = new ImageView(new Image(getClass.getResource("/hintstones/hstone_R.png").toExternalForm(), 50, 50, true, true))
    val labelRedHintStone = new Label("Show there is a stone of the right color and in the right \nposition in the current row (You so good!)")
    labelRedHintStone.setStyle("-fx-background-color: #202225; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: normal; -fx-padding: 7 9 7 9; -fx-background-radius: 10px; -fx-font-alignment: center;")
    redHintStoneGrid.add(imgRedHintStone, 0, 0)
    redHintStoneGrid.add(labelRedHintStone, 1, 0)
        
    val labelEnd = new Label("Thats all? Yeah not that complicated!")
    labelEnd.setStyle("-fx-background-color: #202225; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: normal; -fx-padding: 7 9 7 9; -fx-background-radius: 10px; -fx-font-alignment: center;")
        
    val helpExit = new Button_MasterMind("Exit", () => popup.hide())
    helpExit.alignmentInParent = CENTER_RIGHT
    helpExit.button.setPrefWidth(helpGrid.getPrefWidth() - 10)
        
    helpGrid.add(helpLabel, 0, 0)
    helpGrid.add(scrollGrid, 0, 1)
    helpGrid.add(clickGrid, 0, 2)
    helpGrid.add(blackStoneGrid, 0, 3)
    helpGrid.add(whiteHintStoneGrid, 0, 4)
    helpGrid.add(redHintStoneGrid, 0, 5)
    helpGrid.add(labelEnd, 0, 6)
    helpGrid.add(helpExit.button, 0, 7)
        
    helpGrid.setVgap(10)    
        
    popup.getContent().add(helpGrid)
    popup.show(stage)
  }
    
  def reset_Button_Handler() : Unit = {
    controller.reset
  }

  def save_Button_Handler() : Unit = {
    controller.save
  }

  def load_Button_Handler() : Unit = {
    controller.load
  }    
    
  /**
    * This class is a wrapper for the Button class. It defines the style of the button and takes a 
    * function as parameter (Higher Order Function Principle)
    * @param text Text the button should display
    * @param f    Function that should be executed when the button is clicked
    */
  class Button_MasterMind(text: String, f: () => Unit) extends Button {
      
    val buttonText = text
        
    /* Defines the DEFAULT style of the button in CSS */
    val buttonStyle_default = s"""
      -fx-background-color: #202225, linear-gradient(#20262b, #191d22);
      -fx-background-radius: 5,4,3,5;
      -fx-text-fill: linear-gradient(#da1e28, #2625ff);
      -fx-font-size: 25px;
      -fx-padding: 5 15 5 15;"""
        
    /* Defines the HOVER style of the button in CSS */    
    val buttonStyle_hover = s"""
      -fx-background-color: #202225, linear-gradient(#20262b, #191d22), radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5,4,3,5;
      -fx-background-insets: 0,1,2,0;
      -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );
      -fx-text-fill: linear-gradient(#da1e28, #2625ff);
      -fx-font-size: 25px;
      -fx-padding: 5 15 5 15;"""
        
    /* Defines the CLICK style of the button in CSS */
    val buttonStyle_click = s"""
      -fx-background-color: #202225, linear-gradient(#20262b, #191d22), radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));
      -fx-background-radius: 5,4,3,5;
      -fx-background-insets: 0,1,2,0;
      -fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.9) , 1, 0.0 , 0 , 1 );
      -fx-text-fill: linear-gradient(#da1e28, #2625ff);
      -fx-font-size: 25px;
      -fx-padding: 5 15 5 15;"""    

    val button = new Button() {
      
      if(buttonText.contains(".png")) {
        val button_img = new ImageView(new Image(getClass.getResource("/buttons/" + buttonText).toExternalForm(), 32, 32,true, true ))
        this.setGraphic(button_img)
      } else {
        this.setText(buttonText)
      }
      
      this.setOnMouseClicked(e => {
        this.setStyle(buttonStyle_click)            
        //calls the higher order function (HOF)
        f()
      })
            
      /* Set style when hovering over button */
      this.setOnMouseEntered(e => {this.setStyle(buttonStyle_hover)})
            
      /* Set style when leaving button */
      this.setOnMouseExited(e =>  {this.setStyle(buttonStyle_default)})  
    }
    
    /* Set default style */    
    button.setStyle(buttonStyle_default)
  }

    
  /**
    * Game Stone Class
    *
    * @param x
    * @param y
    */
  class Entry(x: Int, y: Int) extends StackPane {
    val circle_size = 60
    val image_size = circle_size - 5

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

    if (controller.game.state.isInstanceOf[PlayerWin]) then
      label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_win.png").toExternalForm(), image_size, image_size, true, true)))
    else if (controller.game.state.isInstanceOf[PlayerLose]) then
      label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_E.png").toExternalForm(), image_size, image_size, true, true)))
    else if y == controller.game.getCurrentTurn() then
      label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_animation.gif").toExternalForm(), image_size, image_size, true, true)))
    else if y == (controller.game.getCurrentTurn() + 1) then
      label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_" + currentStoneVector(x).stringRepresentation + ".png").toExternalForm, image_size, image_size, true, true)))
    else 
      label.setGraphic(new ImageView(new Image(getClass.getResource("/stones/stone_" + controller.game.field.matrix.cell(y, x).stringRepresentation + ".png").toExternalForm, image_size, image_size, true, true)))
    this.getChildren().add(label)
  }

    
  /**
    * Hint Stone class
    *
    * @param x 
    * @param y
    */
  class Hints(x: Int, y: Int) extends StackPane {
    val circle_size = 60
    val image_size = circle_size - 5

    this.setMinSize(circle_size, circle_size)
    this.setPrefSize(circle_size, circle_size)
    this.setMaxSize(circle_size, circle_size)
    val label = new Label("")
    label.setPrefSize(circle_size, circle_size)
    label.setMinSize(circle_size, circle_size)
    label.setMaxSize(circle_size, circle_size)

    if (controller.game.state.isInstanceOf[PlayerWin]) then
      label.setGraphic(new ImageView(new Image(getClass.getResource("/hintstones/hstone_R.png").toExternalForm(), image_size, image_size, true, true)))
    else if(controller.game.state.isInstanceOf[PlayerLose]) then
      label.setGraphic(new ImageView(new Image(getClass.getResource("/hintstones/hstone_E.png").toExternalForm(), image_size, image_size, true, true)))
    else
      label.setGraphic(new ImageView(new Image(getClass.getResource("/hintstones/hstone_" + controller.game.field.hmatrix.cell(y, x).stringRepresentation + ".png").toExternalForm(), image_size, image_size, true, true)))

    this.getChildren().add(label)
  }
    
  override def stopApp(): Unit = {
    controller.request(QuitStateEvent())
    System.exit(0)
  }
}
