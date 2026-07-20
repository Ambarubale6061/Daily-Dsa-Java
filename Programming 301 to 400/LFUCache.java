import java.util.*;

public class LFUCache {
    class Node {
        int key, val, freq;

        Node(int k, int v) {
            key = k;
            val = v;
            freq = 1;
        }
    }

    Map<Integer, Node> cache = new HashMap<>();
    Map<Integer, LinkedHashSet<Node>> freqMap = new HashMap<>();
    int capacity, minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        minFreq = 0;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null)
            return -1;
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;
        Node node = cache.get(key);
        if (node != null) {
            node.val = value;
            updateFreq(node);
        } else {
            if (cache.size() == capacity) {
                LinkedHashSet<Node> set = freqMap.get(minFreq);
                Node evict = set.iterator().next();
                set.remove(evict);
                cache.remove(evict.key);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(newNode);
            minFreq = 1;
        }
    }

    private void updateFreq(Node node) {
        int freq = node.freq;
        freqMap.get(freq).remove(node);
        if (freq == minFreq && freqMap.get(freq).isEmpty())
            minFreq++;
        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new LinkedHashSet<>()).add(node);
    }
}