public class KnightTour {
    static int N = 8;

    public static boolean knightTour() {
        int[][] board = new int[N][N];
        for (int[] row : board)
            java.util.Arrays.fill(row, -1);
        board[0][0] = 0;
        int[] moveX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] moveY = { 1, 2, 2, 1, -1, -2, -2, -1 };
        if (solveKT(0, 0, 1, board, moveX, moveY)) {
            printSolution(board);
            return true;
        }
        return false;
    }

    private static boolean solveKT(int x, int y, int movei, int[][] board, int[] moveX, int[] moveY) {
        if (movei == N * N)
            return true;
        for (int k = 0; k < 8; k++) {
            int nextX = x + moveX[k], nextY = y + moveY[k];
            if (isSafe(nextX, nextY, board)) {
                board[nextX][nextY] = movei;
                if (solveKT(nextX, nextY, movei + 1, board, moveX, moveY))
                    return true;
                board[nextX][nextY] = -1;
            }
        }
        return false;
    }

    private static boolean isSafe(int x, int y, int[][] board) {
        return x >= 0 && y >= 0 && x < N && y < N && board[x][y] == -1;
    }

    private static void printSolution(int[][] board) {
        for (int[] row : board)
            System.out.println(java.util.Arrays.toString(row));
    }

    public static void main(String[] args) {
        knightTour();
    }
}