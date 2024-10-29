# Open-Source Data Structures and Algorithms Repository: From Beginner to Advanced

This open-source repository is dedicated to learning data structures and algorithms, with a focus on practical, code-based examples. Starting from fundamental concepts and progressing to advanced techniques, this collection of notes covers essential data structures, algorithmic paradigms, time and space complexities, and problem-solving strategies.

The repository is designed to help developers of all levels, from beginners to experienced coders, build a strong foundation in DSA. Each concept is accompanied by clear code examples to reinforce learning. Contributions are welcome to help expand this growing resource.

## Compiling and running with only a JDK

### Create a classes folder

```
cd Algorithms
mkdir classes
```

### Compile the algorithm

```
javac -sourcepath src/main/java -d classes src/main/java/ <relative-path-to-java-source-file>
```

### Run the algorithm

```
java -cp classes <class-fully-qualified-name>
```

### Example

```
$ javac -d classes -sourcepath src/main/java src/main/java/com/zilaidawwab/algorithms/datastructures/dynamicarray/IntArray.java
$ java -cp classes com.zilaidawwab.algorithms.datastructures.dynamicarray.IntArray
```

## Data Structures

### Dynamic Array

- [Dynamic Array](src/main/java/com/zilaidawwab/algorithms/datastructures/dynamicarray/IntArray.java)

### Linked List

- [Singly Linked List](src/main/java/com/zilaidawwab/algorithms/datastructures/linkedlist/SinglyLinkedList.java)
- [Doubly Linked List](src/main/java/com/zilaidawwab/algorithms/datastructures/linkedlist/DoublyLinkedList.java)
- [Circular Linked List](src/main/java/com/zilaidawwab/algorithms/datastructures/linkedlist/CircularLinkedList.java)

### Stack

- [Stack Interface for Implementing Stack Data Strucutre](src/main/java/com/zilaidawwab/algorithms/datastructures/stack/Stack.java)
- [Stack made with Linked List](src/main/java/com/zilaidawwab/algorithms/datastructures/stack/ListStack.java)
- [Stack made with Dynamic Resizeable Array](src/main/java/com/zilaidawwab/algorithms/datastructures/stack/ArrayStack.java)
- [Stack for Integers](src/main/java/com/zilaidawwab/algorithms/datastructures/stack/IntStack.java)

### Queue

- [Queue](src/main/java/com/zilaidawwab/algorithms/datastructures/queue/Queue.java)

### Priority Queue (Heaps)

- [Binary Heap](src/main/java/com/zilaidawwab/algorithms/datastructures/priorityqueue/BinaryHeap.java)
- [Binary Heap Quick Removals (Using HashTables)](src/main/java/com/zilaidawwab/algorithms/datastructures/priorityqueue/BinaryHeapQuickRemovals.java)
- [Min D Heap](src/main/java/com/zilaidawwab/algorithms/datastructures/priorityqueue/MinDHeap.java)
- [Min Indexed Binary Heap](src/main/java/com/zilaidawwab/algorithms/datastructures/priorityqueue/MinIndexedBinaryHeap.java)
- [Min Indexed D Heap](src/main/java/com/zilaidawwab/algorithms/datastructures/priorityqueue/MinIndexedDHeap.java)

### Union Find

- [Union Find](src/main/java/com/zilaidawwab/algorithms/datastructures/unionfind/UnionFind.java)

### Binary Search Tree

- [Binary Search Tree](src/main/java/com/zilaidawwab/algorithms/datastructures/binarysearchtree/BinarySearchTree.java)
- [Tree Traversal Order](src/main/java/com/zilaidawwab/algorithms/datastructures/binarysearchtree/TreeTraversalOrder.java)
