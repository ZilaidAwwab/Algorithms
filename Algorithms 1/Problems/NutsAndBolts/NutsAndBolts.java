/*
Question
Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n bolts. 
The goal is to find the corresponding pairs of nuts and bolts. Each nut fits exactly
one bolt and each bolt fits exactly one nut. By fitting a nut and a bolt together, 
the carpenter can see which one is bigger (but the carpenter cannot compare two nuts 
or two bolts directly). Design an algorithm for the problem that uses at most 
proportional to n logn compares (probabilistically).
*/

public class NutsAndBolts {
    class Nut {
        private int size;
        public int compare(Bolt bolt) {
            if (bolt.size > this.size) return -1;
            else if (bolt.size < this.size) return 1;
            else return 0;
        }
    }

    class Bolt {
        private int size;
    }

    public void pair(Bolt[] bolts, Nut[] nuts) {
        int n = nuts.length;
        assert bolts.length == n;
        Nut[] auxN = new Nut[n];
        Bolt[] auxB = new Bolt[n]; // need tree map to implement

        for (int i = 0; i < n; i++) {
            // using floor and ceil api in treemap
            int lo = floor(auxB, nuts[i]);
            int hi = ceil(auxB, nuts[i]);
            int index = partition(bolts, nuts[i], lo, hi);
            auxB[index] = bolts[index];
            auxN[index] = nuts[i];
        }

        for (int i = 0; i < n; i++) {
            nuts[i] = auxN[i];
        }
    }

    private int partition(Bolt[] bolt, Nut nut, int lo, int hi) {
        int l = lo;
        int r = hi;
        while (true) {
            while (nut.compare(bolt[++l]) > 0) if (l == hi) break;
            while (nut.compare(bolt[--r]) < 0) if (r == lo) break;
            if (l >= r) break;
            exch(bolt, l, r);
        }
        return l;
    }

    private void exch(Bolt[] bolt, int l, int r) {
        Bolt tmp = bolt[l];
        bolt[l] = bolt[r];
        bolt[r] = tmp;
    }

    private int floor(Bolt[] b, Nut nut) {
        return 0;
    }

    private int ceil(Bolt[] b, Nut nut) {
        return 0;
    }
}
