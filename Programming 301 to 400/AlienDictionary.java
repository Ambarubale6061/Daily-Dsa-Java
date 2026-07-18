import java.util.*;

public class AlienDictionary {
    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for (String w : words)
            for (char c : w.toCharArray())
                indegree.putIfAbsent(c, 0);
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2))
                return "";
            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    adj.putIfAbsent(c1, new HashSet<>());
                    if (!adj.get(c1).contains(c2)) {
                        adj.get(c1).add(c2);
                        indegree.put(c2, indegree.getOrDefault(c2, 0) + 1);
                    }
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (char c : indegree.keySet())
            if (indegree.get(c) == 0)
                q.add(c);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            if (adj.containsKey(c)) {
                for (char nb : adj.get(c)) {
                    indegree.put(nb, indegree.get(nb) - 1);
                    if (indegree.get(nb) == 0)
                        q.add(nb);
                }
            }
        }
        return sb.length() == indegree.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
        System.out.println(alienOrder(words));
    }
}