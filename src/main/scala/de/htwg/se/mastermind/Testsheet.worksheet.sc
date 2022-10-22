import de.htwg.se.mastermind.model._
//import scala.util.Success
//import scala.util.Try
//import scala.util.Failure
//import scala.io.StdIn.readLine

val x = 5 + 1
val y = RandomNumberGenerator()

val defaultField = new Field()
println(defaultField)

val smallField = new Field(1, 1)
println(smallField)
/*------------------------------------------------------------------------------ Code.scala tests*/
val c1 = new Code(4)
c1.size

val r  = scala.util.Random
r.nextInt()

val stone = Stone.random
stone.toString()

for (i <- 1 to 5) yield r.nextString(1)
Vector.fill(4)(Stone.random)

val code3 = new Code(Vector(Stone.Red, Stone.Yellow, Stone.Red, Stone.Yellow))
val code1 = new Code(Vector(Stone.Red, Stone.Red, Stone.Yellow, Stone.Blue))   

val sC = new Code(Vector(Stone.Red, Stone.Red, Stone.Blue, Stone.Yellow))
val wC = new Code(Vector(Stone.Purple, Stone.Blue, Stone.Red, Stone.Red))

val sC_equals = sC.compareToEqual(wC.code, 0, List())
sC_equals.size
val sC_present = sC.compareToPresent(wC.code, 0, 0, sC_equals, List())
sC_present.size
val sHints = sC.compareTo(wC.code)
    
val equalList = code1.compareToEqual(code3.code, (code1.size - 1), List())  
val presentList = code1.compareToPresent(code3.code, 0, 0, equalList, List())

//equalList.size

//val presentList = code1.compareToPresent(code3.code, (code1.size - 1), List(), equalList)
//presentList.size

val vec1 = Vector.fill(4)(HintStone.Empty)
vec1.hashCode()
val vec2 = vec1.updated(0, HintStone.Black)
vec1.hashCode()
val vec3 = vec2.updated(2, HintStone.Black)

val testHint = code3.buildVector(Vector(), vec3.size, 3, 1)

if(code1.equals(code3))
  println("Equals")
code1.size

code1.toString()
val code2 = new Code(5)

val oneToFive = Vector(1, 2, 3, 4, 5)
for (i <- oneToFive) yield i

/* Test Vectors for code compare*/
val codeComp1 = new Code(Vector(Stone.Red, Stone.Blue, Stone.Green))
val codeComp2 = new Code(Vector(Stone.Red, Stone.Blue, Stone.Green))
val codeComp3 = new Code(Vector(Stone.Red, Stone.Blue, Stone.Yellow))
val codeComp4 = new Code(Vector(Stone.Blue, Stone.Red, Stone.Green))
val codeComp5 = new Code(Vector(Stone.Red, Stone.Purple, Stone.Blue))
val codeComp6 = new Code(Vector(Stone.Red, Stone.Red, Stone.Blue))
val codeComp7 = new Code(Vector(Stone.Red, Stone.Yellow, Stone.Purple))
val codeComp8 = new Code(Vector(Stone.Red, Stone.Yellow, Stone.Red))

codeComp1.code(0)


/* Test area (Code 2 should only return two "White" hints) */
val solutionCode = new Code(Vector(Stone.Red, Stone.Red, Stone.Blue, Stone.Yellow))                 

val wrongCode = new Code(Vector(Stone.Red, Stone.Blue, Stone.Purple, Stone.Red))                    
val wrongCode2 = new Code(Vector(Stone.Green, Stone.Green, Stone.Red, Stone.Green))                 
val wrongCode3 = new Code(Vector(Stone.Purple, Stone.Purple, Stone.Purple, Stone.Purple))
val wrongCode4 = new Code(Vector(Stone.Red, Stone.Red, Stone.Red, Stone.Blue))           
solutionCode.compareTo(wrongCode4.code)

//solutionCode.code.filter(solutionCode.code())
solutionCode.code.drop(1)

/* Code compare test area */
val solutionVector = solutionCode
val compareVec2 = wrongCode

var testList: List[Int] = List()
var equalCount = 0
var presentCount = 0

for(i <- 0 to (solutionVector.size-1)){
  
  if(solutionVector.code(i).equals(compareVec2.code(i))){
      equalCount = equalCount + 1
      testList = testList.appended(i)
  }
  else
  {
    for(j <- 0 to  (solutionVector.size-1)){        
      if((i != j) && (!testList.contains(j)) && (solutionVector.code(i).equals(compareVec2.code(j)))){
        presentCount = presentCount + 1 
      }
    }
  }
}
testList
//presentCount = presentCount - equalCount
equalCount
presentCount

var mutableVector: Vector[HintStone] = Vector()
mutableVector

val equalsVector = Vector.fill(equalCount)(HintStone.Black)
val presentVector = Vector.fill(presentCount)(HintStone.White)
val stepVector = equalsVector.concat(presentVector)
val diffCount = solutionVector.size - equalCount - presentCount
val emptyVec = Vector.fill(diffCount)(HintStone.Empty)
val endVector = stepVector.concat(emptyVec)


solutionVector.compareTo(compareVec2.code)

codeComp1.code(0)

val objStone1 = Stone
val objStone2 = Stone

objStone1 

Stone.random
    
/*------------------------------------------------------------------------------*/

def RandomNumberGenerator(): Int = 
  val r = new scala.util.Random
  return r.nextInt(100)

def Loop():Unit =
  val randomnr = RandomNumberGenerator()
  print("Your guess: ")
  val input = readLine(" ").toInt

  if(randomnr == input)
    print("\nCongratulations you guessed the correct number\n")
  else
    print("Sorry you picked the wrong number, the correct number was " + randomnr +"\nWanna try again? (y/n)\n")

  val ans = readLine(">> ")

  if(ans != "n")
    return Loop()