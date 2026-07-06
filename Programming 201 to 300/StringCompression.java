public class StringCompression {
    public static int compress(char[] chars) {
        int idx = 0, i = 0;
        while (i < chars.length) {
            char cur = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == cur) {
                i++;
                count++;
            }
            chars[idx++] = cur;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray())
                    chars[idx++] = c;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        char[] chars = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
        int len = compress(chars);
        System.out.println(new String(chars, 0, len)); // a2b2c3
    }
}