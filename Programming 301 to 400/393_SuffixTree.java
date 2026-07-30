import java.util.*;

class SuffixTree {
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        int start, end;
        Node suffixLink;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    Node root;
    String text;

    // Basic implementation not full Ukkonen; placeholder
    public SuffixTree(String text) {
        this.text = text;
        root = new Node(-1, -1);
        // Naive construction O(n^2)
        for (int i = 0; i < text.length(); i++) {
            Node cur = root;
            for (int j = i; j < text.length(); j++) {
                char c = text.charAt(j);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new Node(j, text.length()));
                    break;
                }
                cur = cur.children.get(c);
            }
        }
    }
}