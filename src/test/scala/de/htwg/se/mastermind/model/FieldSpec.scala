package de.htwg.se.mastermind.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec{
  "A default Field" should {
    val defaultField = new Field()
    "have a bar as String of form '+---+---+---+---+" in {                         // Test default values: bar
      defaultField.bar() should be("+---+---+---+---+" + eol)
    }
    "have cells as String of form '|   |   |   |   |   [   |   |   |   ]'" in {
      defaultField.cells() should be("|   |   |   |   |   [   |   |   |   ]" + eol)
    }
    "have a mesh in form of a Mastermind field" in {
      defaultField.mesh() should be
      ("""+---+---+---+---+
          |   |   |   |   |   [   |   |   |   ]
          +---+---+---+---+
          |   |   |   |   |   [   |   |   |   ]
          +---+---+---+---+
          |   |   |   |   |   [   |   |   |   ]
          +---+---+---+---+
          |   |   |   |   |   [   |   |   |   ]
          +---+---+---+---+
          |   |   |   |   |   [   |   |   |   ]
          +---+---+---+---+
          |   |   |   |   |   [   |   |   |   ]
          +---+---+---+---+""")
    }
  }
}