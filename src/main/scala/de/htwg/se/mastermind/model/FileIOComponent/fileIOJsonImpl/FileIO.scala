package de.htwg.se.mastermind

package model
package FileIOComponent
package fileIOJsonImpl

import GameComponent.GameInterface
import GameComponent.GameBaseImpl.{Field, Stone, Matrix}

class FileIO extends FileIOInterface {
  override def load: GameInterface = ???
  override def save(game: GameInterface): Unit = ???
}
