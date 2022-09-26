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
    //var equalCount = 0
    //var presentCount = 0
    
    val equalsList = compareToEqual(userInput, (size -1), List())
    
    val presentList = compareToPresent(userInput, (size -1), equalsList, List())
    
    
    /* Build return vector */
    //@todo could be cleaner
    val equalsVector = Vector.fill(equalsList.size)(HintStone.Black)
    val presentVector = Vector.fill(presentList.size)(HintStone.White)
    val stepVector = equalsVector.concat(presentVector)
    val diffCount = size - equalsList.size - presentList.size
    val emptyVec = Vector.fill(diffCount)(HintStone.Empty)
    val endVector = stepVector.concat(emptyVec)
    
    return endVector
  }
  
  
  def compareToEqual(inputUser: Vector[Stone], currentPos: Int, equalsList: List[Int]): (List[Int]) = {
    
    if(currentPos < 0){
      return equalsList
    }
    
    if(this.code(currentPos).equals(inputUser(currentPos))){
      compareToEqual(inputUser, (currentPos - 1), equalsList.appended(currentPos))
    }
    else{
      compareToEqual(inputUser, (currentPos - 1), equalsList)
    }
  }
  
  
  def compareToPresent(inputUser: Vector[Stone], currentPos: Int, equalsList: List[Int], presentList: List[Int]): (List[Int]) = {
    
    if(currentPos < 0){
      return presentList
    }
    
    if(equalsList.contains(currentPos)){
      compareToPresent(inputUser, (currentPos - 1), equalsList, presentList)
    }
    else{
      for(i <- 0 to (size - 1)){
        if(!equalsList.contains(i) && !presentList.contains(i) && (i != currentPos)){
          if(inputUser(currentPos).equals(this.code(i))){
            compareToPresent(inputUser, (currentPos - 1), equalsList, presentList.appended(i))
          }
        }
      }
      compareToPresent(inputUser, (currentPos - 1), equalsList, presentList)
    }
  }