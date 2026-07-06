import java.util.*;

public class RestoreIPAddresses {
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(String s, int idx, List<String> parts, List<String> res) {
        if (parts.size() == 4) {
            if (idx == s.length())
                res.add(String.join(".", parts));
            return;
        }
        for (int len = 1; len <= 3 && idx + len <= s.length(); len++) {
            String segment = s.substring(idx, idx + len);
            if (isValid(segment)) {
                parts.add(segment);
                backtrack(s, idx + len, parts, res);
                parts.remove(parts.size() - 1);
            }
        }
    }

    private static boolean isValid(String seg) {
        if (seg.length() > 1 && seg.charAt(0) == '0')
            return false;
        int val = Integer.parseInt(seg);
        return val >= 0 && val <= 255;
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }
}