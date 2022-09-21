package de.htwg.se.mastermind.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec{
  "A Field" should {
    "have a bar as String of form '+---+---+---+---+" in {                         // Test default values: bar
      bar() should be("+---+---+---+---+" + eol)
    }
    "have cells as String of form '|   |   |   |   |'" in {
      cells() should be("|   |   |   |   |")
    }
    "have a hint bar as String of form '[   |   |   |   ]'" in {
      hint_bar() should be("   [   |   |   |   ]" + eol)
    }
    "have a mesh in form of a Mastermind field" in {
      mesh() should be
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