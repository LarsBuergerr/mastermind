//package de.htwg.se.mastermind
//package aview
//
//
//import org.scalatest.wordspec.AnyWordSpec
//import org.scalatest.matchers.should.Matchers._
//import model.{Stone, Game}
//import controller.Controller
//import util.GameMode
//
//class TUISpec extends AnyWordSpec {
//    "A default TUI should have a default controller" in {
//        //val defaultTUI = new TUI(new Controller(new Game(GameMode.strategy_medium)))
////
//        //defaultTUI.controller.game.field.cols should be(4)
//        //defaultTUI.controller.game.field.rows should be(6)
//
//        //defaultTUI.loopCount should be(0)
//        //defaultTUI.SUCCESS_VAL should be(0)
//        //defaultTUI.EXIT_VAL    should be(1)
//        //defaultTUI.WIN_VAL     should be(2)
//        //defaultTUI.ERROR_VAL   should be(-1)
//        //defaultTUI.LOOSE_VAL   should be(3)
//
//    }
//    "A default TUI should have a parseInput method that parses the input and returns an Int value" in {
//        //val defaultTUI = new TUI(new Controller(new Game(GameMode.strategy_medium)))
////
//        //defaultTUI.controller.game.field.matrix.m(0).forall(p => p == Stone.Empty) should be(true)
//        //defaultTUI.controller.game.field.matrix.m(0).forall(p => p == Stone.Red) should be(false)
////
//        ////defaultTUI.parseInput("rrrr", 0)
//        ////defaultTUI.controller.field.matrix.m(0).forall(p => p == Stone.Red) should be(true)
//        ////defaultTUI.controller.field.matrix.m(0).forall(p => p == Stone.Empty) should be(false)
//    }//
//    "A default TUI should have a buildVector method that returns a vector with the given input" in {
//        //val defaultTUI = new TUI()
//        //val vector: Vector[Stone] = Vector()
//        //val vector2: Vector[Stone] = Vector()
//        //val input = "rrrr"
//        //val charArray = input.toCharArray()
//    //
//        //val filledVector = defaultTUI.buildVector(vector, charArray)
////
//        //filledVector.forall(p => p == Stone.Red) should be(true)
//        //filledVector.forall(p => p == Stone.Empty) should be(false)
//        //filledVector.size should be(4)
////
//        //val input2 = "gbyw"
//        //val charArray2 = input2.toCharArray()
////
//        //val filledVector2 = defaultTUI.buildVector(vector2, charArray2)
////
//        //filledVector2(0) should be(Stone.Green)
//        //filledVector2(1) should be(Stone.Blue)
//        //filledVector2(2) should be(Stone.Yellow)
//        //filledVector2(3) should be(Stone.White)
////
//        //filledVector2.size should be(4)
//        //
//    }
//    "A default TUI should have different Values for different states of the game" in {
//        //val defaultTUI = new TUI()
//        //
//        //defaultTUI.parseInput("wypg", 0) should be(0)
//        //defaultTUI.parseInput("ggggg", 1) should be(-1)
//        //defaultTUI.parseInput("", 2) should be(-1)
//        //defaultTUI.parseInput("h", 3) should be(-1)
//        //defaultTUI.parseInput("q", 4) should be(1)
//    }
//
//}