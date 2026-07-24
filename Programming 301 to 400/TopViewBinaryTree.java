import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class TopViewBinaryTree {
    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode n, int h) {
            node = n;
            hd = h;
        }
    }

    public static List<Integer> topView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (!map.containsKey(p.hd))
                map.put(p.hd, p.node.val);
            if (p.node.left != null)
                q.add(new Pair(p.node.left, p.hd - 1));
            if (p.node.right != null)
                q.add(new Pair(p.node.right, p.hd + 1));
        }
        res.addAll(map.values());
        return res;
    }
}