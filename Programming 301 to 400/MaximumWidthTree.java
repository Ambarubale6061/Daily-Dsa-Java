import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class MaximumWidthTree {
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        Queue<Object[]> q = new LinkedList<>(); // node, index
        q.add(new Object[] { root, 1 });
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int left = (int) q.peek()[1], right = left;
            for (int i = 0; i < size; i++) {
                Object[] cur = q.poll();
                TreeNode node = (TreeNode) cur[0];
                right = (int) cur[1];
                if (node.left != null)
                    q.add(new Object[] { node.left, 2 * right });
                if (node.right != null)
                    q.add(new Object[] { node.right, 2 * right + 1 });
            }
            maxWidth = Math.max(maxWidth, right - left + 1);
        }
        return maxWidth;
    }
}