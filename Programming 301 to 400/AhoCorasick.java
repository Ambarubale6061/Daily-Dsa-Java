import java.util.*;

public class AhoCorasick {
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node fail;
        List<Integer> output = new ArrayList<>();
    }

    Node root;

    public AhoCorasick(String[] patterns) {
        root = new Node();
        for (int i = 0; i < patterns.length; i++) {
            insert(patterns[i], i);
        }
        buildFailLinks();
    }

    private void insert(String s, int idx) {
        Node cur = root;
        for (char c : s.toCharArray()) {
            cur = cur.children.computeIfAbsent(c, k -> new Node());
        }
        cur.output.add(idx);
    }

    private void buildFailLinks() {
        Queue<Node> q = new LinkedList<>();
        for (Node child : root.children.values()) {
            child.fail = root;
            q.add(child);
        }
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Map.Entry<Character, Node> e : cur.children.entrySet()) {
                char c = e.getKey();
                Node child = e.getValue();
                Node fail = cur.fail;
                while (fail != null && !fail.children.containsKey(c))
                    fail = fail.fail;
                child.fail = (fail != null) ? fail.children.get(c) : root;
                child.output.addAll(child.fail.output);
                q.add(child);
            }
        }
    }
}