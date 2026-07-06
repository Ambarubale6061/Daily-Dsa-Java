public class KMPAlgorithm {
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty())
            return 0;
        int[] lps = computeLPS(needle);
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length())
                    return i - j;
            } else if (j > 0) {
                j = lps[j - 1];
            } else
                i++;
        }
        return -1;
    }

    private static int[] computeLPS(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0, i = 1;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else
                lps[i++] = 0;
        }
        return lps;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll")); // 2
    }
}