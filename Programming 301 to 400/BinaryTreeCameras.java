class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeCameras {
    static int cameras = 0;

    public static int minCameraCover(TreeNode root) {
        cameras = 0;
        if (dfs(root) == 0)
            cameras++;
        return cameras;
    }

    // 0: not covered, 1: covered but no camera, 2: has camera
    private static int dfs(TreeNode node) {
        if (node == null)
            return 1;
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left == 0 || right == 0) {
            cameras++;
            return 2;
        }
        if (left == 2 || right == 2)
            return 1;
        return 0;
    }
}