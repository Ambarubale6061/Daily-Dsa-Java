class TrieNode {
    boolean isEnd;
    TrieNode[] children = new TrieNode[26];
}

public class StreamOfCharacters {
    TrieNode root;
    StringBuilder sb;

    public StreamOfCharacters(String[] words) {
        root = new TrieNode();
        sb = new StringBuilder();
        for (String w : words) {
            TrieNode node = root;
            // insert reversed
            for (int i = w.length() - 1; i >= 0; i--) {
                int idx = w.charAt(i) - 'a';
                if (node.children[idx] == null)
                    node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.isEnd = true;
        }
    }

    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        for (int i = sb.length() - 1; i >= 0; i--) {
            int idx = sb.charAt(i) - 'a';
            if (node.children[idx] == null)
                return false;
            node = node.children[idx];
            if (node.isEnd)
                return true;
        }
        return false;
    }
}