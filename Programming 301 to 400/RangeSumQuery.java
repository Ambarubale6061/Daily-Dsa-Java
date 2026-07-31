public class RangeSumQuery {
    // Using Fenwick tree
    int[] bit;
    int[] nums;

    public RangeSumQuery(int[] nums) {
        this.nums = nums.clone();
        bit = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++)
            add(i, nums[i]);
    }

    private void add(int idx, int val) {
        for (int i = idx + 1; i < bit.length; i += i & -i)
            bit[i] += val;
    }

    public void update(int index, int val) {
        int diff = val - nums[index];
        nums[index] = val;
        add(index, diff);
    }

    public int sumRange(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    private int prefixSum(int idx) {
        int sum = 0;
        for (int i = idx + 1; i > 0; i -= i & -i)
            sum += bit[i];
        return sum;
    }
}