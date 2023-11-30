/*
  Monty Hall problem in short:
    - A player is given the choice of three doors, one hiding a car prize and the others, goats
    - The player selects one door without opening it
    - A non-chosen door not hiding the car is revealed by the host
    - The player has then the possibility to keep their initial choice or to switch for the non-opened door
    - Is it to their advantage to switch? (short answer: yes, 2/3 probability)
 */

import scala.util.Random

object MontyHallSimulation {
  def simulateGame(
      remainingTrials: Int,
      switchWins: Int,
      keepWins: Int,
      random: Random
  ): (Int, Int) = {
    if (remainingTrials <= 0) {
      (switchWins, keepWins)
    } else {
      val doorsRange = 1 to 3

      val prizeDoor = doorsRange(random.nextInt(doorsRange.length))
      val chosenDoor = doorsRange(random.nextInt(doorsRange.length))
      val remainingDoors = doorsRange.filterNot(_ == prizeDoor).filterNot(_ == chosenDoor)
      val openedDoor = remainingDoors(random.nextInt(remainingDoors.length))
      val switchedDoor = doorsRange.filterNot(_ == chosenDoor).filterNot(_ == openedDoor).head

      val updatedSwitchWins = if (switchedDoor == prizeDoor) switchWins + 1 else switchWins
      val updatedKeepWins = if (chosenDoor == prizeDoor) keepWins + 1 else keepWins

      simulateGame(remainingTrials - 1, updatedSwitchWins, updatedKeepWins, random)
    }
  }

  def runTrials(numTrials: Int): (Int, Int) = {
    val random = new Random()
    simulateGame(numTrials, 0, 0, random)
  }

  def main(args: Array[String]): Unit = {
    val numTrials = if (args.length > 0) args(0).toInt else 100

    val (switchWins, keepWins) = runTrials(numTrials)

    println(s"Number of trials in this simulation: $numTrials")
    println(s"Switching doors wins: $switchWins times")
    println(s"Keeping initial choice wins: $keepWins times")

    val switchWinPercentage = switchWins.toDouble / numTrials * 100
    val keepWinPercentage = keepWins.toDouble / numTrials * 100

    println(s"Switching doors win percentage: $switchWinPercentage%")
    println(s"Keeping initial choice win percentage: $keepWinPercentage%")
  }
}
