class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class CountGoodNodes {
    public static int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private static int dfs(TreeNode node, int maxSoFar) {
        if (node == null)
            return 0;
        int count = node.val >= maxSoFar ? 1 : 0;
        maxSoFar = Math.max(maxSoFar, node.val);
        count += dfs(node.left, maxSoFar);
        count += dfs(node.right, maxSoFar);
        return count;
    }
}