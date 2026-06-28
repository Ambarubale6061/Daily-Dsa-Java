import java.util.PriorityQueue;

public class ConnectRopes {
    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int r : ropes)
            minHeap.add(r);
        int totalCost = 0;
        while (minHeap.size() > 1) {
            int sum = minHeap.poll() + minHeap.poll();
            totalCost += sum;
            minHeap.add(sum);
        }
        return totalCost;
    }

    public static void main(String[] args) {
        int[] ropes = { 4, 3, 2, 6 };
        System.out.println(minCost(ropes)); // 29
    }
}