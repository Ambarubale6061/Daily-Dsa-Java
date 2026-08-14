import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

  // GA Parameters
  static final int POPULATION_SIZE = 10;
  static final int CHROMOSOME_LENGTH = 8;
  static final int MAX_GENERATIONS = 50;
  static final double MUTATION_RATE = 0.05;

  static Random random = new Random();

  // Chromosome Class
  static class Chromosome {
    int[] genes;
    int fitness;

    Chromosome() {
      genes = new int[CHROMOSOME_LENGTH];
      for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
        genes[i] = random.nextBoolean() ? 1 : 0;
      }
      calculateFitness();
    }

    Chromosome(int[] genes) {
      this.genes = genes.clone();
      calculateFitness();
    }

    void calculateFitness() {
      fitness = 0;
      for (int gene : genes) {
        fitness += gene; // Count number of 1s
      }
    }

    @Override
    public String toString() {
      return Arrays.toString(genes) + " Fitness = " + fitness;
    }
  }

  // Create Initial Population
  static Chromosome[] initializePopulation() {
    Chromosome[] population = new Chromosome[POPULATION_SIZE];

    for (int i = 0; i < POPULATION_SIZE; i++) {
      population[i] = new Chromosome();
    }

    return population;
  }

  // Tournament Selection
  static Chromosome selectParent(Chromosome[] population) {

    Chromosome c1 = population[random.nextInt(POPULATION_SIZE)];
    Chromosome c2 = population[random.nextInt(POPULATION_SIZE)];

    return (c1.fitness > c2.fitness) ? c1 : c2;
  }

  // Single Point Crossover
  static Chromosome crossover(Chromosome p1, Chromosome p2) {

    int[] childGenes = new int[CHROMOSOME_LENGTH];

    int point = random.nextInt(CHROMOSOME_LENGTH);

    for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
      if (i < point)
        childGenes[i] = p1.genes[i];
      else
        childGenes[i] = p2.genes[i];
    }

    return new Chromosome(childGenes);
  }

  // Mutation
  static void mutate(Chromosome chromosome) {

    for (int i = 0; i < CHROMOSOME_LENGTH; i++) {

      if (random.nextDouble() < MUTATION_RATE) {
        chromosome.genes[i] ^= 1;
      }
    }

    chromosome.calculateFitness();
  }

  // Find Best Chromosome
  static Chromosome getBest(Chromosome[] population) {

    Chromosome best = population[0];

    for (Chromosome c : population) {
      if (c.fitness > best.fitness)
        best = c;
    }

    return best;
  }

  // Genetic Algorithm
  public static void evolve() {

    Chromosome[] population = initializePopulation();

    for (int generation = 1; generation <= MAX_GENERATIONS; generation++) {

      Chromosome[] newPopulation = new Chromosome[POPULATION_SIZE];

      for (int i = 0; i < POPULATION_SIZE; i++) {

        Chromosome parent1 = selectParent(population);
        Chromosome parent2 = selectParent(population);

        Chromosome child = crossover(parent1, parent2);

        mutate(child);

        newPopulation[i] = child;
      }

      population = newPopulation;

      Chromosome best = getBest(population);

      System.out.println("Generation " + generation + " -> " + best);

      // Stop if optimal solution found
      if (best.fitness == CHROMOSOME_LENGTH) {
        System.out.println("\nOptimal Solution Found!");
        break;
      }
    }

    System.out.println("\nFinal Best Solution:");
    System.out.println(getBest(population));
  }

  public static void main(String[] args) {
    evolve();
  }
}