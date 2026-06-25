import java.util.Stack;

public class QueueUsingStacks {
    // Same as ImplementQueue above, but explicit class name
    Stack<Integer> in = new Stack<>(), out = new Stack<>();

    public void enqueue(int x) {
        in.push(x);
    }

    public int dequeue() {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());
        return out.pop();
    }

    public int front() {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());
        return out.peek();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}