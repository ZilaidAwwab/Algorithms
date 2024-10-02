public class Edge implements Comparable<Edge> {
    private final int v;    // one vertex
    private final int w;    // other vertex
    private final double weight;    // edge weight

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if(vertex == v) return w;
        else if(vertex == w) return v;
        else throw new RuntimeException("Inconsistent Edge");
    }

    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
    
    @Override
    public int compareTo(Edge that) {
        if(this.weight() < that.weight()) return -1;
        else if(this.weight() < that.weight()) return 1;
        else return 0;
    }
}

/*
 * This data type provides the methods either() and other() so that such clients can 
 * use other(v) to find the other vertex when it knows v. When neither vertex is 
 * known, our clients use the idiomatic code int v = e.either(), w = e.other(v); to 
 * access an Edge e’s two vertices.
 */
