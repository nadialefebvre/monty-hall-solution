# Monty Hall solution - with input

The Monty Hall solution is an implementation of the Monty Hall problem, a probability puzzle inspired by a game show. This code simulates the probability problem where a player is presented with three doors, one hiding a car prize and the other two, goats. The player initially selects one door, after which the host reveals a non-chosen door that doesn't hide the car. The player is then given the choice to keep their initial choice or switch to the other unopened door.

**Table of contents**

- [Problem overview](#problem-overview)
- [Tech stack](#tech-stack)
- [Code design choices](#code-design-choices)
- [Test coverage](#test-coverage)
- [Usage](#usage)

## Problem overview

The simulation demonstrates the counterintuitive nature of the Monty Hall problem, indicating that statistically, it's advantageous for the player to switch doors after the host reveals one of the non-chosen doors.

For further information, you can refer to [Wikipedia - Monty Hall problem](https://en.wikipedia.org/wiki/Monty_Hall_problem).

## Tech stack

### Language and tools used

- **Scala:** Programming language used for the implementation of the Monty Hall Simulation, combining both object-oriented and functional programming.

- **Scala standard library:** Used built-in features and collections (such as `Random` and `Try`) from the Scala standard library for random number generation, error handling and data manipulation.

## Code design choices

The code is structured into two main classes:

- `MontyHallRound`: Represents a round of the Monty Hall game, storing information about the prize door, the initially chosen door and the door switched to.

- `MontyHallGame`: Wraps up the logic for simulating the Monty Hall game, including methods to simulate a round and recursively simulate the game for a specified number of trials.

  The `MontyHallSimulation` object contains the main method to execute and run the simulation. It handles user inputs for the number of trials and displays the outcomes of the simulation, including the counts and percentages of wins when switching doors versus keeping the initial choice.

### Use of functional programming concepts

- **Immutability**: Immutable data structures, like the `MontyHallRound` case class, are used to ensure data consistency and prevent unintended modifications.

- **Tail recursion**: The `simulateGame` method uses tail recursion to handle the simulation of multiple game trials, avoiding stack overflow issues and optimizing memory usage.

### Error handling and input validation

- **Input validation**: The code validates user input for the number of trials, ensuring it's a positive integer greater than zero. It defaults to a predefined value if invalid input is provided.

- **Error handling**: Proper error handling using `Try` and `Success` cases is implemented to handle parsing errors and invalid input types.

### Randomness and simulations

- **Random selection**: Random number generation is used to simulate the initial door choice, prize door assignment and the host's reveal of a non-chosen door.

### Clear console outputs and messages

- **Informative console outputs**: The code provides clear and informative messages in the console, indicating the number of trials performed, counts of wins and win percentages for switching doors versus keeping the initial choice.

## Test coverage

The codebase includes comprehensive unit tests covering various aspects of the Monty Hall simulation. These tests ensure the correctness and reliability of the implemented logic.

## Usage

### Running the simulation

To run the Monty Hall solution, follow these steps:

1. **Clone the repository**

   ```
   git clone <repository-url>
   ```

2. **Compile and run**

   Compile the code using a Scala compiler and then run the simulation:

   ```
   scalac MontyHallSimulation.scala
   scala MontyHallSimulation
   ```

   Upon running the simulation, you will be prompted to enter the number of trials you want to simulate by typing the desired number in the console and pressing enter. If no input is provided or if invalid input (e.g., non-numeric characters or a number less than or equal to zero) is entered, the simulation runs with a default value of 100 trials.

### Trying different simulation methods (branches)

This repository contains different branches showcasing distinct methods for running the Monty Hall simulation. The `with-input` branch, which previously implemented the simulation with user input through the console, has been merged into the `main` branch for demonstration purposes. To explore these variations, follow these steps after cloning the repository:

1. **Switch to different branches**

   - **with-default branch:** This branch simulates the Monty Hall problem with a predefined default number of trials.

     ```
     git checkout with-default
     ```

   - **with-argument branch:** This branch allows simulation by passing the number of trials as a command-line argument.

     ```
     git checkout with-argument
     ```

   - **with-input branch:** This branch prompts the user in the console to input the number of trials.

     ```
     git checkout with-input
     ```

2. **Follow branch-specific instructions**

   Refer to the README within each branch for detailed instructions on how to compile and run the simulation, and interact with it based on the chosen method.

Choose the branch corresponding to the simulation method you want to explore to observe the differences in how the Monty Hall problem is simulated and interacted with.
