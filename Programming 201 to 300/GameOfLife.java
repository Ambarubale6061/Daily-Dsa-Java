public class GameOfLife {
    public static void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int live = 0;
                for (int[] d : dirs) {
                    int r = i + d[0], c = j + d[1];
                    if (r >= 0 && r < m && c >= 0 && c < n && (board[r][c] == 1 || board[r][c] == 2))
                        live++;
                }
                if (board[i][j] == 1 && (live < 2 || live > 3))
                    board[i][j] = 2; // 1 -> 0
                if (board[i][j] == 0 && live == 3)
                    board[i][j] = 3; // 0 -> 1
            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                board[i][j] %= 2;
    }

    public static void main(String[] args) {
        int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
        gameOfLife(board);
        for (int[] row : board)
            System.out.println(java.util.Arrays.toString(row));
    }
}