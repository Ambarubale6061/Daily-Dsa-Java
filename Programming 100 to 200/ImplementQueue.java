import java.util.Stack;

public class ImplementQueue {
    Stack<Integer> in = new Stack<>(), out = new Stack<>();

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());
        return out.pop();
    }

    public int peek() {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());
        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    public static void main(String[] args) {
        ImplementQueue q = new ImplementQueue();
        q.push(1);
        q.push(2);
        System.out.println(q.peek()); // 1
    }
}