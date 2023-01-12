/**
  * Matrix.scala
  */

//****************************************************************************** PACKAGE
package de.htwg.se.mastermind
package model
package GameComponent
package GameBaseImpl


//****************************************************************************** IMPORTS


//****************************************************************************** CLASS DEFINITION
case class Matrix[T] (m: Vector[Vector[T]]):
  
  def this(rows: Int,cols: Int, filling: T) = this(Vector.tabulate(rows, cols) { (row, col) => filling })
  
  val rows: Int = m.size
  
  val cols: Int = m.head.size

  def cell(row: Int, col: Int): T = m(row)(col)
  
  def row(row: Int) = m(row)
  
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(rows, cols) { (row, col) => filling })
  
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(m.updated(row, m(row).updated(col, cell)))
  
  def replaceRow(row: Int, vec: Vector[T]): Matrix[T] = copy(m.updated(row, vec))