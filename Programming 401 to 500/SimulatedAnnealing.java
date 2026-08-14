import java.util.Random;

public class SimulatedAnnealing {

  static final Random rand = new Random();

  // Objective Function: f(x) = x² + 4sin(x)
  public static double objectiveFunction(double x) {
    return x * x + 4 * Math.sin(x);
  }

  // Generate a neighboring solution
  public static double getNeighbor(double current) {
    return current + (rand.nextDouble() * 2 - 1); // Random step [-1,1]
  }

  // Acceptance Probability
  public static double acceptanceProbability(double currentEnergy,
      double newEnergy,
      double temperature) {

    if (newEnergy < currentEnergy)
      return 1.0;

    return Math.exp((currentEnergy - newEnergy) / temperature);
  }

  // Simulated Annealing Algorithm
  public static double solve() {

    double temperature = 1000;
    double coolingRate = 0.003;

    // Initial random solution
    double currentSolution = rand.nextDouble() * 20 - 10; // [-10,10]
    double bestSolution = currentSolution;

    while (temperature > 1) {

      // Generate neighbor
      double newSolution = getNeighbor(currentSolution);

      double currentEnergy = objectiveFunction(currentSolution);
      double newEnergy = objectiveFunction(newSolution);

      // Decide whether to accept
      if (acceptanceProbability(currentEnergy, newEnergy, temperature) > rand.nextDouble()) {

        currentSolution = newSolution;
      }

      // Update best solution
      if (objectiveFunction(currentSolution) < objectiveFunction(bestSolution)) {

        bestSolution = currentSolution;
      }

      // Cool down
      temperature *= (1 - coolingRate);
    }

    System.out.printf("Best Solution (x): %.4f%n", bestSolution);
    System.out.printf("Minimum Value f(x): %.4f%n",
        objectiveFunction(bestSolution));

    return bestSolution;
  }

  public static void main(String[] args) {
    solve();
  }
}