import java.util.*;

public class FrequencySort {
    public static String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        PriorityQueue<Character> heap = new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));
        heap.addAll(freq.keySet());
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            char c = heap.poll();
            int count = freq.get(c);
            for (int i = 0; i < count; i++)
                sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree")); // eert
    }
}