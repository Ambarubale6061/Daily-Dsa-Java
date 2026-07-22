import java.util.*;

public class DesignAutocompleteSystem {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
    }

    TrieNode root;
    String prefix;

    public DesignAutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        for (int i = 0; i < sentences.length; i++)
            add(sentences[i], times[i]);
    }

    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        prefix += c;
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch))
                return new ArrayList<>();
            node = node.children.get(ch);
        }
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> node.counts.get(a).equals(node.counts.get(b)) ? b.compareTo(a)
                        : node.counts.get(a) - node.counts.get(b));
        for (String s : node.counts.keySet()) {
            pq.add(s);
            if (pq.size() > 3)
                pq.poll();
        }
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty())
            res.add(0, pq.poll());
        return res;
    }

    private void add(String sentence, int times) {
        TrieNode node = root;
        for (char c : sentence.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.counts.put(sentence, node.counts.getOrDefault(sentence, 0) + times);
        }
    }
}