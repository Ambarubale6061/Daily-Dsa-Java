import java.util.*;

public class DesignLeaderboard {
    Map<Integer, Integer> scores;

    public DesignLeaderboard() {
        scores = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        scores.put(playerId, scores.getOrDefault(playerId, 0) + score);
    }

    public int top(int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int score : scores.values()) {
            pq.add(score);
            if (pq.size() > K)
                pq.poll();
        }
        int sum = 0;
        while (!pq.isEmpty())
            sum += pq.poll();
        return sum;
    }

    public void reset(int playerId) {
        scores.put(playerId, 0);
    }
}