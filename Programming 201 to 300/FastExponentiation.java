public class FastExponentiation {
    public static double pow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        while (N > 0) {
            if ((N & 1) == 1)
                ans *= x;
            x *= x;
            N >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(pow(2.0, 10)); // 1024.0
    }
}