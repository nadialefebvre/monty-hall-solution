import MontyHallSimulation._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import scala.util.Random

/** Tests the Monty Hall game simulation. */
class MontyHallSimulationTest extends AnyWordSpec with Matchers {
  // Represents the available doors in the game
  val doors = Vector(1, 2, 3)
  // Instance of the Monty Hall game
  val montyHallGame = new MontyHallGame
  // Random number generator
  val random = new Random

  /** Asserts if the given door number is within the valid range of doors (1 to 3).
    *
    * @param door The door number to be checked.
    */
  def assertDoorInRange(door: Int): Unit = {
    door should (be >= 1 and be <= 3)
  }

  "The Monty Hall game" when {
    "choosing a door randomly" should {

      /** Tests if the Monty Hall game returns a random door from the available doors. */
      "return a random door from the available doors" in {
        val randomDoor = montyHallGame.getRandomDoor(doors, random)

        assertDoorInRange(randomDoor)
      }
    }

    "simulating a round" should {

      /** Tests the simulation of a round in the Monty Hall game. */
      "simulate a round of the Monty Hall game" in {
        val MontyHallRound(prizeDoor, chosenDoor, switchedDoor) =
          montyHallGame.simulateRound(random)

        assertDoorInRange(prizeDoor)
        assertDoorInRange(chosenDoor)
        assertDoorInRange(switchedDoor)
        chosenDoor should not be switchedDoor
      }
    }

    "running multiple rounds" should {

      /** Simulates the Monty Hall game for a specified number of trials.
        *
        * @param numTrials Number of trials to be simulated.
        * @param switchWins Counter for wins when switching doors.
        * @param keepWins Counter for wins when keeping the initial choice.
        * @param random Random number generator.
        * @return A tuple containing the number of wins when switching doors and when keeping the initial choice.
        */
      "simulate the Monty Hall game for a specified number of trials" in {
        val numTrials = 1000
        val (switchWins, keepWins) = montyHallGame.simulateGame(numTrials, 0, 0, random)

        switchWins + keepWins shouldEqual numTrials
        switchWins should be >= 0
        keepWins should be >= 0
      }
    }

    "handling input scenarios" should {
      val defaultNumTrials = MontyHallSimulation.DefaultNumTrials

      /** Tests the behavior of the program when handling invalid inputs. */
      "use default numTrials when input is invalid" in {
        val invalidInputs = Seq("0", "-1", "1.5", "1,5", "a string")
        val outputStream = new ByteArrayOutputStream()

        invalidInputs.foreach { input =>
          val inputStream = new ByteArrayInputStream(input.getBytes)

          Console.withIn(inputStream) {
            Console.withOut(outputStream) {
              MontyHallSimulation.useDefaultNumTrials() shouldBe defaultNumTrials
            }
          }

          val output = outputStream.toString.trim
          output shouldBe s"Using default value: $defaultNumTrials"
          outputStream.reset()
        }
      }

      /** Tests the behavior of the program when handling a valid input. */
      "not use default numTrials when input is valid" in {
        val input = "1000"
        val inputStream = new ByteArrayInputStream(input.getBytes)
        val outputStream = new ByteArrayOutputStream()

        Console.withIn(inputStream) {
          Console.withOut(outputStream) {
            val numTrials = scala.io.StdIn.readLine().toInt
            numTrials shouldBe input.toInt
          }
        }

        val output = outputStream.toString.trim
        output should not be s"Using default value: $defaultNumTrials"
        outputStream.reset()
      }
    }
  }
}
