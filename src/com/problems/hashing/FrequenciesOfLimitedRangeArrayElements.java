package com.problems.hashing;

import com.util.PrintUtl;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/frequency-of-array-elements-1587115620/0
 *
 * Solution link :
 *
 *
 * https://takeuforward.org/data-structure/count-frequency-of-each-element-in-the-array/
 */
public class FrequenciesOfLimitedRangeArrayElements {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we will use a hashmap here
    private static void type2() {
        int[] arr = {2, 3, 2, 3, 5};
        int P = 5;
        int N = arr.length;
        frequencyCount(arr, N, P);
        PrintUtl.print(arr);
    }

    public static void frequencyCount(int[] arr, int N, int P) {
        // P+1 size freq array is enough to
        int[] freq = new int[P + 1];
        for (int num : arr) freq[num]++;
        for (int i = 0; i < N; i++) {
            arr[i] = (i + 1 <= P) ? freq[i + 1] : 0;
        }
    }

    // we will use the brute force approach here
    private static void type1() {
    }
}
