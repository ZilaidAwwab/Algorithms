import java.util.HashMap;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class WordNet {
    private HashMap<String, Bag<Integer>> map;
    private SAP sap;
    private int len;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if(synsets == null || hypernyms == null) {
            throw new java.lang.IllegalArgumentException();
        }

        parseSynsets(synsets);
        makeSAP(hypernyms);
    }

    // Helper methods
    // put all synsets into the arrayList
    private void parseSynsets(String synsets) {
        In in = new In(synsets);
        map = new HashMap<String, Bag<Integer>>();

        while (in.hasNextLine()) {
            String[] fields = in.readLine().split(",");
            int id = Integer.parseInt(fields[0]);
            String[] nouns = fields[1].split(" ");

            for(int i = 0; i < nouns.length; i++) {
                Bag<Integer> bag;
                if(map.containsKey(nouns[i])) bag = map.get(nouns[i]);
                else bag = new Bag<Integer>();
                bag.add(id);
                map.put(nouns[i].trim(), bag);
            }
            len = id + 1;
        }
    }

    private void makeSAP(String hypernyms) {
        In in = new In(hypernyms);
        Digraph g = new Digraph(len);

        while (in.hasNextLine()) {
            String[] connections = in.readLine().split(",");
            for(int i = 1; i < connections.length; i++) {
                g.addEdge(Integer.parseInt(connections[0]), Integer.parseInt(connections[i]));
            }
        }
        sap = new SAP(g);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return map.keySet();
    }

    // is the word of a wordnet noun?
    public boolean isNoun(String word) {
        if(word == null) throw new java.lang.IllegalArgumentException();
        return map.containsKey(word);
    }

    // distance between nounA and nounB
    public int distance(String nounA, String nounB) {
        if(!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException();
        return sap.length(map.get(nounA), map.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common 
    // ancestor of nounA and nounB in a shortest ancestral path
    public String sap(String nounA, String nounB) {
        if(!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException();

        int ancestor = sap.ancestor(map.get(nounA), map.get(nounB));

        StringBuilder synset = new StringBuilder();
        Iterator<String> keys = map.keySet().iterator();

        while (keys.hasNext()) {
            String next = keys.next();
            for(int val: map.get(next)) {
                if(val == ancestor) {
                    synset.append(next);
                    synset.append(" ");
                }
            }
        }
        return synset.toString();
    }

    public static void main(String[] args) {
        
    }
}
