/*
  Monty Hall problem in short:
    - A player is given the choice of three doors, one hiding a car prize and the others, goats
    - The player selects one door without opening it
    - A non-chosen door not hiding the car is revealed by the host
    - The player has then the possibility to keep their initial choice or to switch for the non-opened door
    - Is it to their advantage to switch? (short answer: yes, 2/3 probability)

  Set up game variables:
    - Define the total number of trials to simulate (hard coded for now)
    - Create variables to keep track of both numbers of wins for switching and for keeping door

  Define a recursive function to perform the simulation until the number of trials is reached, which will:
    - Generate the car door and the initially chosen door randomly
    - Identify the remaining door(s) by filtering out the chosen door and the car door (can be the same door)
    - Select one door for the host to open among the remaining doors
    - Calculate the switched door based on the strategy (switch or keep)
    - Update win counts for both switching and keeping strategies

  Output the simulation results:
    - Display the number of trials performed
    - Show the counts of switch wins and keep wins
    - Calculate and display win percentages for both strategies
 */

import scala.util.Random

object MontyHallSimulation extends App {
  val numTrials = 100

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

      val carDoor = doorsRange(random.nextInt(doorsRange.length))
      val chosenDoor = doorsRange(random.nextInt(doorsRange.length))
      val remainingDoors = doorsRange.filterNot(_ == carDoor).filterNot(_ == chosenDoor)
      val openedDoor = remainingDoors(random.nextInt(remainingDoors.length))
      val switchedDoor = (doorsRange).filterNot(_ == chosenDoor).filterNot(_ == openedDoor).head

      val updatedSwitchWins = if (switchedDoor == carDoor) switchWins + 1 else switchWins
      val updatedKeepWins = if (chosenDoor == carDoor) keepWins + 1 else keepWins

      simulateGame(remainingTrials - 1, updatedSwitchWins, updatedKeepWins, random)
    }
  }

  def runTrials(numTrials: Int): (Int, Int) = {
    val random = new Random()
    simulateGame(numTrials, 0, 0, random)
  }

  val (switchWins, keepWins) = runTrials(numTrials)

  println(s"Number of trials in this simulation: $numTrials")
  println(s"Switching doors wins: $switchWins times")
  println(s"Keeping initial choice wins: $keepWins times")

  val switchWinPercentage = switchWins.toDouble / numTrials * 100
  val keepWinPercentage = keepWins.toDouble / numTrials * 100

  println(s"Switching doors win percentage: $switchWinPercentage%")
  println(s"Keeping initial choice win percentage: $keepWinPercentage%")
}
