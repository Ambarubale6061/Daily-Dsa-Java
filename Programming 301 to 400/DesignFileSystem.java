import java.util.*;

public class DesignFileSystem {
    class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        int value = -1;
    }

    TrieNode root;

    public DesignFileSystem() {
        root = new TrieNode();
    }

    public boolean createPath(String path, int value) {
        if (path.isEmpty() || path.equals("/"))
            return false;
        String[] parts = path.split("/");
        TrieNode node = root;
        for (int i = 1; i < parts.length - 1; i++) {
            if (!node.children.containsKey(parts[i]))
                return false;
            node = node.children.get(parts[i]);
        }
        String last = parts[parts.length - 1];
        if (node.children.containsKey(last))
            return false;
        node.children.put(last, new TrieNode());
        node.children.get(last).value = value;
        return true;
    }

    public int get(String path) {
        String[] parts = path.split("/");
        TrieNode node = root;
        for (int i = 1; i < parts.length; i++) {
            if (!node.children.containsKey(parts[i]))
                return -1;
            node = node.children.get(parts[i]);
        }
        return node.value;
    }
}