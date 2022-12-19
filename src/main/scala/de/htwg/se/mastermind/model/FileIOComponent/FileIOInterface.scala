package de.htwg.se.mastermind

package model
package FileIOComponent

import GameComponent.GameInterface
import GameComponent.GameBaseImpl.{Field, Stone, Matrix}


trait FileIOInterface {

  def load: Matrix[Stone]

  def save(game: GameInterface): Unit
}

