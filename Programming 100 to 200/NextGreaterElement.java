import java.util.*;

public class NextGreaterElement {
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                res[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 5, 2, 10 };
        System.out.println(Arrays.toString(nextGreaterElement(nums))); // [5,10,10,-1]
    }
}