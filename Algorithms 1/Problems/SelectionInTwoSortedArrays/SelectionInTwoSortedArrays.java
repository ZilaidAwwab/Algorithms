/*
Question
Selection in two sorted arrays. Given two sorted arrays a[] and b[], of lengths n1
and n2 and an integer 0 ≤ k < n1 +n2, design an algorithm to find a key of rank 
k. The order of growth of the worst case running time of your algorithm should be 
log n, where n = n1 + n2
Version 1: n1 = n2 (equal length arrays) and k = n/2 (median).

Version 2: k = n/2 (median).

Version 3: no restrictions.
*/

public class SelectionInTwoSortedArrays {
    int MAX = Integer.MAX_VALUE;
    int MIN = Integer.MIN_VALUE;

    public int select(int[] a, int ah, int[] b, int bh, int k) {
        int n1 = a.length - ah;
        int n2 = b.length - bh;

        int i = ah + (int)(double)(n1/(n1+n2)*(k-1));
        int j = bh + k - i - 1;
        
        int ai = i == n1 ? MAX : a[i];
        int bj = j == n2 ? MAX : b[j];
        
        int ai1 = i == 0 ? MIN : a[i-1];
        int bj1 = j == 0 ? MIN : b[j-1];

        if (ai > bj1 && ai < bj) return ai;
        else if (bj > ai1 && bj < ai) return bj;
        else if (ai < bj1) return select(a, i+1, b, bh, k-i-1);
        else return select(a, ah, b, j+1, k-j-1);
    }
}
