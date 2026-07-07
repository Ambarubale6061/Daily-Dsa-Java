public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray())
            map[c]++;
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0)
                counter--;
            while (counter == 0) {
                if (end - start < minLen) {
                    minLen = end - start;
                    minStart = start;
                }
                if (map[s.charAt(start++)]++ == 0)
                    counter++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
    }
}