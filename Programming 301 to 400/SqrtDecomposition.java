public class SqrtDecomposition {
    int[] arr;
    int[] block;
    int blockSize;

    public SqrtDecomposition(int[] nums) {
        arr = nums;
        blockSize = (int) Math.ceil(Math.sqrt(nums.length));
        block = new int[blockSize];
        for (int i = 0; i < nums.length; i++)
            block[i / blockSize] += nums[i];
    }

    public void update(int idx, int val) {
        int b = idx / blockSize;
        block[b] += val - arr[idx];
        arr[idx] = val;
    }

    public int sumRange(int l, int r) {
        int sum = 0;
        int startBlock = l / blockSize, endBlock = r / blockSize;
        if (startBlock == endBlock) {
            for (int i = l; i <= r; i++)
                sum += arr[i];
        } else {
            for (int i = l; i < (startBlock + 1) * blockSize; i++)
                sum += arr[i];
            for (int i = startBlock + 1; i < endBlock; i++)
                sum += block[i];
            for (int i = endBlock * blockSize; i <= r; i++)
                sum += arr[i];
        }
        return sum;
    }
}