public class KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int p : piles)
            r = Math.max(r, p);
        while (l < r) {
            int m = l + (r - l) / 2;
            int hours = 0;
            for (int p : piles)
                hours += (p + m - 1) / m;
            if (hours <= h)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] piles = { 3, 6, 7, 11 };
        int h = 8;
        System.out.println(minEatingSpeed(piles, h)); // 4
    }
}