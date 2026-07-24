import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class DeserializeTree {
    public static TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return dHelper(q);
    }

    private static TreeNode dHelper(Queue<String> q) {
        String val = q.poll();
        if (val.equals("null"))
            return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = dHelper(q);
        node.right = dHelper(q);
        return node;
    }
}