import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BoundaryTraversal {
    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        res.add(root.val);
        leftBoundary(root.left, res);
        leaves(root.left, res);
        leaves(root.right, res);
        rightBoundary(root.right, res);
        return res;
    }

    private static void leftBoundary(TreeNode node, List<Integer> res) {
        while (node != null && (node.left != null || node.right != null)) {
            res.add(node.val);
            node = node.left != null ? node.left : node.right;
        }
    }

    private static void leaves(TreeNode node, List<Integer> res) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            res.add(node.val);
            return;
        }
        leaves(node.left, res);
        leaves(node.right, res);
    }

    private static void rightBoundary(TreeNode node, List<Integer> res) {
        Stack<Integer> stack = new Stack<>();
        while (node != null && (node.left != null || node.right != null)) {
            stack.push(node.val);
            node = node.right != null ? node.right : node.left;
        }
        while (!stack.isEmpty())
            res.add(stack.pop());
    }
}