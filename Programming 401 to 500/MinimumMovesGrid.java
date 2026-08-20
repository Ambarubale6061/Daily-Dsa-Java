// 497_MinimumMovesGrid.java
// Minimum Moves in a Grid using BFS
// '.' = Empty Cell
// '#' = Obstacle
// 'S' = Start
// 'E' = Destination

import java.util.*;

public class MinimumMovesGrid {

    // 4-direction movement
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    // BFS Node
    static class Node {
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    // Find minimum moves from S to E
    public static int minimumMoves(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int sx = -1, sy = -1;
        int ex = -1, ey = -1;

        // Locate Start and End
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if (grid[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        if (sx == -1 || ex == -1)
            return -1;

        boolean[][] visited = new boolean[rows][cols];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy, 0));

        visited[sx][sy] = true;

        while (!queue.isEmpty()) {

            Node cur = queue.poll();

            if (cur.x == ex && cur.y == ey)
                return cur.dist;

            for (int k = 0; k < 4; k++) {

                int nx = cur.x + DX[k];
                int ny = cur.y + DY[k];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                if (visited[nx][ny])
                    continue;

                if (grid[nx][ny] == '#')
                    continue;

                visited[nx][ny] = true;

                queue.offer(new Node(nx, ny, cur.dist + 1));
            }
        }

        return -1;
    }

    // Return shortest path coordinates
    public static List<int[]> shortestPath(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int sx = -1, sy = -1;
        int ex = -1, ey = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if (grid[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        List<int[]> path = new ArrayList<>();

        if (sx == -1 || ex == -1)
            return path;

        boolean[][] visited = new boolean[rows][cols];

        int[][] px = new int[rows][cols];
        int[][] py = new int[rows][cols];

        for (int[] row : px)
            Arrays.fill(row, -1);

        for (int[] row : py)
            Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});

        visited[sx][sy] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if (cur[0] == ex && cur[1] == ey)
                break;

            for (int d = 0; d < 4; d++) {

                int nx = cur[0] + DX[d];
                int ny = cur[1] + DY[d];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                if (visited[nx][ny])
                    continue;

                if (grid[nx][ny] == '#')
                    continue;

                visited[nx][ny] = true;

                px[nx][ny] = cur[0];
                py[nx][ny] = cur[1];

                queue.offer(new int[]{nx, ny});
            }
        }

        if (!visited[ex][ey])
            return path;

        int x = ex;
        int y = ey;

        while (x != -1 && y != -1) {

            path.add(new int[]{x, y});

            int nx = px[x][y];
            int ny = py[x][y];

            x = nx;
            y = ny;
        }

        Collections.reverse(path);

        return path;
    }

    // Print grid with shortest path
    public static void printGrid(char[][] grid, List<int[]> path) {

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
                {'.', '#', '.', '#', '.'},
                {'.', '.', '.', '.', '.'},
                {'#', '#', '.', '#', '.'},
                {'.', '.', '.', '.', 'E'}
        };

        int moves = minimumMoves(grid);

        System.out.println("Minimum Moves = " + moves);

        List<int[]> path = shortestPath(grid);

        System.out.println("\nShortest Path:");

        for (int[] p : path)
            System.out.println("(" + p[0] + ", " + p[1] + ")");

        System.out.println("\nGrid with Path:");

        printGrid(grid, path);
    }
}