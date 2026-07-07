public class RabinKarp {
    public static int search(String text, String pattern) {
        int d = 256, q = 101, m = pattern.length(), n = text.length();
        if (m > n)
            return -1;
        int h = 1;
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % q;
        int p = 0, t = 0;
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }
        for (int i = 0; i <= n - m; i++) {
            if (p == t) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    return i;
            }
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
                if (t < 0)
                    t += q;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search("GEEKS FOR GEEKS", "FOR")); // 6
    }
}