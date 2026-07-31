public class RollingHash {
    static final long BASE = 91138233L, MOD = 972663749L;

    public static long[] buildHash(String s) {
        long[] hash = new long[s.length() + 1];
        long[] pow = new long[s.length() + 1];
        pow[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            hash[i + 1] = (hash[i] * BASE + s.charAt(i)) % MOD;
            pow[i + 1] = (pow[i] * BASE) % MOD;
        }
        return hash; // store pow separately
    }

    public static long getHash(long[] hash, long[] pow, int l, int r) {
        long h = hash[r + 1] - (hash[l] * pow[r - l + 1]) % MOD;
        if (h < 0)
            h += MOD;
        return h;
    }
}