import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;

public class Topological {
    private Iterable<Integer> order;    // topological order

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if(!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order == null;
    }

    public static void main(String[] args) {
        String fileName = args[0];
        String separator = args[1];
        SymbolDigraph sg = new SymbolDigraph(fileName, separator);

        Topological top = new Topological(sg.digraph());

        for(int v: top.order()) {
            StdOut.println(sg.nameOf(v));
        }
    }
}
