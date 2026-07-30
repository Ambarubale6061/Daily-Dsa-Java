public class LazyPropagation {
    long[] tree, lazy;
    int n;

    public LazyPropagation(int[] arr) {
        n = arr.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(arr, 2 * node + 1, l, mid);
        build(arr, 2 * node + 2, mid + 1, r);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    private void propagate(int node, int l, int r) {
        if (lazy[node] != 0) {
            tree[node] += (r - l + 1) * lazy[node];
            if (l != r) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    public void rangeAdd(int ql, int qr, long val) {
        rangeAdd(0, 0, n - 1, ql, qr, val);
    }

    private void rangeAdd(int node, int l, int r, int ql, int qr, long val) {
        propagate(node, l, r);
        if (ql > r || qr < l)
            return;
        if (ql <= l && r <= qr) {
            lazy[node] += val;
            propagate(node, l, r);
            return;
        }
        int mid = (l + r) / 2;
        rangeAdd(2 * node + 1, l, mid, ql, qr, val);
        rangeAdd(2 * node + 2, mid + 1, r, ql, qr, val);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    public long rangeSum(int ql, int qr) {
        return rangeSum(0, 0, n - 1, ql, qr);
    }

    private long rangeSum(int node, int l, int r, int ql, int qr) {
        propagate(node, l, r);
        if (ql > r || qr < l)
            return 0;
        if (ql <= l && r <= qr)
            return tree[node];
        int mid = (l + r) / 2;
        return rangeSum(2 * node + 1, l, mid, ql, qr) + rangeSum(2 * node + 2, mid + 1, r, ql, qr);
    }
}