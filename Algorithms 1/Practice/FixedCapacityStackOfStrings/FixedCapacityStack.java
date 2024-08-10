public class FixedCapacityStack<Item> {
  private Item[] a;
  private int N;

  public FixedCapacityStack(int cap) {
    // we can safely ignore this warning
    a = (Item[]) new Object[cap];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  // resize function
  public void resize(int max) {
    Item[] temp = (Item[]) new Object[max];
    for (int i = 0; i < N; i++) {
      temp[i] = a[i];
    }
    a = temp;
  }
  
  public void push(Item item) {
    // add item to the top of stack

    // here we are resizing the array, if the array if filled
    if (N == a.length) resize(2 * a.length);
    a[N++] = item;
  } 

  public Item pop() {
    // remove item from top of the stack
    Item item = a[--N];
    a[N] = null; // avoid loitering

    // here we are checking, that if the array is quartely filled then we half the array size so to save the space
    if (N > 0 && N == a.length/4) resize(a.length / 2);
    return item;
  }

  // With these resizing implementations in the push and pop, the stack never overflows, nor it become less than quarterly full (unless it is empty)

  // Here we can implement the same main class as used in the previous file
}


/*
Loitering. Java’s garbage collection policy is to reclaim the memory associated with 
any objects that can no longer be accessed. In our pop() implementations, the reference to the popped item remains in the array. The item is effectively an orphan—it will be never be accessed again—but the Java garbage collector has no way to know this until it is overwritten. Even when the client is done with the item, the reference in the array may keep it alive. This condition (holding a reference to an item that is no longer needed) is known as loitering. In this case, loitering is easy to avoid, by setting the array entry corresponding to the popped item to null, thus overwriting the unused reference and making it possible for the system to reclaim the memory associated with the popped item when the client is finished with it.
*/
