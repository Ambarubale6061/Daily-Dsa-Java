import java.util.*;

public class TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char t : tasks)
            freq[t - 'A']++;
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int idleSlots = (maxFreq - 1) * n;
        for (int i = 24; i >= 0; i--) {
            idleSlots -= Math.min(freq[i], maxFreq - 1);
        }
        return tasks.length + Math.max(0, idleSlots);
    }

    public static void main(String[] args) {
        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
        System.out.println(leastInterval(tasks, 2)); // 8
    }
}