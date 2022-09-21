import de.htwg.se.mastermind.model._
import scala.util.Success
import scala.util.Try
import scala.util.Failure
import scala.io.StdIn.readLine

val x = 5 + 1
val y = RandomNumberGenerator()

val c1 = new Code(4)
c1.size

val r  = scala.util.Random
r.nextInt()

val stone = Stone.random
stone.toString()

for (i <- 1 to 5) yield r.nextString(1)
Vector.fill(4)(Stone.random)

val code1 = new Code(4)
code1.size

val code2 = new Code(5)

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