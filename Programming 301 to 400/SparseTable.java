public class SparseTable {
    int[][] st;
    int[] log;

    public SparseTable(int[] arr) {
        int n = arr.length;
        int K = (int) (Math.log(n) / Math.log(2)) + 1;
        st = new int[n][K];
        for (int i = 0; i < n; i++)
            st[i][0] = arr[i];
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
        log = new int[n + 1];
        for (int i = 2; i <= n; i++)
            log[i] = log[i / 2] + 1;
    }

    public int rangeMin(int l, int r) {
        int j = log[r - l + 1];
        return Math.min(st[l][j], st[r - (1 << j) + 1][j]);
    }
}