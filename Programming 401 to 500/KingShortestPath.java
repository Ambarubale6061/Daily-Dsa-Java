// 493_KingShortestPath.java
// Shortest Path for a Chess King
// Supports:
// 1. Minimum King Moves (Formula)
// 2. BFS on Chessboard (with obstacles)
// 3. Reconstruct Shortest Path

import java.util.*;

public class KingShortestPath {

    // 8 possible king moves
    private static final int[] DX = {
            -1, -1, -1,
             0,  0,
             1,  1,  1
    };

    private static final int[] DY = {
            -1, 0, 1,
            -1, 1,
            -1, 0, 1
    };

    // -------------------------------
    // O(1) Formula
    // -------------------------------
    public static int kingSteps(int x1, int y1, int x2, int y2) {

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);

        return Math.max(dx, dy);
    }

    // -------------------------------
    // BFS Node
    // -------------------------------
    static class Node {
        int x, y, dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    // -------------------------------
    // BFS Shortest Path with Obstacles
    // '.' = free cell
    // '#' = blocked
    // -------------------------------
    public static int shortestPath(char[][] board,
                                   int sx,
                                   int sy,
                                   int tx,
                                   int ty) {

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];

        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {

            Node cur = queue.poll();

            if (cur.x == tx && cur.y == ty)
                return cur.dist;

            for (int i = 0; i < 8; i++) {

                int nx = cur.x + DX[i];
                int ny = cur.y + DY[i];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                if (board[nx][ny] == '#')
                    continue;

                if (!visited[nx][ny]) {

                    visited[nx][ny] = true;

                    queue.offer(new Node(
                            nx,
                            ny,
                            cur.dist + 1
                    ));
                }
            }
        }

        return -1;
    }

    // -------------------------------
    // Reconstruct Path
    // -------------------------------
    public static List<int[]> shortestPathCoordinates(
            char[][] board,
            int sx,
            int sy,
            int tx,
            int ty) {

        int rows = board.length;
        int cols = board[0].length;

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

            if (cur[0] == tx && cur[1] == ty)
                break;

            for (int i = 0; i < 8; i++) {

                int nx = cur[0] + DX[i];
                int ny = cur[1] + DY[i];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                if (board[nx][ny] == '#')
                    continue;

                if (!visited[nx][ny]) {

                    visited[nx][ny] = true;

                    px[nx][ny] = cur[0];
                    py[nx][ny] = cur[1];

                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        List<int[]> path = new ArrayList<>();

        if (!visited[tx][ty])
            return path;

        int cx = tx;
        int cy = ty;

        while (cx != -1 && cy != -1) {

            path.add(new int[]{cx, cy});

            int nx = px[cx][cy];
            int ny = py[cx][cy];

            cx = nx;
            cy = ny;
        }

        Collections.reverse(path);

        return path;
    }

    // -------------------------------
    // Print Board
    // -------------------------------
    public static void printBoard(char[][] board,
                                  List<int[]> path) {

        char[][] copy = new char[board.length][];

        for (int i = 0; i < board.length; i++)
            copy[i] = board[i].clone();

        for (int[] p : path) {

            if (copy[p[0]][p[1]] == '.')
                copy[p[0]][p[1]] = '*';
        }

        for (char[] row : copy)
            System.out.println(new String(row));
    }

    // -------------------------------
    // Example
    // -------------------------------
    public static void main(String[] args) {

        System.out.println("Formula:");
        System.out.println(
                kingSteps(0, 0, 7, 5)
        );

        char[][] board = {
                {'.', '.', '.', '.', '.'},
                {'.', '#', '#', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '#', '.', '#', '.'},
                {'.', '.', '.', '.', '.'}
        };

        int moves = shortestPath(board, 0, 0, 4, 4);

        System.out.println("\nBFS Moves = " + moves);

        List<int[]> path =
                shortestPathCoordinates(board,
                        0, 0,
                        4, 4);

        System.out.println("\nPath:");

        for (int[] p : path)
            System.out.println("(" + p[0] + "," + p[1] + ")");

        System.out.println("\nBoard:");

        printBoard(board, path);
    }
}