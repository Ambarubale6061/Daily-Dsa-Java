import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class VerticalOrderTraversal {
    static class Pair {
        TreeNode node;
        int x, y;

        Pair(TreeNode n, int x, int y) {
            node = n;
            this.x = x;
            this.y = y;
        }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            map.putIfAbsent(p.x, new TreeMap<>());
            map.get(p.x).putIfAbsent(p.y, new PriorityQueue<>());
            map.get(p.x).get(p.y).add(p.node.val);
            if (p.node.left != null)
                q.add(new Pair(p.node.left, p.x - 1, p.y + 1));
            if (p.node.right != null)
                q.add(new Pair(p.node.right, p.x + 1, p.y + 1));
        }
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            List<Integer> col = new ArrayList<>();
            for (PriorityQueue<Integer> pq : ys.values()) {
                while (!pq.isEmpty())
                    col.add(pq.poll());
            }
            res.add(col);
        }
        return res;
    }
}