// 484_GlobalMinimumCut.java
// Stoer-Wagner Algorithm
// Time Complexity: O(V^3)

import java.util.*;

public class GlobalMinimumCut {

    static class Result {
        int minCut;
        List<Integer> partition;

        Result(int minCut, List<Integer> partition) {
            this.minCut = minCut;
            this.partition = partition;
        }
    }

    public static Result stoerWagner(int[][] graph) {
        int n = graph.length;

        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            g[i] = graph[i].clone();
        }

        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            groups.add(new ArrayList<>());
            groups.get(i).add(i);
        }

        boolean[] removed = new boolean[n];
        int bestCut = Integer.MAX_VALUE;
        List<Integer> bestPartition = new ArrayList<>();

        int vertices = n;

        while (vertices > 1) {
            boolean[] added = new boolean[n];
            int[] weights = new int[n];
            int prev = -1;
            int last = -1;

            for (int i = 0; i < vertices; i++) {

                int sel = -1;
                for (int j = 0; j < n; j++) {
                    if (!removed[j] && !added[j] &&
                            (sel == -1 || weights[j] > weights[sel])) {
                        sel = j;
                    }
                }

                added[sel] = true;

                if (i == vertices - 1) {
                    last = sel;

                    if (weights[sel] < bestCut) {
                        bestCut = weights[sel];
                        bestPartition = new ArrayList<>(groups.get(sel));
                    }

                    if (prev != -1) {
                        for (int j = 0; j < n; j++) {
                            if (!removed[j] && j != prev) {
                                g[prev][j] += g[last][j];
                                g[j][prev] = g[prev][j];
                            }
                        }

                        groups.get(prev).addAll(groups.get(last));
                        removed[last] = true;
                        vertices--;
                    }

                    break;
                }

                prev = sel;

                for (int j = 0; j < n; j++) {
                    if (!removed[j] && !added[j]) {
                        weights[j] += g[sel][j];
                    }
                }
            }
        }

        return new Result(bestCut, bestPartition);
    }

    public static void main(String[] args) {

        // Undirected weighted graph (Adjacency Matrix)
        int[][] graph = {
                {0, 3, 2, 0},
                {3, 0, 4, 2},
                {2, 4, 0, 5},
                {0, 2, 5, 0}
        };

        Result result = stoerWagner(graph);

        System.out.println("Global Minimum Cut Value = " + result.minCut);
        System.out.println("One Partition = " + result.partition);
    }
}