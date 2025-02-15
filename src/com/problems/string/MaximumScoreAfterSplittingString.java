package com.problems.string;

/*
 *
 * problem links :
 * https://leetcode.com/problems/maximum-score-after-splitting-a-string/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=mc_eSStDrWw
 *
 * https://neetcode.io/solutions/maximum-score-after-splitting-a-string
 * */
public class MaximumScoreAfterSplittingString {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // we will calculate the ones in one iteration
    private static void type2() {
        String s = "011101";
        int ans = maxScore2(s);
        System.out.println(ans);
    }

    public static int maxScore2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        // first we will calculate all the ones
        int ones = 0;
        for (char ch : arr) {
            if (ch == '1') ones++;
        }
        // now we will go through the array once again
        // we will take two variables leftZeros, rightOnes
        // we will initialize them with 0 and total count of 1s
        // so if the bit is 0 then we will increment leftZeros else we will decrement from the rightOnes,
        // so we don't need to calculate again
        int max = 0;
        int leftZeros = 0, rightOnes = ones;
        for (int i = 0; i < n - 1; i++) {
            int bit = arr[i] - '0';
            if (bit == 0) {
                leftZeros++;
            } else {
                rightOnes--;
            }
            max = Math.max(max, (leftZeros + rightOnes));
        }
        return max;
    }

    // brute force approach
    // we will use a pointer and calculate left 0s and right 1s individually
    private static void type1() {
        String s = "011101";
        int ans = maxScore1(s);
        System.out.println(ans);
    }

    public static int maxScore1(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int max = 0;
        for (int i = 1; i < n; i++) {
            int counter = 0;
            // checking the left side
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] == '0') counter++;
            }
            // checking the right side
            for (int j = i; j < n; j++) {
                if (arr[j] == '1') counter++;
            }
            // checking the max
            max = Math.max(max, counter);
        }
        return max;
    }
}
