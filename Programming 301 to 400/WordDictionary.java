class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
}

public class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
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
        return searchInNode(word, 0, root);
    }

    private boolean searchInNode(String word, int idx, TrieNode node) {
        if (node == null)
            return false;
        if (idx == word.length())
            return node.isEnd;
        char c = word.charAt(idx);
        if (c == '.') {
            for (TrieNode child : node.children) {
                if (searchInNode(word, idx + 1, child))
                    return true;
            }
            return false;
        } else {
            int next = c - 'a';
            return searchInNode(word, idx + 1, node.children[next]);
        }
    }
}