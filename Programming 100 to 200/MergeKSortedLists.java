import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists)
            if (node != null)
                heap.add(node);
        ListNode dummy = new ListNode(0), curr = dummy;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            curr.next = min;
            curr = curr.next;
            if (min.next != null)
                heap.add(min.next);
        }
        return dummy.next;
    }
}