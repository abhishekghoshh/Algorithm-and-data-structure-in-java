package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem link :
 *
 * Solution link :
 * https://www.youtube.com/watch?v=hXqRLILcC1k
 *
 * https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
 */
public class BoyerMoore {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String text = "test, this is a test";
        String pattern = "test";
        List<Integer> answer = match(text, pattern);
        System.out.println(answer);
    }

    private static List<Integer> match(String text, String pattern) {
        List<Integer> answer = new ArrayList<>();
        char[] arr = text.toCharArray();
        char[] ptrn = pattern.toCharArray();

        int m = ptrn.length;
        int n = arr.length;

        final int NO_OF_CHARS = 256;
        int[] badChar = new int[NO_OF_CHARS];
        //shift table algorithm
        Arrays.fill(badChar, -1);
        for (int i = 0; i < m; i++) badChar[arr[i]] = i;

        int s = 0, j;
        while (s <= (n - m)) {
            j = m - 1;
            // Keep reducing index j of the pattern while characters of pattern
            // and text are matching at this shift s
            while (j >= 0 && ptrn[j] == arr[s + j]) j--;
            if (j < 0) {
                // we have found one possible answer
                answer.add(s);
                // now we will go to the next window
                // Shift the pattern so that the next character in text
                // aligned with the last occurrence of it in the pattern.The condition s+m < n is necessary for the case
                // when the pattern occurs at the end of text
                s += (s + m < n) ? m - badChar[arr[s + m]] : 1;
            } else {
                // Shift the pattern so that the bad character in text aligns with the last occurrence of it in pattern.
                // The max function is used to make sure that we get a positive shift.
                // We may get a negative shift
                // if the last occurrence of bad character in pattern is on the right side of the current character.
                s += Math.max(1, j - badChar[arr[s + j]]);
            }
        }
        return answer;
    }
}
