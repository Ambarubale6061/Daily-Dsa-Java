public class Rotate_List {

}

class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
      return head;
    }

    // Find length and last node
    int length = 1;
    ListNode tail = head;
    while (tail.next != null) {
      tail = tail.next;
      length++;
    }

    k %= length;
    if (k == 0) {
      return head;
    }

    // Make the list circular
    tail.next = head;

    // Find new tail
    int steps = length - k;
    ListNode newTail = head;
    for (int i = 1; i < steps; i++) {
      newTail = newTail.next;
    }

    // Break the circle
    ListNode newHead = newTail.next;
    newTail.next = null;

    return newHead;
  }
}