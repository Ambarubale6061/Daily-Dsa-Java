public class NCr {
    public static long nCr(int n, int r) {
        if (r > n)
            return 0;
        long res = 1;
        for (int i = 0; i < r; i++) {
            res = res * (n - i) / (i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(nCr(5, 2)); // 10
    }
}