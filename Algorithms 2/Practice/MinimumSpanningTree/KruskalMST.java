import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph g) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        UF uf = new UF(g.V());

        while (!pq.isEmpty() && mst.size() < g.V()-1) {
            Edge e = pq.delMin();                   // get minimum weight edge on pq
            int v = e.either(), w = e.other(v);     // and its vertices
            if(uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double totalWeight = 0.0;
        for (Edge e : mst) {
            totalWeight += e.weight();
        }
        return totalWeight;
    }
}
