import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    final private WordNet wordNet;

    // constructor takes a wordnet object
    public Outcast(WordNet wordNet) {
        if(wordNet == null) throw new java.lang.IllegalArgumentException();
        
        this.wordNet = wordNet;
    }
    
    // given an array of wordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        if(nouns == null) throw new java.lang.IllegalArgumentException();

        int max = -1;
        String outcast = "";

        for(String nounA: nouns) {
            int sum = 0;
            for(String nounB: nouns) {
                sum += wordNet.distance(nounA, nounB);
            }
            if(sum > max) {
                max = sum;
                outcast = nounA;
            }
        }
        return outcast;
    }

    public static void main(String[] args)  {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++)  {
        In in = new In(args[t]);
        String[] nouns = in.readAllStrings();
        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
