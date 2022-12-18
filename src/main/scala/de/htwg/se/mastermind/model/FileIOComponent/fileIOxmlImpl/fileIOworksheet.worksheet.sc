import de.htwg.se.mastermind.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.mastermind.model.GameComponent.GameBaseImpl.Matrix
import de.htwg.se.mastermind.model.GameComponent.GameBaseImpl.Field
import de.htwg.se.mastermind.model.GameComponent.GameBaseImpl.Stone
import de.htwg.se.mastermind.model.FileIOComponent.fileIOxmlImpl.FileIO

val game = Game(new Field(4, 4, Stone(" ")))
val fileIO = new FileIO()

fileIO.save(game)


