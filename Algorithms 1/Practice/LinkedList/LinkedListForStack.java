import java.util.Iterator;

public class LinkedListForStack<Item> implements Iterable<Item> {
  private Node first; // top of stack, most recently added node
  private int N; // number of items

  private class Node {
    // nested class to define nodes
    Item item;
    Node next;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public int size() {
    return N;
  }

  public void push(Item item) {
    // add item to top of stack
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    N++;
  }

  public Item pop() {
    // remove item from the top of the stack
    Item item = first.item;
    first = first.next;
    N--;
    return item;
  }

  // refer to the previous programs from the readme list for iterator and client implementation

  // this is just added in order to get rid of the error, real implementation can be referenced from other programs
  @Override
  public Iterator<Item> iterator() {
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }

}
