/**
  * ███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗ ███╗   ███╗██╗███╗   ██╗██████╗ 
  * ████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██╔══██╗
  * ██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝██╔████╔██║██║██╔██╗ ██║██║  ██║
  * ██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗██║╚██╔╝██║██║██║╚██╗██║██║  ██║
  * ██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██████╔╝
  * ╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝ 
  *                                                                                  
  * Mastermind.scala
  * Created by: LarsBuergerr & Smokey95
  * 
  * Project is part of the course "Software Engineering" at HTWG Konstanz
  * 
  * This is the main class of the Mastermind game.
  * 
  * © LarsBuergerr & Smokey95
  */

//****************************************************************************** PACKAGE  
package de.htwg.se.mastermind


//****************************************************************************** IMPORTS
import controller.ControllerComponent.ControllerInterface
import controller.ControllerComponent.ControllerBaseImpl.Controller
//import controller.ControllerComponent.ControllerMockImpl.Controller

import model.GameComponent.GameInterface
import model.GameComponent.GameBaseImpl.Game
//import model.GameComponent.GameMockImpl.Game

import model.GameComponent.GameBaseImpl.Code

import model.GameModeComponent.GameModeInterface
import model.GameModeComponent.GameModeBaseImpl.GameMode
//import model.GameModeComponent.GameModeMockImpl.GameMode

import model.FileIOComponent.FileIOInterface
//import model.FileIOComponent.fileIOyamlImpl.FileIO
import model.FileIOComponent.fileIOjsonImpl.FileIO
//import model.FileIOComponent.fileIOxmlImpl.FileIO


//****************************************************************************** OBJECT DEFINITION
object MastermindModule {
  val field = GameMode.selectMode
  given GameInterface       = Game(field, new Code(field.matrix.cols), 0)
  given ControllerInterface = Controller()
  given FileIOInterface     = FileIO()
}