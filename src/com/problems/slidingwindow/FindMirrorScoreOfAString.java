package com.problems.slidingwindow;

import java.util.Arrays;
import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/find-mirror-score-of-a-string/description/
 *
 * Solution link:
 *
 * */
public class FindMirrorScoreOfAString {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // let's compact the marker array
    // we can have a 26 letter marker array for every character
    // and there we will save the index so then in O(1) time we will get the index of the mirror character
    // but here is a catch if let's say the string is --aa--zz--
    // for the first z it will get 'a' but for the second z there will be no 'a' but we can clearly see there is 'a'
    // so rather just storing the indices we will store a stack, and we will push the indices into the stack for that character
    // so for every character we will get the closest mirror character from the top of the stack
    private static void type2() {
        String s = "aczzx";
        long ans = calculateScore2(s);
        System.out.println(ans);
    }

    public static long calculateScore2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        Stack<Integer>[] marker = new Stack[26];
        for (int i = 0; i < 26; i++) marker[i] = new Stack<>();
        long score = 0;
        for (int i = 0; i < n; i++) {
            int pos = arr[i] - 'a';
            int mirrorPos = 25 - pos;
            // there are no mirror character, so we will push the current index
            if (marker[mirrorPos].isEmpty()) {
                marker[pos].push(i);
            } else {
                // else we will get the mirror character index from the stack
                int j = marker[mirrorPos].pop();
                score += (i - j);
            }
        }
        return score;
    }

    // brute force approach
    // time complexity O(n^2) and space complexity O(n)
    // we will use a marker array of length
    // for every index character we will try to find mirror character
    // if we find it then we unmark the position by giving value -1
    // else we will mark the current pos
    private static void type1() {
        String s = "aczzx";
        long ans = calculateScore1(s);
        System.out.println(ans);
    }

    public static long calculateScore1(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        long score = 0;
        int[] marker = new int[n];
        Arrays.fill(marker, -1);
        for (int i = 0; i < n; i++) {
            int pos = arr[i] - 'a';
            int mirrorPos = 25 - pos;
            int j = i - 1;
            while (j >= 0 && marker[j] != mirrorPos) j--;
            if (j == -1) {
                // marking the current position
                marker[i] = pos;
            } else {
                score += (i - j);
                // unmarking the prev position
                marker[j] = -1;
            }
        }
        return score;
    }
}
