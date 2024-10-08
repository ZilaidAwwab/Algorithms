/*
Question
4-SUM
Given an array a[] of n integers, the 4-SUM problem is to determine if there exist 
distinct indices i, j, k, and l such that a[i]+a[j]=a[k]+a[l]. Design an algorithm 
for the 4-SUM problem that takes time proportional to n^2 (under suitable technical 
assumptions).
 */

import java.util.HashSet;

public class FourSum {
    public static boolean fourSum(int[] a) {
        int n = a.length;
        assert n >= 4;
        HashSet<Integer> arr = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int val = a[i] + a[j];
                if (arr.contains(val)) return true;
                else arr.add(val);
            } 
        }
        return false;
    }
}
