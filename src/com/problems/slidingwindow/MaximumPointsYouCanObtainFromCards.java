package com.problems.slidingwindow;
/*
 *
 * problem links :
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards
 * https://www.codingninjas.com/studio/problems/maximum-points-from-cards_8391016
 *
 * Solution link :
 *
 * */

// Tags: Array, Dynamic programming, Sliding window
public class MaximumPointsYouCanObtainFromCards {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // TODO study later
    private static void type4() {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        int n = cardPoints.length;
        int sp = 0;
        int ep = n - k;
        int sum = 0;
        for (int i = ep; i < n; i++) {
            sum += cardPoints[i];
        }
        int ans = sum;
        while (ep < n) {
            sum += cardPoints[sp] - cardPoints[ep];
            sp++;
            ep++;
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }

    // dynamic programming
    // time complexity O(2k)
    // space complexity O(k)
    private static void type3() {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        int n = cardPoints.length;
        int prefix = 0;
        int[] suffix = new int[k + 1];
        for (int i = 1; i <= k; i++)
            suffix[i] = suffix[i - 1] + cardPoints[n - i];
        int max = suffix[k];
        for (int i = 1; i <= k; i++) {
            prefix += cardPoints[i - 1];
            max = Math.max(max, prefix + suffix[k - i]);
        }
        System.out.println(max);
    }

    // dynamic programming
    // time complexity O(2k)
    // space complexity O(2k)
    private static void type2() {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        int n = cardPoints.length;
        int max = 0;
        int[] prefix = new int[k + 1], suffix = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            prefix[i] = prefix[i - 1] + cardPoints[i - 1];
            suffix[i] = suffix[i - 1] + cardPoints[n - i];
        }
        for (int i = 0; i <= k; i++)
            max = Math.max(max, prefix[i] + suffix[k - i]);
        System.out.println(max);
    }
    // Brute force approach
    private static void type1() {
    }
}
