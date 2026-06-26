import java.util.*;

public class IntersectionOfArrays {
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums1)
            map.put(n, map.getOrDefault(n, 0) + 1);
        List<Integer> list = new ArrayList<>();
        for (int n : nums2) {
            if (map.getOrDefault(n, 0) > 0) {
                list.add(n);
                map.put(n, map.get(n) - 1);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 2, 1 }, b = { 2, 2 };
        System.out.println(Arrays.toString(intersect(a, b))); // [2,2]
    }
}