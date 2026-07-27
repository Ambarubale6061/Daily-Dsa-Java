class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class RecoverBST {
    static TreeNode first = null, second = null, prev = null;

    public static void recoverTree(TreeNode root) {
        first = second = prev = null;
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private static void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        if (prev != null && prev.val >= node.val) {
            if (first == null)
                first = prev;
            second = node;
        }
        prev = node;
        inorder(node.right);
    }
}