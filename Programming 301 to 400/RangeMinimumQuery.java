public class RangeMinimumQuery {
    int[] segmentTree;
    int n;

    public RangeMinimumQuery(int[] nums) {
        n = nums.length;
        segmentTree = new int[4 * n];
        build(nums, 0, 0, n - 1);
    }

    private void build(int[] nums, int node, int l, int r) {
        if (l == r) {
            segmentTree[node] = nums[l];
            return;
        }
        int mid = (l + r) / 2;
        build(nums, 2 * node + 1, l, mid);
        build(nums, 2 * node + 2, mid + 1, r);
        segmentTree[node] = Math.min(segmentTree[2 * node + 1], segmentTree[2 * node + 2]);
    }

    public int query(int ql, int qr) {
        return query(0, 0, n - 1, ql, qr);
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l)
            return Integer.MAX_VALUE;
        if (ql <= l && r <= qr)
            return segmentTree[node];
        int mid = (l + r) / 2;
        return Math.min(query(2 * node + 1, l, mid, ql, qr), query(2 * node + 2, mid + 1, r, ql, qr));
    }
}