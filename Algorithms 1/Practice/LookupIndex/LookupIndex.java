import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LookupIndex {
    public static void main(String[] args) {
        In in = new In(args[0]);    // index database
        String sp = args[1];        // separator

        ST<String, Queue<String>> st = new ST<>();
        ST<String, Queue<String>> ts = new ST<>();

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            String key = a[0];
            for (int i = 0; i < a.length; i++) {
                String val = a[i];
                if (!st.contains(key)) st.put(key, new Queue<String>());
                if (!ts.contains(val)) st.put(val, new Queue<String>());
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readLine();
            if (st.contains(query)) {
                for (String s: st.get(query)) StdOut.println(" " + s);
            }
            if (ts.contains(query)) {
                for (String s: ts.get(query)) StdOut.println(" " + s);
            }
        }
    }
}

/*
 * This data-driven symbol-table client reads key-value pairs from a file, then 
 * prints the values corresponding to the keys found on standard input. Keys are 
 * strings; values are lists of strings. The separating delimiter is taken as a 
 * command line argument.
 */
