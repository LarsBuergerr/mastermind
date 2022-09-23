/**
  * Represents a generated code the player has to solve
  */
  
package de.htwg.se.mastermind.model

/**
  * Code that has to be solved in game
  * Default Constructor: for test and when in 2 Player Mode
  * Auxiliary Constructor: for Single Player Mode (generates Random code)
  *
  * @param code
  */
case class Code(code: Vector[Stone]):
  
  /* AUX CON: used to generate a vector with random values*/
  def this(size: Int = 4) = this(Vector.fill(size)(Stone.random))
  
  
  val size = code.size
  
  
  override def toString(): String = code.map(_.toString()).mkString(" | ")
  

  /**
    * Compares a generated code with the input code done by the user
    *
    * @param userInput  UserInput as Stone Vector
    * @return HintStone Vector (All black: code are equal)
    */
  def compareTo(userInput: Vector[Stone]):Vector[HintStone] = {
    
    if(this.code.size != userInput.size){                                       /* Shouldn't be possible but check anyway (Safety First)*/
      //@todo throw exception?
    }
    
    var retVal: Vector[HintStone] = Vector()
    
    if(this.code.equals(userInput)){                                            /* Check if vectors are equal */
      //@todo multiple return points a problem?
      return Vector.fill(size)(HintStone.Black)
    }
    
    /* Count Variables */
    var equalCount = 0
    var presentCount = 0
    var equalsList: List[Int] = List()

    /* Check code */
    for(i <- 0 to (size-1)){
      
      /* Check on equals and save position in list */
      if(this.code(i).equals(userInput(i)))
      {
        equalCount = equalCount + 1
        equalsList = equalsList.appended(i)
      }
      else
      {
        /* Check if other match who were not equals already */
        for(j <- 0 to (size-1)){    
          if((i != j) && (!equalsList.contains(j)) && (this.code(i).equals(userInput(j)))){
            presentCount = presentCount + 1
          }
        }
      }
    }
    
    /* Build return vector */
    //@todo could be cleaner
    val equalsVector = Vector.fill(equalCount)(HintStone.Black)
    val presentVector = Vector.fill(presentCount)(HintStone.White)
    val stepVector = equalsVector.concat(presentVector)
    val diffCount = size - equalCount - presentCount
    val emptyVec = Vector.fill(diffCount)(HintStone.Empty)
    val endVector = stepVector.concat(emptyVec)
    
    return endVector
  }