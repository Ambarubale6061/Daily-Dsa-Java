public class LastOccurrence {
    public static int lastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                ans = mid;
                left = mid + 1;
            } else if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 2, 3, 4 };
        System.out.println(lastOccurrence(arr, 2)); // 3
    }
}