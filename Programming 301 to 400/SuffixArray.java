import java.util.Arrays;

public class SuffixArray {
    // O(n log n) construction
    public static int[] buildSuffixArray(String s) {
        int n = s.length();
        Integer[] sa = new Integer[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = s.charAt(i);
        }
        for (int k = 1; k < n; k <<= 1) {
            final int K = k;
            Arrays.sort(sa, (a, b) -> {
                if (rank[a] != rank[b])
                    return rank[a] - rank[b];
                int ra = (a + K < n) ? rank[a + K] : -1;
                int rb = (b + K < n) ? rank[b + K] : -1;
                return ra - rb;
            });
            int[] newRank = new int[n];
            newRank[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                int prev = sa[i - 1], cur = sa[i];
                int ra1 = rank[prev], ra2 = (prev + K < n) ? rank[prev + K] : -1;
                int rb1 = rank[cur], rb2 = (cur + K < n) ? rank[cur + K] : -1;
                newRank[cur] = newRank[prev] + (ra1 != rb1 || ra2 != rb2 ? 1 : 0);
            }
            rank = newRank;
        }
        return Arrays.stream(sa).mapToInt(i -> i).toArray();
    }
}