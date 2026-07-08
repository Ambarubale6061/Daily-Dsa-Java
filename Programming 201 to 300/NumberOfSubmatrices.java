public class NumberOfSubmatrices {

    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }

        int m = mat.length;
        int n = mat[0].length;
        int totalSubmatrices = 0;

        // Step 1: Transform the matrix where each cell mat[i][j] stores
        // the number of consecutive 1's to its left in the same row.
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] += mat[i][j - 1];
                }
            }
        }

        // Step 2: For each cell, treat it as the bottom-right corner of the submatrix
        // and look upwards to count valid submatrices.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] > 0) {
                    int minWidth = mat[i][j];
                    // Move up row by row
                    for (int k = i; k >= 0; k--) {
                        if (mat[k][j] == 0) {
                            break; // No more submatrices can be formed above this
                        }
                        minWidth = Math.min(minWidth, mat[k][j]);
                        totalSubmatrices += minWidth;
                    }
                }
            }
        }

        return totalSubmatrices;
    }

    public static void main(String[] args) {
        int[][] mat = {
                { 1, 0, 1 },
                { 1, 1, 0 },
                { 1, 1, 0 }
        };
        // Expected output: 13
        System.out.println("Total submatrices: " + numSubmat(mat));
    }
}