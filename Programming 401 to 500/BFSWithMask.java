// 489_BFSWithMask.java
// BFS with Bitmask States
// Problem: Shortest Path to Get All Keys (LeetCode 864)

import java.util.*;

public class BFSWithMask {

    static class State {
        int row;
        int col;
        int mask;
        int dist;

        State(int row, int col, int mask, int dist) {
            this.row = row;
            this.col = col;
            this.mask = mask;
            this.dist = dist;
        }
    }

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static int shortestPathAllKeys(String[] grid) {

        int rows = grid.length;
        int cols = grid[0].length();

        int startRow = 0;
        int startCol = 0;
        int allKeysMask = 0;

        // Locate start position and total keys
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                char ch = grid[i].charAt(j);

                if (ch == '@') {
                    startRow = i;
                    startCol = j;
                }

                if (ch >= 'a' && ch <= 'f') {
                    allKeysMask |= (1 << (ch - 'a'));
                }
            }
        }

        boolean[][][] visited = new boolean[rows][cols][1 << 6];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(startRow, startCol, 0, 0));
        visited[startRow][startCol][0] = true;

        while (!queue.isEmpty()) {

            State cur = queue.poll();

            if (cur.mask == allKeysMask)
                return cur.dist;

            for (int k = 0; k < 4; k++) {

                int nr = cur.row + DR[k];
                int nc = cur.col + DC[k];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols)
                    continue;

                char cell = grid[nr].charAt(nc);

                if (cell == '#')
                    continue;

                int newMask = cur.mask;

                // Collect key
                if (cell >= 'a' && cell <= 'f') {
                    newMask |= (1 << (cell - 'a'));
                }

                // Locked door
                if (cell >= 'A' && cell <= 'F') {

                    int need = cell - 'A';

                    if ((newMask & (1 << need)) == 0)
                        continue;
                }

                if (!visited[nr][nc][newMask]) {

                    visited[nr][nc][newMask] = true;

                    queue.offer(new State(
                            nr,
                            nc,
                            newMask,
                            cur.dist + 1
                    ));
                }
            }
        }

        return -1;
    }

    // -----------------------------
    // Example
    // -----------------------------
    public static void main(String[] args) {

        String[] grid = {
                "@.a..",
                "###.#",
                "b.A.B"
        };

        int ans = shortestPathAllKeys(grid);

        System.out.println("Shortest Path = " + ans);
    }
}