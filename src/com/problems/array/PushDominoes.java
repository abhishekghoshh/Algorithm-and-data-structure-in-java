package com.problems.array;

/*
 * Problem link :
 * https://leetcode.com/problems/push-dominoes/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=evUFsOb_iLY
 *
 * https://neetcode.io/solutions/push-dominoes
 */

// Tags: Array, Two Pointers
public class PushDominoes {
    public static void main(String[] args) {
        type1();
    }

    // this is optimized
    private static void type1() {
        String dominoes = ".L.R...LR..L..";
        String ans = pushDominoes1(dominoes);
        System.out.println(ans);
    }

    public static String pushDominoes1(String dominoes) {
        char[] arr = dominoes.toCharArray();
        int n = arr.length;
        int i = 0, j = n - 1;
        int lastL = -1;
        // shrinking the left size, if there is any L in the left then all the left side domino's will fall in the left freely
        while (i <= j && arr[i] != 'R') {
            if (arr[i] == 'L') lastL = i;
            i++;
        }
        if (lastL != -1) {
            for (int k = 0; k <= lastL; k++) arr[k] = 'L';
        }
        // shrinking the right size, if there is any R in the right then all the right side domino's will fall in the right freely
        int lastR = -1;
        while (i <= j && arr[j] != 'L') {
            if (arr[j] == 'R') lastR = j;
            j--;
        }
        if (lastR != -1) {
            for (int k = lastR; k < n; k++) arr[k] = 'R';
        }
        // now we have a combination of R and L, while R will force in the right and L will force in the left
        if (i < j) {
            // now we will work on only R to L range
            while (i <= j) {
                // we will to set all the domino's to R in between R start and End
                int rStart = i, rEnd = i;
                while (i < j && arr[i] != 'L') {
                    if (arr[i] == 'R') rEnd = i;
                    i++;
                }
                for (int k = rStart + 1; k < rEnd; k++) arr[k] = 'R';
                // the [. . . .] between R and L
                int start = rEnd + 1;
                int end = i - 1;
                while (start < end) {
                    arr[start++] = 'R';
                    arr[end--] = 'L';
                }
                // // we will to set all the domino's to L in between L start and End
                int lStart = i, lEnd = i;
                while (i <= j && arr[i] != 'R') {
                    if (arr[i] == 'L') lEnd = i;
                    i++;
                }
                for (int k = lStart + 1; k < lEnd; k++) arr[k] = 'L';
            }
        }
        return new String(arr);
    }
}
