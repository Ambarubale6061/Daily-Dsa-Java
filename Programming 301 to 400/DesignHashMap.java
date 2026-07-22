class Node {
    int key, val;
    Node next;

    Node(int k, int v) {
        key = k;
        val = v;
    }
}

public class DesignHashMap {
    Node[] nodes;

    public DesignHashMap() {
        nodes = new Node[10000];
    }

    private int hash(int key) {
        return key % nodes.length;
    }

    public void put(int key, int value) {
        int idx = hash(key);
        if (nodes[idx] == null)
            nodes[idx] = new Node(-1, -1);
        Node prev = find(nodes[idx], key);
        if (prev.next == null)
            prev.next = new Node(key, value);
        else
            prev.next.val = value;
    }

    public int get(int key) {
        int idx = hash(key);
        if (nodes[idx] == null)
            return -1;
        Node prev = find(nodes[idx], key);
        return prev.next == null ? -1 : prev.next.val;
    }

    public void remove(int key) {
        int idx = hash(key);
        if (nodes[idx] == null)
            return;
        Node prev = find(nodes[idx], key);
        if (prev.next != null)
            prev.next = prev.next.next;
    }

    private Node find(Node head, int key) {
        Node curr = head, prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }
}