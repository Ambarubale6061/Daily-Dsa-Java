public class CountOccurrences {
    public static int first(int[] arr, int target) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target) {
                ans = m;
                r = m - 1;
            } else if (arr[m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        return ans;
    }

    public static int last(int[] arr, int target) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target) {
                ans = m;
                l = m + 1;
            } else if (arr[m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        return ans;
    }

    public static int count(int[] arr, int target) {
        int f = first(arr, target);
        if (f == -1)
            return 0;
        return last(arr, target) - f + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 2, 3 };
        System.out.println(count(arr, 2)); // 3
    }
}