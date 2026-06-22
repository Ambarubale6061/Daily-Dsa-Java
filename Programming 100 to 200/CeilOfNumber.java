public class CeilOfNumber {
    public static int ceil(int[] arr, int target) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target)
                return arr[m];
            else if (arr[m] > target) {
                ans = arr[m];
                r = m - 1;
            } else
                l = m + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 8, 10, 11, 12, 19 };
        System.out.println(ceil(arr, 5)); // 8
    }
}