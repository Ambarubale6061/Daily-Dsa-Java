import java.util.Random;

public class AntColonyOptimization {

  private final int numCities;
  private final int numAnts;
  private final int maxIterations;

  private final double alpha = 1.0; // Pheromone importance
  private final double beta = 5.0; // Distance importance
  private final double evaporation = 0.5;
  private final double Q = 100.0;

  private final double[][] distances;
  private final double[][] pheromone;

  private final Random random = new Random();

  private int[] bestTour;
  private double bestLength = Double.MAX_VALUE;

  public AntColonyOptimization(double[][] distances, int ants, int iterations) {
    this.distances = distances;
    this.numCities = distances.length;
    this.numAnts = ants;
    this.maxIterations = iterations;

    pheromone = new double[numCities][numCities];

    for (int i = 0; i < numCities; i++) {
      for (int j = 0; j < numCities; j++) {
        pheromone[i][j] = 1.0;
      }
    }
  }

  public void optimize() {

    for (int iter = 0; iter < maxIterations; iter++) {

      int[][] tours = new int[numAnts][numCities];
      double[] lengths = new double[numAnts];

      for (int ant = 0; ant < numAnts; ant++) {
        tours[ant] = constructTour();
        lengths[ant] = calculateLength(tours[ant]);

        if (lengths[ant] < bestLength) {
          bestLength = lengths[ant];
          bestTour = tours[ant].clone();
        }
      }

      evaporatePheromones();

      for (int ant = 0; ant < numAnts; ant++) {
        updatePheromones(tours[ant], lengths[ant]);
      }
    }

    System.out.println("Best Tour Length: " + bestLength);
    System.out.print("Best Tour: ");

    for (int city : bestTour) {
      System.out.print(city + " ");
    }
    System.out.println(bestTour[0]);
  }

  private int[] constructTour() {

    int[] tour = new int[numCities];
    boolean[] visited = new boolean[numCities];

    int current = random.nextInt(numCities);

    tour[0] = current;
    visited[current] = true;

    for (int i = 1; i < numCities; i++) {

      int next = selectNextCity(current, visited);

      tour[i] = next;
      visited[next] = true;
      current = next;
    }

    return tour;
  }

  private int selectNextCity(int current, boolean[] visited) {

    double[] probabilities = new double[numCities];
    double sum = 0;

    for (int city = 0; city < numCities; city++) {

      if (!visited[city]) {

        probabilities[city] = Math.pow(pheromone[current][city], alpha)
            * Math.pow(1.0 / distances[current][city], beta);

        sum += probabilities[city];
      }
    }

    double rand = random.nextDouble() * sum;
    double total = 0;

    for (int city = 0; city < numCities; city++) {

      if (!visited[city]) {
        total += probabilities[city];

        if (total >= rand)
          return city;
      }
    }

    for (int city = 0; city < numCities; city++)
      if (!visited[city])
        return city;

    return -1;
  }

  private double calculateLength(int[] tour) {

    double length = 0;

    for (int i = 0; i < numCities - 1; i++) {
      length += distances[tour[i]][tour[i + 1]];
    }

    length += distances[tour[numCities - 1]][tour[0]];

    return length;
  }

  private void evaporatePheromones() {

    for (int i = 0; i < numCities; i++) {
      for (int j = 0; j < numCities; j++) {
        pheromone[i][j] *= (1 - evaporation);

        if (pheromone[i][j] < 0.0001)
          pheromone[i][j] = 0.0001;
      }
    }
  }

  private void updatePheromones(int[] tour, double length) {

    double deposit = Q / length;

    for (int i = 0; i < numCities - 1; i++) {

      int from = tour[i];
      int to = tour[i + 1];

      pheromone[from][to] += deposit;
      pheromone[to][from] += deposit;
    }

    int last = tour[numCities - 1];
    int first = tour[0];

    pheromone[last][first] += deposit;
    pheromone[first][last] += deposit;
  }

  public static void main(String[] args) {

    double[][] graph = {
        { 0, 2, 9, 10 },
        { 2, 0, 6, 4 },
        { 9, 6, 0, 8 },
        { 10, 4, 8, 0 }
    };

    AntColonyOptimization aco = new AntColonyOptimization(graph, 20, 100);

    aco.optimize();
  }
}