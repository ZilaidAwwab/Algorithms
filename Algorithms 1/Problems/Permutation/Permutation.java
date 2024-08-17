/*
Question
Permutation
Given two integer arrays of size n, design a subquadratic algorithm to determine
whether one is a permutation of the other. That is, do they contain exactly the
same entries but, possibly, in a different order.
*/

import edu.princeton.cs.algs4.Shell;

public class Permutation {
  private Integer[] a;
  private Integer[] b;

  public Permutation(Integer[] a, Integer[] b) {
    this.a = a;
    this.b = b;
  }

  public boolean isPerm(Integer[] a, Integer[] b) {
    if (a.length != b.length) return false;

    Shell.sort(a);
    Shell.sort(b);

    for (int i = 0; i < a.length; i++) {
      if (a[i] == b[i]) return true;
    }
    return true;
  }
}
