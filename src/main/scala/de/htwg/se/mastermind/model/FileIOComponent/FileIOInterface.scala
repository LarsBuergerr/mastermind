package de.htwg.se.mastermind

package model
package FileIOComponent

import GameComponent.GameInterface


trait FileIOInterface {

  def load: GameInterface

  def save(game: GameInterface): Unit
}

