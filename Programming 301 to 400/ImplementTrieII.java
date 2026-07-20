class TrieNode {
    TrieNode[] children = new TrieNode[26];
    int countEnd = 0, countPrefix = 0;
}

public class ImplementTrieII {
    TrieNode root;

    public ImplementTrieII() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
            node.countPrefix++;
        }
        node.countEnd++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = find(word);
        return node == null ? 0 : node.countEnd;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode node = find(prefix);
        return node == null ? 0 : node.countPrefix;
    }

    public void erase(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            node = node.children[idx];
            node.countPrefix--;
        }
        node.countEnd--;
    }

    private TrieNode find(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return null;
            node = node.children[idx];
        }
        return node;
    }
}