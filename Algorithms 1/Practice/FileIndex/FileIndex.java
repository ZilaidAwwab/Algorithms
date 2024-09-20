import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FileIndex {
    public static void main(String[] args) {
        ST<String, SET<File>> st = new ST<>();
        for (String filename: args) {
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()) {
                String word = in.readString();

                if (!st.contains(word)) st.put(word, new SET<File>());
                SET<File> set = st.get(word);
                set.add(file);
            }
        }
        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) {
                for (File file: st.get(query)) StdOut.println(" " + file.getName());
            }
        }
    }
}

/*
 * This symbol-table client indexes a set of files. We search for each word in each 
 * file in a symbol table, maintaining a SET of file names that contain the word. 
 * Names for In can also refer to web pages, so this code can also be used to build 
 * an inverted index of web pages.
 */
