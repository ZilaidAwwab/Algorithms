import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class DoublingTest {
  public static double timeTrial(int N) {
    // Time ThreeSum.count() for N random 6 digits int
    int MAX = 1000000;
    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
      a[i] = StdRandom.uniformInt(-MAX, MAX);
    }
    Stopwatch timer = new Stopwatch();
    int count = ThreeSum.count(a);
    StdOut.print(count);
    return timer.elapsedTime();
  }

  public static void main(String[] args) {
    // print table of running times
    for (int N = 250; true; N += N) {
      // print time for problem size N
      double time = timeTrial(N);
      StdOut.printf("%7d %5.1f\n", N, time);
    }
  }
}
