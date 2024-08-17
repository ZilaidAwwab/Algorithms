/*
Question
Dutch national flag.
Given an array of n buckets, each containing a red, white, or blue pebble,
sort them by color.

The allowed operations are: 
swap(i,j):  swap the pebble in bucket i with the pebble in bucket j. 
color(i): determine the color of the pebble in bucket i.

The performance requirements are as follows:
At most n calls to color().
At most n calls to swap().
Constant extra space.
*/

public class DutchNationalFlag {
  enum Pebble {
    Red,
    White,
    Blue
  }

  private Pebble[] pebbles;

  private Pebble color(int i) {
    return pebbles[i];
  }

  private int compare(Pebble p) {
    Pebble white = Pebble.White;
    return p.ordinal() - white.ordinal();
  }

  private void swap(int i, int j) {
    Pebble temp = pebbles[i];
    pebbles[i] = pebbles[j];
    pebbles[j] = temp;
  }

  public void sort() {
    assert pebbles.length > 0;
    int r = 0;
    int runner = 0;
    int b = pebbles.length - 1;

    while (runner <= b) {
      Pebble color = color(runner);
      int cmp = compare(color);
      
      if (cmp < 0) swap(runner++, r++);
      else if (cmp > 0) swap(runner, b--);
      else runner++;
    }
  }
}
