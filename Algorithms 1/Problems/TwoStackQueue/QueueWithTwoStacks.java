/*
Question
Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
*/

import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks<Item> {
  private Stack<Item> inputStack;
  private Stack<Item> outputStack;
  private int size;

  public QueueWithTwoStacks() {
    inputStack = new Stack<>();
    outputStack = new Stack<>();
  }

  public void enqueue(Item item) {
    inputStack.push(item);
    size++;
  }

  public Item dequeue() {
    // Here we are taking out the items from the input stack and filling them into output stack, if the output stack is empty
    if (outputStack.isEmpty()) {
      while (!inputStack.isEmpty()) {
        outputStack.push(inputStack.pop());
      }
    }

    Item temp = null;
    // Now if the output stack is not empty then we are taking the item out from it and saving that into the temp variable and then we return the temp
    if (!outputStack.isEmpty()) {
      temp = outputStack.pop();
      size--;
    }
    return temp;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }
}
