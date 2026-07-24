import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class PathSumII {
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(TreeNode node, int remain, List<Integer> path, List<List<Integer>> res) {
        if (node == null)
            return;
        path.add(node.val);
        if (node.left == null && node.right == null && remain == node.val)
            res.add(new ArrayList<>(path));
        dfs(node.left, remain - node.val, path, res);
        dfs(node.right, remain - node.val, path, res);
        path.remove(path.size() - 1);
    }
}