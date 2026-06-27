import java.util.PriorityQueue;

public class KClosestPoints {
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
        for (int[] p : points) {
            maxHeap.add(p);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++)
            res[i] = maxHeap.poll();
        return res;
    }

    public static void main(String[] args) {
        int[][] points = { { 1, 3 }, { -2, 2 } };
        int[][] res = kClosest(points, 1);
        System.out.println(java.util.Arrays.deepToString(res)); // [[-2,2]]
    }
}