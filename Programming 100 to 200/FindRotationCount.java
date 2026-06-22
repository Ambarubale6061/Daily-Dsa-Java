public class FindRotationCount {
    public static int findRotationCount(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] > arr[r])
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] arr = { 15, 18, 2, 3, 6, 12 };
        System.out.println(findRotationCount(arr)); // 2
    }
}