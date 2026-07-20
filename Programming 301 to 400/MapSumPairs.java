class TrieNode {
    int val;
    TrieNode[] children = new TrieNode[26];
}

public class MapSumPairs {
    TrieNode root;

    public MapSumPairs() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode node = root;
        for (char c : key.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.val = val;
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return 0;
            node = node.children[idx];
        }
        return dfs(node);
    }

    private int dfs(TrieNode node) {
        int sum = node.val;
        for (TrieNode child : node.children) {
            if (child != null)
                sum += dfs(child);
        }
        return sum;
    }
}