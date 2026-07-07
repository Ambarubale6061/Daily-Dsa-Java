public class IsomorphicStrings {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] ms = new int[256], mt = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (ms[c1] != mt[c2])
                return false;
            ms[c1] = i + 1;
            mt[c2] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add")); // true
    }
}