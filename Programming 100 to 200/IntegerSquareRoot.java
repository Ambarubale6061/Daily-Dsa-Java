public class IntegerSquareRoot {
    public static int mySqrt(int x) {
        if (x == 0)
            return 0;
        int l = 1, r = x, ans = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (m <= x / m) {
                ans = m;
                l = m + 1;
            } else
                r = m - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8)); // 2
        System.out.println(mySqrt(16)); // 4
    }
}