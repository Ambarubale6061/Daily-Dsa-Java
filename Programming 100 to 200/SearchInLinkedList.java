class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class SearchInLinkedList {
    public static boolean search(ListNode head, int target) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == target)
                return true;
            curr = curr.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        System.out.println(search(head, 2)); // true
    }
}