/*
 * Reachable vertex.
 * DAG: Design a linear-time algorithm to determine whether a DAG has a vertex that 
 * is reachable from every other vertex, and if so, find one.
 * Digraph: Design a linear-time algorithm to determine whether a digraph has a 
 * vertex that is reachable from every other vertex, and if so, find one.
 */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.TarjanSCC;
import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;

public class ReachableVertex {
    private boolean[] marked;
    

    public int findVertexInDAG(Digraph g) {
        // assert g in DAG
        Digraph gr = g.reverse();
        for(int v = 0; v < gr.V(); v++) {
            if(g.outdegree(v) == 0) {
                int count = 1;
                marked = new boolean[gr.V()];
                marked[v] = true;
                Queue<Integer> visited = new Queue<>();
                visited.enqueue(v);

                while (!visited.isEmpty()) {
                    int w = visited.dequeue();
                    for(int i: gr.adj(w)) {
                        if(!marked[i]) {
                            marked[i] = true;
                            count++;
                            visited.enqueue(i);
                        }
                    }
                }
                if(count == g.V()) return v;
                break;
            }
        }
        return -1;
    }

    private int[] id;
    private int count;

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w: g.adj(v)) {
            if(!marked[w]) dfs(g, w);
        }
    }

    public int findV(Digraph g) {
        TarjanSCC scc = new TarjanSCC(g);
        int c = scc.count();

        Digraph kDAG = new Digraph(c);

        DepthFirstOrder dfs = new DepthFirstOrder(g.reverse());
        marked = new boolean[g.V()];
        id = new int[g.V()];

        for(int v: dfs.reversePost()) {
            if(!marked[v]) {
                dfs(g, v);
                count++;
            }
        }

        for(int v: dfs.reversePost()) {
            for(int w: g.adj(v)) {
                if(id[w] != id[v]) kDAG.addEdge(id[v], id[w]);
            }
        }

        return findVertexInDAG(kDAG);
    }
}
