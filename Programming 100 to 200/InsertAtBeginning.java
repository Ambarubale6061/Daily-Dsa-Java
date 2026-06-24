class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class InsertAtBeginning {
    public static ListNode insertAtBeginning(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        return newNode;
    }

    // test
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head = insertAtBeginning(head, 1);
        System.out.println(head.val); // 1
    }
}