import java.util.*;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] cand, int remain, int start, List<Integer> temp, List<List<Integer>> res) {
        if (remain < 0)
            return;
        if (remain == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < cand.length; i++) {
            temp.add(cand[i]);
            backtrack(cand, remain - cand[i], i, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] cand = { 2, 3, 6, 7 };
        System.out.println(combinationSum(cand, 7));
    }
}