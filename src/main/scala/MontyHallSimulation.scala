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

object MontyHallSimulation extends App {
  println("Solution will be here!")
}