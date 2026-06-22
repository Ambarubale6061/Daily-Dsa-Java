public class ShipPackagesWithinDays {
    public static int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 0;
        for (int w : weights) {
            l = Math.max(l, w);
            r += w;
        }
        while (l < r) {
            int m = l + (r - l) / 2;
            int cur = 0, need = 1;
            for (int w : weights) {
                if (cur + w > m) {
                    need++;
                    cur = 0;
                }
                cur += w;
            }
            if (need <= days)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] weights = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int days = 5;
        System.out.println(shipWithinDays(weights, days)); // 15
    }
}