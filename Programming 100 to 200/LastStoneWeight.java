import java.util.PriorityQueue;
import java.util.Collections;

public class LastStoneWeight {
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones)
            maxHeap.add(s);
        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(), x = maxHeap.poll();
            if (y != x)
                maxHeap.add(y - x);
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    public static void main(String[] args) {
        int[] stones = { 2, 7, 4, 1, 8, 1 };
        System.out.println(lastStoneWeight(stones)); // 1
    }
}