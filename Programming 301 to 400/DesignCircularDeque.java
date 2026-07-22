public class DesignCircularDeque {
    int[] dq;
    int front, rear, size, capacity;

    public DesignCircularDeque(int k) {
        dq = new int[k];
        capacity = k;
        front = size = 0;
        rear = -1;
    }

    public boolean insertFront(int value) {
        if (isFull())
            return false;
        if (--front < 0)
            front = capacity - 1;
        dq[front] = value;
        size++;
        if (size == 1)
            rear = front;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull())
            return false;
        rear = (rear + 1) % capacity;
        dq[rear] = value;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty())
            return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty())
            return false;
        if (--rear < 0)
            rear = capacity - 1;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : dq[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : dq[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}