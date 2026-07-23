class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
    }
}

public class BinaryTreeToDLL {
    static Node prev = null, head = null;

    public static Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        prev = null;
        head = null;
        convert(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    private static void convert(Node node) {
        if (node == null)
            return;
        convert(node.left);
        if (prev == null)
            head = node;
        else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        convert(node.right);
    }
}