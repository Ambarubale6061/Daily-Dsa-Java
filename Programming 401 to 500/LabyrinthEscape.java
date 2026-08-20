// 499_LabyrinthEscape.java
// BFS to Escape from a Labyrinth
//
// Symbols:
// 'S' = Start
// 'E' = Exit
// '.' = Empty Cell
// '#' = Wall
//
// Time Complexity: O(R × C)
// Space Complexity: O(R × C)

import java.util.*;

public class LabyrinthEscape {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

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

    // Returns minimum steps to escape
    public static int escape(char[][] maze) {

        int rows = maze.length;
        int cols = maze[0].length;

        int sx = -1, sy = -1;
        int ex = -1, ey = -1;

        // Find start and exit
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (maze[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if (maze[i][j] == 'E') {
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

            for (int d = 0; d < 4; d++) {

                int nx = cur.x + DX[d];
                int ny = cur.y + DY[d];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                if (visited[nx][ny])
                    continue;

                if (maze[nx][ny] == '#')
                    continue;

                visited[nx][ny] = true;

                queue.offer(new Node(nx, ny, cur.dist + 1));
            }
        }

        return -1;
    }

    // Reconstruct shortest path
    public static List<int[]> shortestPath(char[][] maze) {

        int rows = maze.length;
        int cols = maze[0].length;

        int sx = -1, sy = -1;
        int ex = -1, ey = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (maze[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if (maze[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        List<int[]> path = new ArrayList<>();

        if (sx == -1 || ex == -1)
            return path;

        boolean[][] visited = new boolean[rows][cols];

        int[][] parentX = new int[rows][cols];
        int[][] parentY = new int[rows][cols];

        for (int[] row : parentX)
            Arrays.fill(row, -1);

        for (int[] row : parentY)
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

                if (maze[nx][ny] == '#')
                    continue;

                visited[nx][ny] = true;

                parentX[nx][ny] = cur[0];
                parentY[nx][ny] = cur[1];

                queue.offer(new int[]{nx, ny});
            }
        }

        if (!visited[ex][ey])
            return path;

        int x = ex;
        int y = ey;

        while (x != -1 && y != -1) {

            path.add(new int[]{x, y});

            int px = parentX[x][y];
            int py = parentY[x][y];

            x = px;
            y = py;
        }

        Collections.reverse(path);

        return path;
    }

    // Print maze with shortest path
    public static void printMaze(char[][] maze, List<int[]> path) {

        char[][] copy = new char[maze.length][];

        for (int i = 0; i < maze.length; i++)
            copy[i] = maze[i].clone();

        for (int[] cell : path) {

            if (copy[cell[0]][cell[1]] == '.')
                copy[cell[0]][cell[1]] = '*';
        }

        for (char[] row : copy)
            System.out.println(new String(row));
    }

    public static void main(String[] args) {

        char[][] maze = {
                {'S', '.', '.', '#', '.'},
                {'.', '#', '.', '#', '.'},
                {'.', '.', '.', '.', '.'},
                {'#', '#', '.', '#', '.'},
                {'.', '.', '.', '.', 'E'}
        };

        int answer = escape(maze);

        System.out.println("Minimum Steps = " + answer);

        List<int[]> path = shortestPath(maze);

        System.out.println("\nShortest Path:");

        for (int[] p : path)
            System.out.println("(" + p[0] + ", " + p[1] + ")");

        System.out.println("\nMaze:");

        printMaze(maze, path);
    }
}