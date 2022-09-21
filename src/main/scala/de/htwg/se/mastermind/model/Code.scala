package de.htwg.se.mastermind.model

/* Class for code to solve */
case class Code(code: Vector[Stone]):
  
  /* AUX CON: used to generate a vector with random values*/
  def this(size: Int) = this(Vector.fill(size)(Stone.random))
  
  val size = code.size
  