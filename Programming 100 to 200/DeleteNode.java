class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class DeleteNode {
    // Delete node by value (first occurrence)
    public static ListNode deleteNode(ListNode head, int target) {
        if (head == null)
            return null;
        if (head.val == target)
            return head.next;
        ListNode curr = head;
        while (curr.next != null && curr.next.val != target)
            curr = curr.next;
        if (curr.next != null)
            curr.next = curr.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head = deleteNode(head, 1);
        System.out.println(head.val); // 2
    }
}