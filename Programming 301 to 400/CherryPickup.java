public class CherryPickup {
    public static int cherryPickup(int[][] grid) {
        int n = grid.length;
        Integer[][][] memo = new Integer[n][n][n];
        int ans = dfs(grid, 0, 0, 0, memo);
        return Math.max(0, ans);
    }

    private static int dfs(int[][] grid, int r1, int c1, int r2, Integer[][][] memo) {
        int c2 = r1 + c1 - r2;
        if (r1 >= grid.length || c1 >= grid[0].length || r2 >= grid.length || c2 >= grid[0].length || grid[r1][c1] == -1
                || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;
        if (r1 == grid.length - 1 && c1 == grid[0].length - 1)
            return grid[r1][c1];
        if (memo[r1][c1][r2] != null)
            return memo[r1][c1][r2];
        int cherries = grid[r1][c1];
        if (r1 != r2)
            cherries += grid[r2][c2];
        int best = Math.max(Math.max(dfs(grid, r1 + 1, c1, r2 + 1, memo), dfs(grid, r1 + 1, c1, r2, memo)),
                Math.max(dfs(grid, r1, c1 + 1, r2 + 1, memo), dfs(grid, r1, c1 + 1, r2, memo)));
        return memo[r1][c1][r2] = cherries + (best == Integer.MIN_VALUE ? 0 : best);
    }
}