public class RecursiveBinaryTree {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length-1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid-1);
        else if (key > a[mid]) return rank(key, a, mid+1, hi);
        else return mid;
    }
}

/*
Rules for designing a recursion program
■ The recursion has a base case—we always include a conditional statement as the first statement in the program that has a return.

■ Recursive calls must address subproblems that are smaller in some sense, so that recursive calls converge to the base case. In the code below, the difference between the values of the fourth and the third arguments always decreases.

■ Recursive calls should not address subproblems that overlap. In the code below, the portions of the array referenced by the two subproblems are disjoint.
*/
