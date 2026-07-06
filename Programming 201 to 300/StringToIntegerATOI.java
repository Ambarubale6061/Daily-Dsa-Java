public class StringToIntegerATOI {
    public static int myAtoi(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int i = 0, n = s.length(), sign = 1;
        while (i < n && s.charAt(i) == ' ')
            i++;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }
        int num = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            if (num > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            i++;
        }
        return num * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("   -42")); // -42
    }
}