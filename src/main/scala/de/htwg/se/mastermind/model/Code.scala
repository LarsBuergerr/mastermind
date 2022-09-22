/**
  * 
  */
  
package de.htwg.se.mastermind.model

/**
  * Random code that has to be solved in game
  * Default Constructor only for tests!
  *
  * @param code
  */
case class Code(code: Vector[Stone]):
  
  /* AUX CON: used to generate a vector with random values*/
  def this(size: Int) = this(Vector.fill(size)(Stone.random))
  
  val size = code.size
  
  override def toString(): String = code.map(_.toString()).mkString(" | ")
  
  def compare(userInput: Vector[Stone]):Vector[HintStone] =
  {
    
    if(this.code.size != userInput.size){                                       /* Shouldn't be possible but check anyway (Safety First)*/
      //@todo throw exception?
    }
    
    if(this.code.equals(userInput)){                                            /* Check if vectors are equal */
      //@todo multiple return points a problem?
      return Vector.fill(size)(HintStone.Black)
    }
    
    var equalCount = 0
    var presentCount = 0

    for(i <- 0 to (size-1)){
      
      if(this.code(i).equals(userInput(i)))
      {
        equalCount = equalCount + 1
      }
      
      for(j <- 0 to (size-1)){    
        if((i != j) && (this.code(i).equals(userInput(j)))){
          presentCount = presentCount + 1
        }
      }
    }
    
    val equalsVector = Vector.fill(equalCount)(HintStone.Black)
    val presentVector = Vector.fill(presentCount)(HintStone.White)
    val stepVector = equalsVector.concat(presentVector)
    val diffCount = size - equalCount - presentCount
    val emptyVec = Vector.fill(diffCount)(HintStone.Empty)
    val endVector = stepVector.concat(emptyVec)
    
    return endVector
  }