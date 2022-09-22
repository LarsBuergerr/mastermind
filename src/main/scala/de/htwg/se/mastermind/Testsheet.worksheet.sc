import de.htwg.se.mastermind.model._
import scala.util.Success
import scala.util.Try
import scala.util.Failure
import scala.io.StdIn.readLine

val x = 5 + 1
val y = RandomNumberGenerator()

/*------------------------------------------------------------------------------ Code.scala tests*/
val c1 = new Code(4)
c1.size

val r  = scala.util.Random
r.nextInt()

val stone = Stone.random
stone.toString()

for (i <- 1 to 5) yield r.nextString(1)
Vector.fill(4)(Stone.random)

val code1 = new Code(4)
val code3 = code1

val vec1 = Vector.fill(4)(HintStone.Empty)
vec1.hashCode()
val vec2 = vec1.updated(0, HintStone.Black)
vec1.hashCode()
val vec3 = vec2.updated(2, HintStone.Black)

var vect1 = Vector(HintStone.Empty)
vect1.appended(HintStone.Black)
vect1.appended(HintStone.White)

if(code1.equals(code3))
  println("Equals")
code1.size

code1.toString()
val code2 = new Code(5)

val codeComp1 = new Code(Vector(Stone.Red, Stone.Blue, Stone.Green))
val codeComp2 = new Code(Vector(Stone.Red, Stone.Blue, Stone.Green))
val codeComp3 = new Code(Vector(Stone.Red, Stone.Blue, Stone.Yellow))
val codeComp4 = new Code(Vector(Stone.Blue, Stone.Red, Stone.Green))
val codeComp5 = new Code(Vector(Stone.Red, Stone.Purple, Stone.Blue))
val codeComp6 = new Code(Vector(Stone.Red, Stone.Red, Stone.Blue))
val codeComp7 = new Code(Vector(Stone.Red, Stone.Yellow, Stone.Purple))

codeComp1.compare(codeComp2.code)
codeComp1.compare(codeComp3.code)

var testVec = Vector(HintStone.Empty)
var testCount = 0
testVec.hashCode()

codeComp1.code(0)


var equalCount = 0
var presentCount = 0

val solutionVector = codeComp6
val compareVec2 = codeComp7

for(i <- 0 to (solutionVector.size-1)){
  
  if(solutionVector.code(i).equals(compareVec2.code(i))){
    equalCount = equalCount + 1
  }
  
  for(j <- 0 to  (solutionVector.size-1)){        
    if((i != j) && (solutionVector.code(i).equals(compareVec2.code(j)))){
      presentCount = presentCount + 1
    }
  }
}
//presentCount = presentCount - equalCount
equalCount
presentCount

val equalsVector = Vector.fill(equalCount)(HintStone.Black)
val presentVector = Vector.fill(presentCount)(HintStone.White)
val stepVector = equalsVector.concat(presentVector)
val diffCount = solutionVector.size - equalCount - presentCount
val emptyVec = Vector.fill(diffCount)(HintStone.Empty)
val endVector = stepVector.concat(emptyVec)

testVec
testVec.hashCode()
testCount

solutionVector.compare(compareVec2.code)

codeComp1.code(0)

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