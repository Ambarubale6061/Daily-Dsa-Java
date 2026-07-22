import java.util.*;

public class DesignTwitter {
    class Tweet {
        int id, time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = timestamp++;
        }
    }

    Map<Integer, Set<Integer>> followers;
    Map<Integer, Tweet> tweets;
    int timestamp;

    public DesignTwitter() {
        followers = new HashMap<>();
        tweets = new HashMap<>();
        timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {
        Tweet t = new Tweet(tweetId);
        t.next = tweets.get(userId);
        tweets.put(userId, t);
        followers.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);
        for (int followee : followers.getOrDefault(userId, new HashSet<>())) {
            Tweet t = tweets.get(followee);
            if (t != null)
                pq.add(t);
        }
        List<Integer> feed = new ArrayList<>();
        while (!pq.isEmpty() && feed.size() < 10) {
            Tweet t = pq.poll();
            feed.add(t.id);
            if (t.next != null)
                pq.add(t.next);
        }
        return feed;
    }

    public void follow(int followerId, int followeeId) {
        followers.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followers.containsKey(followerId))
            followers.get(followerId).remove(followeeId);
    }
}