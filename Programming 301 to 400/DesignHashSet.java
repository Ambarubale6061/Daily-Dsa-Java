class Node {
    int key;
    Node next;

    Node(int k) {
        key = k;
    }
}

public class DesignHashSet {
    Node[] nodes;

    public DesignHashSet() {
        nodes = new Node[10000];
    }

    private int hash(int key) {
        return key % nodes.length;
    }

    public void add(int key) {
        int idx = hash(key);
        if (nodes[idx] == null)
            nodes[idx] = new Node(-1);
        Node prev = find(nodes[idx], key);
        if (prev.next == null)
            prev.next = new Node(key);
    }

    public void remove(int key) {
        int idx = hash(key);
        if (nodes[idx] == null)
            return;
        Node prev = find(nodes[idx], key);
        if (prev.next != null)
            prev.next = prev.next.next;
    }

    public boolean contains(int key) {
        int idx = hash(key);
        if (nodes[idx] == null)
            return false;
        return find(nodes[idx], key).next != null;
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