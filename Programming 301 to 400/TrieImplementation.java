class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
}

public class TrieImplementation {
    TrieNode root;

    public TrieImplementation() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
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

    public static void main(String[] args) {
        TrieImplementation trie = new TrieImplementation();
        trie.insert("apple");
        System.out.println(trie.search("apple")); // true
        System.out.println(trie.startsWith("app")); // true
    }
}