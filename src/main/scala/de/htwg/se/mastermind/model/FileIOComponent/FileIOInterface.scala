package de.htwg.se.mastermind

package model
package FileIOComponent

import GameComponent.GameInterface
import GameComponent.GameBaseImpl.{Field, Stone, Matrix}
import play.api.libs.json.JsObject


trait FileIOInterface {

  def load(game: GameInterface): GameInterface

  def save(game: GameInterface): Unit

  def gameToJson(game: GameInterface): JsObject
}

