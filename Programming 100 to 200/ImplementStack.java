import java.util.LinkedList;
import java.util.Queue;

public class ImplementStack {
    Queue<Integer> q = new LinkedList<>();

    public void push(int x) {
        q.add(x);
        int size = q.size();
        for (int i = 0; i < size - 1; i++)
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

    public static void main(String[] args) {
        ImplementStack stack = new ImplementStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top()); // 2
        System.out.println(stack.pop()); // 2
        System.out.println(stack.top()); // 1
    }
}