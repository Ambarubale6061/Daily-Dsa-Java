class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class InsertAtEnd {
    public static ListNode insertAtEnd(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        if (head == null)
            return newNode;
        ListNode curr = head;
        while (curr.next != null)
            curr = curr.next;
        curr.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head = insertAtEnd(head, 2);
        System.out.println(head.next.val); // 2
    }
}