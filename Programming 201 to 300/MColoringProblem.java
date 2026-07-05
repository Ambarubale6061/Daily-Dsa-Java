import java.util.Arrays;

public class MColoringProblem {
    public static boolean graphColoring(boolean[][] graph, int m, int[] color) {
        return canColor(graph, m, color, 0);
    }

    private static boolean canColor(boolean[][] graph, int m, int[] color, int v) {
        if (v == graph.length)
            return true;
        for (int c = 1; c <= m; c++) {
            if (isSafe(graph, v, color, c)) {
                color[v] = c;
                if (canColor(graph, m, color, v + 1))
                    return true;
                color[v] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(boolean[][] graph, int v, int[] color, int c) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] && color[i] == c)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean[][] graph = { { false, true, true }, { true, false, true }, { true, true, false } };
        int[] color = new int[3];
        System.out.println(graphColoring(graph, 3, color)); // true
    }
}