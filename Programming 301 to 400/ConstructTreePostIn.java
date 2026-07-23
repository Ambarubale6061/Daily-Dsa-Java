class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ConstructTreePostIn {
    static int postIdx;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] in, int[] post, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(post[postIdx--]);
        int inIdx = -1;
        for (int i = inStart; i <= inEnd; i++)
            if (in[i] == root.val) {
                inIdx = i;
                break;
            }
        root.right = build(in, post, inIdx + 1, inEnd);
        root.left = build(in, post, inStart, inIdx - 1);
        return root;
    }
}