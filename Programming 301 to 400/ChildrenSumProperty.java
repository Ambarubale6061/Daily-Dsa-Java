class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ChildrenSumProperty {
    public static boolean isChildrenSum(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;
        int sum = 0;
        if (root.left != null)
            sum += root.left.val;
        if (root.right != null)
            sum += root.right.val;
        return (root.val == sum) && isChildrenSum(root.left) && isChildrenSum(root.right);
    }
}