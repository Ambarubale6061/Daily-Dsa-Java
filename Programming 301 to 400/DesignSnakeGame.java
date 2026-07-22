import java.util.*;

public class DesignSnakeGame {
    int width, height, score;
    int[][] food;
    int foodIdx;
    Deque<int[]> snake;
    Set<String> body;

    public DesignSnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        score = 0;
        foodIdx = 0;
        snake = new LinkedList<>();
        snake.add(new int[] { 0, 0 });
        body = new HashSet<>();
        body.add("0,0");
    }

    public int move(String direction) {
        int[] head = snake.peekFirst();
        int r = head[0], c = head[1];
        switch (direction) {
            case "U":
                r--;
                break;
            case "D":
                r++;
                break;
            case "L":
                c--;
                break;
            case "R":
                c++;
                break;
        }
        if (r < 0 || r >= height || c < 0 || c >= width)
            return -1;
        boolean eatFood = foodIdx < food.length && r == food[foodIdx][0] && c == food[foodIdx][1];
        if (eatFood) {
            score++;
            foodIdx++;
        } else {
            int[] tail = snake.pollLast();
            body.remove(tail[0] + "," + tail[1]);
        }
        String key = r + "," + c;
        if (body.contains(key))
            return -1;
        snake.addFirst(new int[] { r, c });
        body.add(key);
        return score;
    }
}