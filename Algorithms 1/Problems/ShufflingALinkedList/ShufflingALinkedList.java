/*
Question
Shuffling a linked list.
Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case. 
*/

import edu.princeton.cs.algs4.StdRandom;

public class ShufflingALinkedList {
  private class Node {
    Object item;
    Node next;
  }

  private void merge(Node lh, Node rh) {
    Node left = lh;
    Node right = rh;

    if (StdRandom.uniformInt(1) > 0) {
      lh = right;
      right = right.next;
    } else {
      left = left.next;
    }

    Node runner = lh;

    while (right != null || left != null) {
      if (left == null) {
        runner.next = right;
        right = right.next;
      } else if (right == null) {
        runner.next = left;
        left = left.next;
      } else if (StdRandom.uniformInt(1) > 0) {
        runner.next = right;
        right = right.next;
      } else {
        runner.next = left;
        left = left.next;
      }
      runner = runner.next;
    }
  }

  public void shuffle(Node head, int N) {
    if (N == 1) return;

    int k = 1;
    Node mid = head;
    while (k < N / 2) {
      mid = mid.next;
      k++;
    }
    Node rh = mid.next;
    mid.next = null;
    shuffle(head, N / 2);
    shuffle(rh, N - N / 2);
    merge(head, rh);
  }
}
