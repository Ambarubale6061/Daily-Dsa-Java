import java.util.Stack;

public class DecodeString {
    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(count);
                strStack.push(cur);
                cur = new StringBuilder();
                count = 0;
            } else if (c == ']') {
                StringBuilder temp = cur;
                cur = strStack.pop();
                int repeat = countStack.pop();
                for (int i = 0; i < repeat; i++)
                    cur.append(temp);
            } else
                cur.append(c);
        }
        return cur.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]")); // aaabcbc
    }
}