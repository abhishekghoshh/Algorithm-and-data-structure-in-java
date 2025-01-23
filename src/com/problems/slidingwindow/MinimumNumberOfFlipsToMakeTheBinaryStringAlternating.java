package com.problems.slidingwindow;
/*
 *
 * problem links :
 * https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=MOeuK6gaC2A
 *
 * https://neetcode.io/solutions/minimum-number-of-flips-to-make-the-binary-string-alternating
 * */


// Tags: Array, Sliding window
public class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {
    public static void main(String[] args) {
        type1();
        type2(); // todo check this one first this is the origin type3 and type4 are same as this
        type3();
        type4();
    }

    // todo exactly like the prev with minor changes
    private static void type4() {
        String s = "111000";
        int ans = minFlips4(s);
        System.out.println(ans);
    }

    private static int minFlips4(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int flips = 0;
        // first we will create the window for the first n size
        for (int i = 0; i < n; i++) {
            boolean isEven = (i % 2) == 0;
            char bit = arr[i];
            if ((isEven && bit == '1') || (!isEven && bit == '0'))
                flips++;
        }
        int min = Math.min(flips, n - flips);
        // if n is even then we do not need to do anything else
        if ((n % 2) == 0 || min == 0) return min;
        // at this point n is odd
        for (int i = 0; i < n; i++) {
            char bit = arr[i];
            boolean isEven = ((i + n) % 2) == 0;
            if ((isEven && bit == '1') || (!isEven && bit == '0'))
                flips++;
            else if ((!isEven && bit == '1') || (isEven && bit == '0')) {
                flips--;
            }
            // updating the min variable
            int currentWindowMin = Math.min(flips, n - flips);
            min = Math.min(min, currentWindowMin);
        }
        return min;
    }

    // similar intuition like the previous with more optimization
    // todo exactly like the previous but here we are assuming that the string is concatenated
    //  we will not attach the strings here we will do it by manipulating the indices
    //  if we see the 2nd sliding window loop it started from n and went till 2n
    //  left char is arr[i-n] and current char is arr[i]
    //  but both are similar characters actually, so we are actually calculating for same char
    //  and the main thing is if the indices are even or odd
    //  if n is even then if 'i' is even then i-n and i+n are even same for if 'i' is odd
    //  if n is odd then  if 'i' is even then i-n and i+n will be odd same for if 'i' is odd, it will toggle
    //  so if n is even we do not need to do anything else
    //  if n is odd then we need to compute in the 2nd loop
    private static void type3() {
        String s = "111000";
        int ans = minFlips3(s);
        System.out.println(ans);
    }

    private static int minFlips3(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int flips = 0;
        // first we will create the window for the first n size
        for (int i = 0; i < n; i++) {
            boolean isEven = (i % 2) == 0;
            char bit = arr[i];
            if ((isEven && bit == '1') || (!isEven && bit == '0'))
                flips++;
        }
        int min = Math.min(flips, n - flips);
        // if n is even then we do not need to do anything else
        if ((n / 2) == 0 || min == 0) return min;
        // now we will calculate for the remaining window
        for (int i = 0; i < n; i++) {
            // calculating for the current bit
            boolean isEven = ((i + n) % 2) == 0;
            char bit = arr[i];
            if ((isEven && bit == '1') || (!isEven && bit == '0'))
                flips++;

            // calculating for the left most bit
            boolean isLeftEven = (i % 2) == 0;
            char leftBit = arr[i];
            if ((isLeftEven && leftBit == '1') || (!isLeftEven && leftBit == '0'))
                flips--;
            // updating the min variable
            int currentWindowMin = Math.min(flips, n - flips);
            min = Math.min(min, currentWindowMin);
        }
        return min;
    }

    // todo optimized approach using the sliding window, discuss it in the interview

    // there is a trick for the first type which is
    // if we append the same string in the string,
    // and then we will create 'n' sliding window it will make the string
    // as if some characters of string added in the last
    // if the string is abcde then ss => abcdeabcde
    // if we take a n size window then it will be ab-cdeab-cde
    // the inner cdeab is same as removing then appending 2 characters at the end of the string

    // let's say we are assuming even index for 1 and odd index for 0,
    // so we will keep a counter for required flips
    // if in even index there is no 1 then we will increment the counter
    // similarly for the 0's if that is not on odd index,
    // but we could also assume that even index for 0 and odd index for 1
    // that also makes the string alternating
    // we do not need to create a separate counter for that as the value will be (n-counter)
    // minimum flips required will be min(counter, n-counter)
    private static void type2() {
        String s = "111000";
        int ans = minFlips2(s);
        System.out.println(ans);
    }


    public static int minFlips2(String s) {
        s = s + s;
        char[] arr = s.toCharArray();
        int n2 = arr.length;
        int n1 = n2 / 2;
        int flips = 0;
        // first we will create the window for the first n size
        for (int i = 0; i < n1; i++) {
            boolean isEven = (i % 2) == 0;
            char bit = arr[i];
            if ((isEven && bit == '1') || (!isEven && bit == '0'))
                flips++;
        }
        int min = Math.min(flips, n1 - flips);
        if (min == 0) return 0;
        for (int i = n1; i < n2; i++) {
            // calculating for the current bit
            boolean isEven = (i % 2) == 0;
            char bit = arr[i];
            if ((isEven && bit == '1') || (!isEven && bit == '0'))
                flips++;
            // calculating for the left most bit
            boolean isLeftEven = ((i - n1) % 2) == 0;
            char leftBit = arr[i - n1];
            if ((isLeftEven && leftBit == '1') || (!isLeftEven && leftBit == '0'))
                flips--;
            // updating the min variable
            int currentWindowMin = Math.min(flips, n1 - flips);
            min = Math.min(min, currentWindowMin);
        }
        return min;
    }

    // brute force approach
    private static void type1() {
    }
}
