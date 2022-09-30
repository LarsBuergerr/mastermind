package de.htwg.se.mastermind
package aview


import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.Stone

class TUISpec extends AnyWordSpec {
    "A default TUI should have a default controller" in {
        val defaultTUI = new TUI()

        defaultTUI.controller.field.cols should be(4)
        defaultTUI.controller.field.rows should be(6)

        defaultTUI.loopCount should be(0)
        defaultTUI.SUCCESS_VAL should be(0)
    }
    "A default TUI should have a parseInput method that parses the input and returns an Int value" in {
        val defaultTUI = new TUI()

        defaultTUI.controller.field.matrix.m(0).forall(p => p == Stone.Empty) should be(true)
        defaultTUI.controller.field.matrix.m(0).forall(p => p == Stone.Red) should be(false)

        defaultTUI.parseInput("rrrr", 0) should be(0)
        defaultTUI.controller.field.matrix.m(0).forall(p => p == Stone.Red) should be(true)
        defaultTUI.controller.field.matrix.m(0).forall(p => p == Stone.Empty) should be(false)
    }
    "A default TUI should have a buildVector method that returns a vector with the given input" in {
        val defaultTUI = new TUI()
        val vector: Vector[Stone] = Vector()
        val input = "rrrr"
        val charArray = input.toCharArray()
    
        val filledVector = defaultTUI.buildVector(vector, charArray)

        filledVector.forall(p => p == Stone.Red) should be(true)
        filledVector.forall(p => p == Stone.Empty) should be(false)
        filledVector.size should be(4)
    }
}