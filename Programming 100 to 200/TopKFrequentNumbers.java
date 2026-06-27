import java.util.*;

public class TopKFrequentNumbers {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums)
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(freq::get));
        for (int n : freq.keySet()) {
            minHeap.add(n);
            if (minHeap.size() > k)
                minHeap.poll();
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = minHeap.poll();
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        System.out.println(Arrays.toString(topKFrequent(nums, 2))); // [2,1]
    }
}