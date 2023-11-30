/*
  Monty Hall problem in short:
    - A player is given the choice of three doors, one hiding a car prize and the others, goats
    - The player selects one door without opening it
    - A non-chosen door not hiding the car is revealed by the host
    - The player has then the possibility to keep their initial choice or to switch for the non-opened door
    - Is it to their advantage to switch? (short answer: yes, 2/3 probability)
 */

import scala.annotation.tailrec
import scala.util.{Failure, Random, Success, Try}

object MontyHallSimulation {
  val DefaultNumTrials = 100

  def getRandomDoor(range: Seq[Int], random: Random): Int = {
    range(random.nextInt(range.length))
  }

  def simulateRound(random: Random): (Int, Int, Int) = {
    val doors = 1 to 3

    val prizeDoor = getRandomDoor(doors, random)
    val chosenDoor = getRandomDoor(doors, random)
    val remainingDoors = doors.filterNot(_ == prizeDoor).filterNot(_ == chosenDoor)
    val openedDoor = getRandomDoor(remainingDoors, random)
    val switchedDoor = doors.find(door => door != chosenDoor && door != openedDoor).get

    (prizeDoor, chosenDoor, switchedDoor)
  }

  @tailrec
  def simulateGame(
      remainingTrials: Int,
      switchWins: Int,
      keepWins: Int,
      random: Random
  ): (Int, Int) = {
    if (remainingTrials <= 0) {
      (switchWins, keepWins)
    } else {
      val (prizeDoor, chosenDoor, switchedDoor) = simulateRound(random)

      val updatedSwitchWins = if (switchedDoor == prizeDoor) switchWins + 1 else switchWins
      val updatedKeepWins = if (chosenDoor == prizeDoor) keepWins + 1 else keepWins

      simulateGame(remainingTrials - 1, updatedSwitchWins, updatedKeepWins, random)
    }
  }

  def useDefaultNumTrials(): Int = {
    println(s"Using default value: $DefaultNumTrials")
    DefaultNumTrials
  }

  def parseNumTrials(args: Array[String]): Int = args match {
    case Array(value) =>
      Try(value.toInt) match {
        case Success(trials) if trials > 0 => trials
        case Success(_) =>
          println(Console.RED + "Number of trials should be higher than 0.")
          useDefaultNumTrials
        case Failure(_) =>
          println(Console.RED + "Invalid type of input for number of trials.")
          useDefaultNumTrials
      }
    case _ =>
      println(Console.RED + "Invalid number of arguments. Please provide only one argument.")
      useDefaultNumTrials
  }

  def main(args: Array[String]): Unit = {
    val numTrials = if (args.nonEmpty) parseNumTrials(args) else DefaultNumTrials

    val random = new Random()

    val (switchWins, keepWins) = simulateGame(numTrials, 0, 0, random)

    println(Console.RESET + s"Number of trials in this simulation: $numTrials")
    println(s"Switching doors wins: $switchWins times")
    println(s"Keeping initial choice wins: $keepWins times")

    val switchWinPercentage = switchWins.toDouble / numTrials * 100
    val keepWinPercentage = keepWins.toDouble / numTrials * 100

    println(s"Switching doors win percentage: $switchWinPercentage%")
    println(s"Keeping initial choice win percentage: $keepWinPercentage%")
  }
}
