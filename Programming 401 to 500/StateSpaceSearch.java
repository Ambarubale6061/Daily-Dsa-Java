// 488_StateSpaceSearch.java
// Generic State Space Search using BFS
// Example: Transform start state to goal state using valid transitions.

import java.util.*;

public class StateSpaceSearch {

    private static final int MAX_STATE = 100000;

    // ----------------------------
    // State Representation
    // ----------------------------
    static class State {
        int value;
        int cost;

        State(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }
    }

    // ----------------------------
    // Generate Next States
    // Modify this method for any state-space problem.
    // ----------------------------
    private static List<Integer> generateNextStates(int state) {

        List<Integer> next = new ArrayList<>();

        if (state + 1 <= MAX_STATE)
            next.add(state + 1);

        if (state - 1 >= 0)
            next.add(state - 1);

        if (state * 2 <= MAX_STATE)
            next.add(state * 2);

        return next;
    }

    // -------------------------------------------------
    // Generic BFS State Space Search
    // Returns minimum number of transitions
    // -------------------------------------------------
    public static int search(int start, int goal) {

        if (start == goal)
            return 0;

        boolean[] visited = new boolean[MAX_STATE + 1];

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(start, 0));

        visited[start] = true;

        while (!queue.isEmpty()) {

            State current = queue.poll();

            for (int next : generateNextStates(current.value)) {

                if (!visited[next]) {

                    if (next == goal)
                        return current.cost + 1;

                    visited[next] = true;
                    queue.offer(new State(next, current.cost + 1));
                }
            }
        }

        return -1;
    }

    // -------------------------------------------------
    // Reconstruct Path
    // -------------------------------------------------
    public static List<Integer> shortestPath(int start, int goal) {

        List<Integer> path = new ArrayList<>();

        if (start == goal) {
            path.add(start);
            return path;
        }

        boolean[] visited = new boolean[MAX_STATE + 1];
        int[] parent = new int[MAX_STATE + 1];

        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        visited[start] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            if (current == goal)
                break;

            for (int next : generateNextStates(current)) {

                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = current;
                    queue.offer(next);
                }
            }
        }

        if (!visited[goal])
            return path;

        for (int cur = goal; cur != -1; cur = parent[cur])
            path.add(cur);

        Collections.reverse(path);

        return path;
    }

    // -------------------------------------------------
    // DFS State Space Exploration
    // -------------------------------------------------
    public static void dfs(int start, int depthLimit) {

        boolean[] visited = new boolean[MAX_STATE + 1];

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

        for (int next : generateNextStates(node)) {

            if (!visited[next])
                dfsHelper(next, visited, depth - 1);
        }
    }

    // -------------------------------------------------
    // Example
    // -------------------------------------------------
    public static void main(String[] args) {

        int start = 5;
        int goal = 17;

        int moves = search(start, goal);

        System.out.println("Minimum Moves : " + moves);

        System.out.println("Shortest Path : " +
                shortestPath(start, goal));

        System.out.print("DFS Traversal : ");
        dfs(start, 3);
    }
}