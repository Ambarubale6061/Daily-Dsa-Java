import java.util.*;

public class DiagonalTraversal {
    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat.length == 0)
            return new int[0];
        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 1; // 1 upward, -1 downward
        for (int i = 0; i < m * n; i++) {
            result[i] = mat[row][col];
            if (d == 1) {
                if (col == n - 1) {
                    row++;
                    d = -1;
                } else if (row == 0) {
                    col++;
                    d = -1;
                } else {
                    row--;
                    col++;
                }
            } else {
                if (row == m - 1) {
                    col++;
                    d = 1;
                } else if (col == 0) {
                    row++;
                    d = 1;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }
}