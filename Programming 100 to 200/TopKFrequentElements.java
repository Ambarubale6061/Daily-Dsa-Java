import java.util.*;

public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums)
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(freq::get));
        for (int n : freq.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = heap.poll();
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        System.out.println(Arrays.toString(topKFrequent(nums, 2))); // [1,2]
    }
}