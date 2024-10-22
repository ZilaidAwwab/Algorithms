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
