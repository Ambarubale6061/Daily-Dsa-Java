import java.util.Arrays;

public class BookAllocation {
    public static int minPages(int[] books, int students) {
        if (students > books.length)
            return -1;
        int l = Arrays.stream(books).max().getAsInt();
        int r = Arrays.stream(books).sum();
        int ans = r;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (isPossible(books, students, m)) {
                ans = m;
                r = m - 1;
            } else
                l = m + 1;
        }
        return ans;
    }

    private static boolean isPossible(int[] books, int students, int max) {
        int count = 1, sum = 0;
        for (int b : books) {
            if (sum + b > max) {
                count++;
                sum = b;
                if (count > students)
                    return false;
            } else
                sum += b;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] books = { 12, 34, 67, 90 };
        int m = 2;
        System.out.println(minPages(books, m)); // 113
    }
}