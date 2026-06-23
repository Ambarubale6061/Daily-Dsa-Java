import java.util.Arrays;

public class CyclicSort {
    public static void cyclicSort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correct = nums[i] - 1; // assuming numbers from 1 to n
            if (nums[i] != nums[correct]) {
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            } else
                i++;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 5, 2, 1, 4 };
        cyclicSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}