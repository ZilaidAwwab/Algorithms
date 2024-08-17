/*
Question
Intersection of two sets.
Given two arrays a[] and b[], each containing N distinct 2D points in the plane,
design a subquadratic algorithm to count the number of points that are contained
both in array a[] and array b[].
*/

import edu.princeton.cs.algs4.Shell;

public class IntersectionOfTwoSets implements Comparable<IntersectionOfTwoSets> {
  private int x;
  private int y;
  
  public IntersectionOfTwoSets(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(IntersectionOfTwoSets that) {
    if (that.x > this.x) return -1;
    if (that.x < this.x) return 1;
    if (that.y > this.y) return -1;
    if (that.y < this.y) return 1;
    return 0;
  }

  public int countIntersection(IntersectionOfTwoSets[] a, IntersectionOfTwoSets[] b) {
    Shell.sort(a);
    Shell.sort(b);

    int i = 0;
    int j = 0;
    int count = 0;

    while (i < a.length && j < b.length) {
      if (a[i].compareTo(b[j]) == 0) {
        count++;
        i++;
        j++;
      } else if (a[i].compareTo(b[j]) < 0) i++;
      else j++;
    }
    return count;
  }
}
