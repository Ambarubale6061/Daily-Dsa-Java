import java.util.*;

public class EventualSafeStates {
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 unvisited, 1 visiting, 2 safe
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, color))
                res.add(i);
        }
        return res;
    }

    private static boolean dfs(int[][] graph, int u, int[] color) {
        if (color[u] != 0)
            return color[u] == 2;
        color[u] = 1;
        for (int v : graph[u]) {
            if (!dfs(graph, v, color))
                return false;
        }
        color[u] = 2;
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
        System.out.println(eventualSafeNodes(graph)); // [2,4,5,6]
    }
}