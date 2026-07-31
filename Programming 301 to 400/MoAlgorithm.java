import java.util.*;

public class MoAlgorithm {
    static class Query implements Comparable<Query> {
        int l, r, idx;

        Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }

        public int compareTo(Query other) {
            int block = l / BLOCK;
            int oblock = other.l / BLOCK;
            if (block != oblock)
                return block - oblock;
            return (block % 2 == 0) ? r - other.r : other.r - r;
        }
    }

    static int BLOCK;

    public static int[] process(int[] arr, int[][] queries) {
        int n = arr.length, q = queries.length;
        BLOCK = (int) Math.sqrt(n);
        Query[] qs = new Query[q];
        for (int i = 0; i < q; i++)
            qs[i] = new Query(queries[i][0], queries[i][1], i);
        Arrays.sort(qs);
        int[] result = new int[q];
        int curL = 0, curR = -1, curAns = 0;
        int[] freq = new int[1000001]; // adjust size as needed
        for (Query qr : qs) {
            while (curL > qr.l) {
                curL--;
                add(arr[curL], freq, curAns);
            }
            while (curR < qr.r) {
                curR++;
                add(arr[curR], freq, curAns);
            }
            while (curL < qr.l) {
                remove(arr[curL], freq, curAns);
                curL++;
            }
            while (curR > qr.r) {
                remove(arr[curR], freq, curAns);
                curR--;
            }
            result[qr.idx] = curAns;
        }
        return result;
    }

    private static void add(int val, int[] freq, int curAns) {
        freq[val]++;
    } // simplified

    private static void remove(int val, int[] freq, int curAns) {
        freq[val]--;
    }
}