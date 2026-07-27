public class DPOnStocksI {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, max = 0;
        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            max = Math.max(max, p - minPrice);
        }
        return max;
    }
}