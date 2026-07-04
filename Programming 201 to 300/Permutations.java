import java.util.*;

public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), new boolean[nums.length], res);
        return res;
    }

    private static void backtrack(int[] nums, List<Integer> temp, boolean[] used, List<List<Integer>> res) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            used[i] = true;
            temp.add(nums[i]);
            backtrack(nums, temp, used, res);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[] { 1, 2, 3 }));
    }
}