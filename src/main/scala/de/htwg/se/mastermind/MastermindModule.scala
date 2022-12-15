package de.htwg.se.mastermind


import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.google.inject.TypeLiteral
import controller.ControllerComponent._
import controller.ControllerComponent.ControllerBaseImpl.Controller
import model.GameComponent.GameInterface
import model.GameComponent.GameBaseImpl.Game
import util.GameModeComponent.GameModeBaseImpl.GameMode
import util.GameModeComponent.GameModeInterface

class MastermindModule extends AbstractModule {
  override def configure(): Unit = {
    // bind(classOf[ControllerInterface]).to(classOf[Controller])
    // bind(classOf[GameInterface]).to(classOf[Game])
    // bind[ControllerInterface](new TypeLiteral[ControllerInterface] {}).to(classOf[Controller])
    
    //bind[GameModeInterface](new TypeLiteral[GameModeInterface] {}).toInstance(GameMode)
    bind[GameInterface](new TypeLiteral[GameInterface] {}).toInstance(Game(GameMode.selectMode))
    bind[ControllerInterface](new TypeLiteral[ControllerInterface] {}).to(classOf[Controller])
  }
}