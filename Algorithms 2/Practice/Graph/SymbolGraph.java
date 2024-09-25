import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolGraph {
    private ST<String, Integer> st;     // String -> index
    private String[] keys;              // index -> String
    private Graph G;                    // the graph

    public SymbolGraph(String stream, String sp) {
        st = new ST<String, Integer>();
        In in = new In(stream);         // first pass
        while (in.hasNextLine()) {      // build the index
            String[] a = in.readLine().split(sp);   // by reading strings
            for(int i = 0; i < a.length; i++) {     // to associate each
                if(!st.contains(a[i])) {            // distinct string
                    st.put(a[i], st.size());        // with an index
                }
            }
        }
        keys = new String[st.size()];       // inverted index
        for(String name: st.keys()) {       // to get string key
            keys[st.get(name)] = name;      // is an array
        }

        G = new Graph(st.size());
        in = new In(stream);            // second pass
        while (in.hasNextLine()) {      // build the graph
            String[] a = in.readLine().split(sp);       // by converting the
            int v = st.get(a[0]);                       // first vertex
            for(int i = 1; i < a.length; i++) {         // on each line
                G.addEdge(v, st.get(a[i]));             // to all the others
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }
}

/*
 * This Graph client allows clients to define graphs with String vertex names 
 * instead of integer indices. It maintains instance variables st (a symbol table 
 * that maps names to indices), keys (an array that maps indices to names), and G (a 
 * graph, with integer vertex names). To build these data structures, it makes two 
 * passes through the graph definition (each line has a string and a list of 
 * adjacent strings, separated by the delimiter sp).
 */
