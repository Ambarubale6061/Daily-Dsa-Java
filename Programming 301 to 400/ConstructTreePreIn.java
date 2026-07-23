class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ConstructTreePreIn {
    static int preIdx = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preIdx = 0;
        return build(preorder, inorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] pre, int[] in, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(pre[preIdx++]);
        int inIdx = -1;
        for (int i = inStart; i <= inEnd; i++)
            if (in[i] == root.val) {
                inIdx = i;
                break;
            }
        root.left = build(pre, in, inStart, inIdx - 1);
        root.right = build(pre, in, inIdx + 1, inEnd);
        return root;
    }
}