import java.util.*;

public class ReorganizeString {
    public static String reorganizeString(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freq[b - 'a'] - freq[a - 'a']);
        for (char c = 'a'; c <= 'z'; c++)
            if (freq[c - 'a'] > 0)
                maxHeap.add(c);
        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() > 1) {
            char first = maxHeap.poll();
            char second = maxHeap.poll();
            sb.append(first).append(second);
            if (--freq[first - 'a'] > 0)
                maxHeap.add(first);
            if (--freq[second - 'a'] > 0)
                maxHeap.add(second);
        }
        if (!maxHeap.isEmpty()) {
            char last = maxHeap.poll();
            if (freq[last - 'a'] > 1)
                return "";
            sb.append(last);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab")); // "aba"
    }
}