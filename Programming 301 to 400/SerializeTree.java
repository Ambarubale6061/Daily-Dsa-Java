import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class SerializeTree {
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sHelper(root, sb);
        return sb.toString();
    }

    private static void sHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        sb.append(node.val).append(",");
        sHelper(node.left, sb);
        sHelper(node.right, sb);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(serialize(root)); // 1,2,null,null,3,null,null,
    }
}