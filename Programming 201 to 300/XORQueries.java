public class XORQueries {
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] ^ arr[i];
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            res[i] = prefix[r + 1] ^ prefix[l];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 4, 8 };
        int[][] queries = { { 0, 1 }, { 1, 2 }, { 0, 3 }, { 3, 3 } };
        System.out.println(java.util.Arrays.toString(xorQueries(arr, queries))); // [2,7,14,8]
    }
}