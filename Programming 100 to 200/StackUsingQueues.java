import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {
    Queue<Integer> q = new LinkedList<>();

    public void push(int x) {
        q.add(x);
        int s = q.size();
        for (int i = 0; i < s - 1; i++)
            q.add(q.remove());
    }

    public int pop() {
        return q.remove();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}