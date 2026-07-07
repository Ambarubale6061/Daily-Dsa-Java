import java.util.*;

public class GroupShiftedStrings {
    public static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String key = getShiftKey(s);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private static String getShiftKey(String s) {
        if (s.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        char first = s.charAt(0);
        for (char c : s.toCharArray()) {
            int diff = (c - first + 26) % 26;
            sb.append(diff).append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
        System.out.println(groupStrings(strs));
    }
}