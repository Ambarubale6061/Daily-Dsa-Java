class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class HouseRobberIII {
    public static int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    private static int[] dfs(TreeNode node) {
        if (node == null)
            return new int[2];
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[] { rob, notRob };
    }
}