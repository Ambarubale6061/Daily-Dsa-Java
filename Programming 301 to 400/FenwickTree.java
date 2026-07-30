public class FenwickTree {
    int[] bit;
    int n;

    public FenwickTree(int size) {
        n = size;
        bit = new int[n + 1];
    }

    public void add(int idx, int delta) {
        for (int i = idx + 1; i <= n; i += i & -i)
            bit[i] += delta;
    }

    public int sum(int idx) { // prefix sum 0..idx
        int s = 0;
        for (int i = idx + 1; i > 0; i -= i & -i)
            s += bit[i];
        return s;
    }

    public int rangeSum(int l, int r) {
        return sum(r) - sum(l - 1);
    }
}