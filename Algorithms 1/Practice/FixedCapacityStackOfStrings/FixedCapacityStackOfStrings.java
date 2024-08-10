import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings {
  private String[] a;
  private int N;

  public FixedCapacityStackOfStrings(int cap) {
    a = new String[cap];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  public void push(String item) {
    a[++N] = item;
  }

  public String pop() {
    return a[--N];
  }

  public static void main(String[] args) {
    FixedCapacityStackOfStrings s;
    s = new FixedCapacityStackOfStrings(100);

    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-")) s.push(item);
      else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
    }

    StdOut.println("(" + s.size() + " left on stack)");
  }
}

/*
This class is limited to the String data type, but in real use case we can have any data type to deal with, so that's why we will implement a more general data type in the next file (FixedCapacityStack) so to handle any data type
*/
