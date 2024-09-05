public class SequentialSearchST<Key, Value> {
    private Node first; // first node in the linked list

    private class Node {
        // linked list node
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        // Search for key, return associated value
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val; // search hit
            }
        }
        return null; // search miss
    }

    public void put(Key key, Value val) {
        // Search for key, Update value if found; grow table if new
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // if the key is already present then we set its value to the value that is recently passed
                x.val = val; // search hit
                return; // update val
            }
        }
        first = new Node(key, val, first); // search miss, add new node
    }

    /* More methods */
    // Method to remove a key from the symbol table
    public void delete(Key key) {
        first = delete(first, key);
    }

    // Helper method to delete a node from the linked list
    private Node delete(Node x, Key key) {
        if (x == null) return null; // Base case: end of list
        if (key.equals(x.key)) return x.next; // Key found: skip it
        x.next = delete(x.next, key); // Recursive call
        return x;
    }

    // Method to check if the symbol table contains a key
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // Method to check if the symbol table is empty
    public boolean isEmpty() {
        return first == null;
    }

    // Method to get the size of the symbol table
    public int size() {
        int count = 0;
        for (Node x = first; x != null; x = x.next) {
            count++;
        }
        return count;
    }
}
