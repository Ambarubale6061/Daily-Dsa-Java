public class DesignCircularQueue {
    int[] q;
    int front, rear, size, capacity;

    public DesignCircularQueue(int k) {
        q = new int[k];
        capacity = k;
        front = size = 0;
        rear = -1;
    }

    public boolean enQueue(int value) {
        if (isFull())
            return false;
        rear = (rear + 1) % capacity;
        q[rear] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty())
            return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : q[front];
    }

    public int Rear() {
        return isEmpty() ? -1 : q[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}