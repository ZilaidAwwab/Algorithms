import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class TopM {
    public static void main(String[] args) {
        // print the top M lines in the input stream
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
        while (StdIn.hasNextLine()) {
            // create an entry from the next line and put on PQ
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) pq.delMin(); // remove minimum if M+1 enteries on the PQ
        } // top M enteries on the PQ

        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t: stack) StdOut.println(t);
    }
}
