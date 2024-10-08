import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<DirectedEdge>[] adj; // adjacency list

    // Constructor to create a graph with V vertices
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    // Constructor to create a graph from input stream (e.g. file)
    public EdgeWeightedDigraph(In in) {
        this(in.readInt()); // Initialize the graph with V vertices from input
        int E = in.readInt(); // Read number of edges
        for (int i = 0; i < E; i++) {
            int v = in.readInt(); // Read from vertex
            int w = in.readInt(); // Read to vertex
            double weight = in.readDouble(); // Read weight of the edge
            addEdge(new DirectedEdge(v, w, weight)); // Add directed edge
        }
    }

    // Returns number of vertices
    public int V() {
        return V;
    }

    // Returns number of edges
    public int E() {
        return E;
    }

    // Adds a directed edge to the graph
    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    // Returns the directed edges adjacent to vertex v
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // Returns all directed edges in the graph
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }
}
