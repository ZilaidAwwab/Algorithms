import java.util.Iterator;

public class LinkedListForQueue<Item> implements Iterable<Item> {
  private Node first; // link to least recently added node
  private Node last; // link to most recently added node
  private int N; // number of items on the queue

  private class Node {
    // nested class to define nodes
    Item item;
    Node next;
  }

  public boolean isEmpty() {
    return first == null; // or N == 0
  }

  public int size() {
    return N;
  }

  public void enqueue(Item item) {
    // add item to the end of the list
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;

    if (isEmpty()) first = last;
    else oldLast.next = last;
    N++;
  }

  public Item dequeue() {
    // remove an item from the beginning of the list
    Item item = first.item;
    first = first.next;
    if (isEmpty()) last = null;
    N--;
    return item;
  }

  // See the iterator and client implementation from the previous queue programs

  // this is just to avoid the error
  @Override
  public Iterator<Item> iterator() {
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }  
}
