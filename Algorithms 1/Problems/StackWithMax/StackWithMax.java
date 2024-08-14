/*
Question
Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.
*/

import java.util.Stack;
import java.util.TreeSet;

public class StackWithMax<Item> extends Stack<Item> {
  private TreeSet<Item> tree = new TreeSet<Item>();

  public Item push(Item item) {
    super.push(item);
    tree.add(item);
    return item;
  }

  public Item pop() {
    Item item = super.pop();
    tree.remove(item);
    return item;
  }

  public Item max() {
    return tree.last();
  }
}
