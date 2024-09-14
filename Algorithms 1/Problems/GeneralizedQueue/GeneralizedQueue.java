/*
Generalized queue. Design a generalized queue data type that supports all of 
the following operations in logarithmic time (or better) in the worst case.

Create an empty data structure.

Append an item to the end of the queue.

Remove an item from the front of the queue.

Return the ith item in the queue.

Remove the ith item from the queue.
*/

import edu.princeton.cs.algs4.RedBlackBST;

public class GeneralizedQueue<Item> {
    private int index;
    private RedBlackBST<Integer, Item> store;

    GeneralizedQueue() {
        index = 0;
        store = new RedBlackBST<Integer, Item>();
    }

    public void append(Item item) {
        store.put(index++, item);
    }

    public void removeFront() {
        store.deleteMin();
    }

    public Item get(int i) {
        int key = store.rank(i);
        return store.get(key);
    }

    public void delete(int i) {
        store.delete(store.rank(i));
    }
}
