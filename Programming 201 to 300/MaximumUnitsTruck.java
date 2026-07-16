import java.util.Arrays;

public class MaximumUnitsTruck {
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int total = 0;
        for (int[] box : boxTypes) {
            int count = Math.min(truckSize, box[0]);
            total += count * box[1];
            truckSize -= count;
            if (truckSize == 0)
                break;
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] boxTypes = { { 1, 3 }, { 2, 2 }, { 3, 1 } };
        System.out.println(maximumUnits(boxTypes, 4)); // 8
    }
}