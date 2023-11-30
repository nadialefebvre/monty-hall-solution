/** The Monty Hall problem is a probability puzzle based on a game show scenario. The gameplay
  * involves the following steps:
  *   - A player is given the choice of three doors, one hiding a car prize and the others, goats.
  *   - The player selects one door without opening it.
  *   - A non-chosen door not hiding the car is revealed by the host.
  *   - The player has the option to keep their initial choice or switch to the non-opened door.
  * Is it to the player's advantage to switch? (Short answer: yes, with a 2/3 probability).
  * @see https://en.wikipedia.org/wiki/Monty_Hall_problem
  */

import scala.annotation.tailrec
import scala.util.Random

/** Represents a round of the Monty Hall game.
  *
  * @param prizeDoor Door hiding the car prize.
  * @param chosenDoor Door initially chosen by the player.
  * @param switchedDoor Door the player switches to (if they choose to switch).
  */
case class MontyHallRound(prizeDoor: Int, chosenDoor: Int, switchedDoor: Int)

/** Wraps up the Monty Hall game logic. */
class MontyHallGame {
  private final val Doors = Vector(1, 2, 3) // Constant: represents the doors.

  /** Returns a random door from the available doors.
    *
    * @param doors Vector containing the available doors.
    * @param random Random object for generating random numbers.
    * @return Randomly chosen door number.
    */
  def getRandomDoor(doors: Vector[Int], random: Random): Int = {
    doors(random.nextInt(doors.length))
  }

  /** Simulates a round of the Monty Hall game.
    *
    * @param random Random object for generating random numbers.
    * @return MontyHallRound object representing the outcome of the round.
    */
  def simulateRound(random: Random): MontyHallRound = {
    val prizeDoor = getRandomDoor(Doors, random)

    val chosenDoor = getRandomDoor(Doors, random)

    val remainingDoors = Doors.filterNot(_ == prizeDoor).filterNot(_ == chosenDoor)
    val openedDoor = getRandomDoor(remainingDoors, random)
    val switchedDoor = Doors.find(door => door != chosenDoor && door != openedDoor).get

    MontyHallRound(prizeDoor, chosenDoor, switchedDoor)
  }

  /** Recursively simulates the Monty Hall game for a specified number of trials.
    *
    * @param remainingTrials Number of trials remaining to simulate.
    * @param switchWins Wins count when switching doors.
    * @param keepWins Wins count when keeping the initial choice.
    * @param random Random object for generating random numbers.
    * @return Tuple containing wins counts when switching and keeping the initial choice.
    */
  @tailrec
  final def simulateGame(
      remainingTrials: Int,
      switchWins: Int,
      keepWins: Int,
      random: Random
  ): (Int, Int) = {
    if (remainingTrials <= 0) {
      (switchWins, keepWins)
    } else {
      val MontyHallRound(prizeDoor, chosenDoor, switchedDoor) = simulateRound(random)

      val updatedSwitchWins = if (switchedDoor == prizeDoor) switchWins + 1 else switchWins
      val updatedKeepWins = if (chosenDoor == prizeDoor) keepWins + 1 else keepWins

      simulateGame(remainingTrials - 1, updatedSwitchWins, updatedKeepWins, random)
    }
  }
}

/** Runs the Monty Hall problem simulation. */
object MontyHallSimulation extends App {
  private final val DefaultNumTrials = 100 // Constant: represents the default number of trials.

  val numTrials = DefaultNumTrials
  val random = new Random()
  val montyHallGame = new MontyHallGame()

  /** Runs the simulation of the Monty Hall problem based on the provided number of trials.
    * It outputs the results showing the winning percentages of switching or keeping the initial choice.
    */
  val (switchWins, keepWins) = montyHallGame.simulateGame(numTrials, 0, 0, random)

  println(Console.CYAN + "Monty Hall simulation")
  println(Console.RESET + s"Number of trials in this simulation: $numTrials")
  println(s"Switching doors wins: $switchWins times")
  println(s"Keeping initial choice wins: $keepWins times")

  val switchWinPercentage = switchWins.toDouble / numTrials * 100
  val keepWinPercentage = keepWins.toDouble / numTrials * 100

  println(s"Switching doors win percentage: $switchWinPercentage%")
  println(s"Keeping initial choice win percentage: $keepWinPercentage%")
}
