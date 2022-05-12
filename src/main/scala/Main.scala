import scala.util.Success
import scala.util.Try
import scala.util.Failure
import scala.io.StdIn.readLine

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

@main def main: Unit = 

  print("Random Number between 0 and 100 is getting generated... \n")
  Loop()

  print("Thank you for playing our game!!!\n")







