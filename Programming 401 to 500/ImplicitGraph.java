// 487_ImplicitGraph.java
// BFS and DFS on an Implicit Graph
// Example Problem:
// Given a start number and a target number,
// allowed operations are:
//   1. x + 1
//   2. x - 1
//   3. x * 2
// Find the minimum number of operations using BFS.

import java.util.*;

public class ImplicitGraph {

    private static final int MAX = 100000;

    // ----------------------------
    // State Representation
    // ----------------------------
    static class State {
        int value;
        int steps;

        State(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }
    }

    // ----------------------------
    // Generate Neighbor States
    // ----------------------------
    private static List<Integer> neighbors(int x) {

        List<Integer> list = new ArrayList<>();

        if (x + 1 <= MAX)
            list.add(x + 1);

        if (x - 1 >= 0)
            list.add(x - 1);

        if (x * 2 <= MAX)
            list.add(x * 2);

        return list;
    }

    // ----------------------------
    // BFS (Shortest Path)
    // ----------------------------
    public static int solve(int start, int target) {

        if (start == target)
            return 0;

        boolean[] visited = new boolean[MAX + 1];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(start, 0));
        visited[start] = true;

        while (!queue.isEmpty()) {

            State current = queue.poll();

            for (int next : neighbors(current.value)) {

                if (!visited[next]) {

                    if (next == target)
                        return current.steps + 1;

                    visited[next] = true;
                    queue.offer(new State(next, current.steps + 1));
                }
            }
        }

        return -1;
    }

    // ----------------------------
    // DFS Traversal
    // ----------------------------
    public static void dfs(int start, int depthLimit) {

        boolean[] visited = new boolean[MAX + 1];

        dfsHelper(start, visited, depthLimit);

        System.out.println();
    }

    private static void dfsHelper(int node,
                                  boolean[] visited,
                                  int depth) {

        if (depth < 0)
            return;

        visited[node] = true;

        System.out.print(node + " ");

        for (int next : neighbors(node)) {

            if (!visited[next]) {
                dfsHelper(next, visited, depth - 1);
            }
        }
    }

    // ----------------------------
    // BFS Traversal
    // ----------------------------
    public static void bfsTraversal(int start) {

        boolean[] visited = new boolean[MAX + 1];

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            System.out.print(current + " ");

            for (int next : neighbors(current)) {

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        System.out.println();
    }

    // ----------------------------
    // Example
    // ----------------------------
    public static void main(String[] args) {

        int start = 5;
        int target = 17;

        System.out.println("Minimum Operations = " + solve(start, target));

        System.out.println("\nBFS Traversal:");
        bfsTraversal(5);

        System.out.println("\nDFS Traversal (Depth 3):");
        dfs(5, 3);
    }
}