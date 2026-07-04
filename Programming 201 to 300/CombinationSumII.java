import java.util.*;

public class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] cand, int remain, int start, List<Integer> temp, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < cand.length; i++) {
            if (i > start && cand[i] == cand[i - 1])
                continue;
            if (cand[i] > remain)
                break;
            temp.add(cand[i]);
            backtrack(cand, remain - cand[i], i + 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] cand = { 10, 1, 2, 7, 6, 1, 5 };
        System.out.println(combinationSum2(cand, 8));
    }
}