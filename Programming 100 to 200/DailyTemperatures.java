import java.util.*;

public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int idx = stack.pop();
                ans[idx] = i - idx;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] temps = { 73, 74, 75, 71, 69, 72, 76, 73 };
        System.out.println(Arrays.toString(dailyTemperatures(temps))); // [1,1,4,2,1,1,0,0]
    }
}