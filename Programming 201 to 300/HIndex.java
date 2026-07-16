public class HIndex {
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int c : citations) {
            if (c >= n)
                count[n]++;
            else
                count[c]++;
        }
        int total = 0;
        for (int i = n; i >= 0; i--) {
            total += count[i];
            if (total >= i)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] citations = { 3, 0, 6, 1, 5 };
        System.out.println(hIndex(citations)); // 3
    }
}