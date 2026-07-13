public class ReversePairs {
    public static int reversePairs(int[] nums) {
        return mergeSort(nums, new int[nums.length], 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int[] temp, int l, int r) {
        if (l >= r)
            return 0;
        int m = l + (r - l) / 2;
        int count = mergeSort(nums, temp, l, m) + mergeSort(nums, temp, m + 1, r);
        int j = m + 1;
        for (int i = l; i <= m; i++) {
            while (j <= r && nums[i] > 2L * nums[j])
                j++;
            count += j - (m + 1);
        }
        merge(nums, temp, l, m, r);
        return count;
    }

    private static void merge(int[] nums, int[] temp, int l, int m, int r) {
        System.arraycopy(nums, l, temp, l, r - l + 1);
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (temp[i] <= temp[j])
                nums[k++] = temp[i++];
            else
                nums[k++] = temp[j++];
        }
        while (i <= m)
            nums[k++] = temp[i++];
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 2, 3, 1 };
        System.out.println(reversePairs(nums)); // 2
    }
}