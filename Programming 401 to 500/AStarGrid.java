// 492_AStarGrid.java
// A* Search on a 2D Grid
// Uses Manhattan Distance as the heuristic.
// Finds the shortest path from Start (S) to Goal (G).

import java.util.*;

public class AStarGrid {

    // Movement directions: Up, Down, Left, Right
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    // Node used in Priority Queue
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int g;          // Cost from start
        int h;          // Heuristic cost
        int f;          // g + h

        Node parent;

        Node(int x, int y, int g, int h, Node parent) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
    }

    // Manhattan Distance Heuristic
    private static int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    // A* Search
    public static List<int[]> shortestPath(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int sx = -1, sy = -1;
        int gx = -1, gy = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if (grid[i][j] == 'G') {
                    gx = i;
                    gy = j;
                }
            }
        }

        if (sx == -1 || gx == -1)
            return Collections.emptyList();

        PriorityQueue<Node> pq = new PriorityQueue<>();

        boolean[][] visited = new boolean[rows][cols];

        int[][] best = new int[rows][cols];

        for (int[] row : best)
            Arrays.fill(row, Integer.MAX_VALUE);

        pq.offer(new Node(
                sx,
                sy,
                0,
                heuristic(sx, sy, gx, gy),
                null));

        best[sx][sy] = 0;

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (visited[cur.x][cur.y])
                continue;

            visited[cur.x][cur.y] = true;

            if (cur.x == gx && cur.y == gy) {

                List<int[]> path = new ArrayList<>();

                while (cur != null) {
                    path.add(new int[]{cur.x, cur.y});
                    cur = cur.parent;
                }

                Collections.reverse(path);
                return path;
            }

            for (int d = 0; d < 4; d++) {

                int nx = cur.x + DX[d];
                int ny = cur.y + DY[d];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                if (grid[nx][ny] == '#')
                    continue;

                int ng = cur.g + 1;

                if (ng < best[nx][ny]) {

                    best[nx][ny] = ng;

                    pq.offer(new Node(
                            nx,
                            ny,
                            ng,
                            heuristic(nx, ny, gx, gy),
                            cur
                    ));
                }
            }
        }

        return Collections.emptyList();
    }

    // Print Grid with Path
    public static void printPath(char[][] grid, List<int[]> path) {

        char[][] copy = new char[grid.length][];

        for (int i = 0; i < grid.length; i++)
            copy[i] = grid[i].clone();

        for (int[] p : path) {

            if (copy[p[0]][p[1]] == '.')
                copy[p[0]][p[1]] = '*';
        }

        for (char[] row : copy)
            System.out.println(new String(row));
    }

    // Example
    public static void main(String[] args) {

        char[][] grid = {
                {'S', '.', '.', '#', '.'},
                {'.', '#', '.', '.', '.'},
                {'.', '#', '.', '#', '.'},
                {'.', '.', '.', '#', '.'},
                {'#', '.', '.', '.', 'G'}
        };

        List<int[]> path = shortestPath(grid);

        if (path.isEmpty()) {
            System.out.println("No Path Found");
            return;
        }

        System.out.println("Shortest Path Length = " + (path.size() - 1));

        System.out.println("\nCoordinates:");

        for (int[] p : path)
            System.out.println("(" + p[0] + ", " + p[1] + ")");

        System.out.println("\nGrid:");

        printPath(grid, path);
    }
}