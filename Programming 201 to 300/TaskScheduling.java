import java.util.Arrays;

public class TaskScheduling {
    // Maximum number of non-overlapping intervals
    public static int maxTasks(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int count = 0, lastEnd = Integer.MIN_VALUE;
        for (int[] t : tasks) {
            if (t[0] >= lastEnd) {
                count++;
                lastEnd = t[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] tasks = { { 1, 3 }, { 2, 5 }, { 3, 9 }, { 6, 8 } };
        System.out.println(maxTasks(tasks)); // 3
    }
}