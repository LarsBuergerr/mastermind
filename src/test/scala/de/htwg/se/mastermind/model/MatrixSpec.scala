/**
  * MatrixSpec.scala
  */

//********************************************************************** PACKAGE  
package de.htwg.se.mastermind
package model

//********************************************************************** IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class MatrixSpec extends AnyWordSpec {
    "A Matrix" should {
        "being created with two dimensions and a filling " in {
            val matrix = new Matrix[Option[Stone]](4, 4, Some(Stone("E")))
            matrix.rows should be(4)
            matrix.cols should be(4)
            matrix.m(0).forall(p => p == Some(Stone("E"))) should be(true)
            
            //matrix.m(1).forall(p => p == Some(Stone.apply("E"))) should be(true)
            //matrix.m(2).forall(p => p == Some(Stone.apply("E"))) should be(true)
            //matrix.m(3).forall(p => p == Some(Stone.apply("E"))) should be(true)
        }
        //"have a cell method that returns the contents of the selected cell" in {
        //    val matrix = new Matrix[Option[Stone]](4, 4, Some(Stone.Blue))
        //    matrix.cell(0, 0) should be(Some(Stone.Blue))
        //    matrix.cell(0, 1) should be(Some(Stone.Blue))
        //    matrix.cell(0, 2) should be(Some(Stone.Blue))
        //    matrix.cell(0, 3) should be(Some(Stone.Blue))
        //
        //    matrix.cell(1, 0) should be(Some(Stone.Blue))
        //    matrix.cell(1, 1) should be(Some(Stone.Blue))
        //    matrix.cell(1, 2) should be(Some(Stone.Blue))
        //    matrix.cell(1, 3) should be(Some(Stone.Blue))
        //}
//
        //"have a filling method which fills the whole matrix with the given color" in {
        //    val matrix = new Matrix[Option[Stone]](4, 4, Some(Stone.Empty))
//
        //    matrix.m(0).forall(p => p == Some(Stone.Empty)) should be(true)
        //    matrix.m(1).forall(p => p == Some(Stone.Empty)) should be(true)
        //    matrix.m(2).forall(p => p == Some(Stone.Empty)) should be(true)
        //    matrix.m(3).forall(p => p == Some(Stone.Empty)) should be(true)
//
        //    val filledMatrix = matrix.fill(Some(Stone.Blue))
        //
        //    filledMatrix.m(0).forall(p => p == Some(Stone.Blue)) should be(true)
        //    filledMatrix.m(1).forall(p => p == Some(Stone.Blue)) should be(true)
        //    filledMatrix.m(2).forall(p => p == Some(Stone.Blue)) should be(true)
        //    filledMatrix.m(3).forall(p => p == Some(Stone.Blue)) should be(true)
        //}
//
        //"have a row method which returns the vector at the given row" in {
        //    val matrix = new Matrix[Option[Stone]](4, 4, Some(Stone.Empty))
        //    val vector = matrix.row(0)
//
        //    vector.forall(p => p == Some(Stone.Empty)) should be(true)
        //}
        //"have a replaceCell method which should replace the selected cell with a chosen Stone" in {
        //    val matrix = new Matrix[Option[Stone]](4, 4, Some(Stone.Empty))
        //    matrix.cell(0, 0) should be(Some(Stone.Empty))
        //    matrix.cell(3, 3) should be(Some(Stone.Empty))
//
        //    val newMatrix = matrix.replaceCell(0, 0, Some(Stone.Blue)).replaceCell(3, 3, Some(Stone.Blue))
        //    newMatrix.cell(0, 0) should be(Some(Stone.Blue))
        //    newMatrix.cell(3, 3) should be(Some(Stone.Blue))
        //}
        //"have a replaceRow method which should replace the whole vector of a given row with chosen Stones" in {
        //    val matrix = new Matrix[Option[Stone]](4, 4, Some(Stone.Empty))
        //    matrix.m(0).forall(p => p == Some(Stone.Empty)) should be(true)
//
        //    val vec = Vector[Option[Stone]](Some(Stone.Blue), Some(Stone.Blue), Some(Stone.Blue), Some(Stone.Blue))
//
        //    val newMatrix = matrix.replaceRow(0, vec)
        //    newMatrix.m(0).forall(p => p == Some(Stone.Empty)) should be(false)
        //    newMatrix.m(0).forall(p => p == Some(Stone.Blue)) should be(true)
        //}

    }
}