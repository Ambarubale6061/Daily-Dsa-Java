import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BurnBinaryTree {
    static Map<TreeNode, TreeNode> parent = new HashMap<>();

    public static int timeToBurn(TreeNode root, TreeNode target) {
        // Build parent map and find target
        buildParent(root, null);
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.add(target);
        visited.add(target);
        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            boolean burned = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null && visited.add(node.left)) {
                    q.add(node.left);
                    burned = true;
                }
                if (node.right != null && visited.add(node.right)) {
                    q.add(node.right);
                    burned = true;
                }
                TreeNode p = parent.get(node);
                if (p != null && visited.add(p)) {
                    q.add(p);
                    burned = true;
                }
            }
            if (burned)
                time++;
        }
        return time;
    }

    private static void buildParent(TreeNode node, TreeNode par) {
        if (node == null)
            return;
        parent.put(node, par);
        buildParent(node.left, node);
        buildParent(node.right, node);
    }
}