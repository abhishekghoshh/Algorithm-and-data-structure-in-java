package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/burst-balloons/description/
 * https://neetcode.io/problems/burst-balloons
 * https://www.naukri.com/code360/problems/mining-diamonds_4244494
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Yz4LlDSlkns&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=52
 * https://www.youtube.com/watch?v=VFskby7lUbw
 *
 * https://takeuforward.org/data-structure/burst-balloons-partition-dp-dp-51/
 * https://neetcode.io/solutions/burst-balloons
 */
public class BustBalloons {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // todo this very hard to have this intuition
    //  explain recursive approaches
    //  if you know how to convert a recursive recurrence relation to iterative approach then only you'll be able to get this
    // we can translate the recursion in to memoization
    // If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
    // If i - 1 or i + 1 goes out of bounds of the array,
    // then treat it as if there is a balloon with a 1 painted on it.
    // For this reason will add 1 and 1 on both sides
    private static void type3() {
        int[] nums = {3, 1, 5, 8};
        int ans = maxCoins3(nums);
        System.out.println(ans);
    }

    private static int maxCoins3(int[] nums) {
        int n = nums.length;
        int N = n + 2;
        int[] copy = new int[N];
        // modifying the array
        copy[0] = copy[n + 1] = 1;
        System.arraycopy(nums, 0, copy, 1, n);
        nums = copy;
        // we will use dp for 1 tp n, no need to store -1 for default value
        // we will use n+2 as here we need k+1 and k can go n
        int[][] dp = new int[N][N];

        // here we have just translated the recursion into iteration and copied the recurrence relation
        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                // same from the recursion
                int max = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    int cost = nums[i - 1] * nums[k] * nums[j + 1]
                            + dp[i][k - 1]
                            + dp[k + 1][j];
                    max = Math.max(max, cost);
                }
                dp[i][j] = max;
            }
        }
        return dp[1][n];
    }

    // recursion with memoization
    // todo if you understand the recurrence relation from the previous type
    //  then it is just a cakewalk
    private static void type2() {
        int[] nums = {3, 1, 5, 8};
        int ans = maxCoins2(nums);
        System.out.println(ans);
    }

    private static int maxCoins2(int[] nums) {
        // If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
        // If i - 1 or i + 1 goes out of bounds of the array,
        // then treat it as if there is a balloon with a 1 painted on it.
        // For this reason will add 1 and 1 on both sides
        int n = nums.length;
        int[] copy = new int[n + 2];
        // modifying the array
        copy[0] = copy[n + 1] = 1;
        System.arraycopy(nums, 0, copy, 1, n);
        // we will use dp for 1 tp n, no need to store -1 for default value
        int[][] dp = new int[n + 1][n + 1];
        // we will choose balloon from the 1 to nth index
        return maxCoins2(1, n, copy, dp);
    }

    static int maxCoins2(int i, int j, int[] nums, int[][] dp) {
        // i > j means there is no balloon in between
        if (i > j) return 0;
        // if the recursion is calculated, then we will directly return the ans
        if (dp[i][j] != 0) return dp[i][j];
        int max = Integer.MIN_VALUE;
        // we will choose every balloon for the range i to j
        // and assume that it is the last balloon to be burst.
        // if it is last, then the cost will be i-1 * k * j+1.
        // we will now call the function with i to k-1 and k+1 to j
        for (int k = i; k <= j; k++) {
            int cost = nums[i - 1] * nums[k] * nums[j + 1]
                    + maxCoins2(i, k - 1, nums, dp)
                    + maxCoins2(k + 1, j, nums, dp);
            max = Math.max(max, cost);
        }
        return dp[i][j] = max;
    }

    // using the recursion
    // todo the intuition is not straight forward
    // what is meant by dynamic programming
    // it is finding the the answer of independent sub problems and storing it
    // which will be used later to find the answer of the bigger sub problem
    // but for this problem if we try to burst a balloon from the middle,
    // then for some balloons on the left side the cost will be depending on some index on the right side
    // same for right side the cost will depend on the left side index
    // so we will change our thought process, we will pick a balloon and assume that this balloon will be the last balloon to burst
    // so for left side the right boundary will be the current balloon and for the right side the left boundary will be current balloon
    private static void type1() {
        int[] nums = {3, 1, 5, 8};
        int ans = maxCoins1(nums);
        System.out.println(ans);
    }

    private static int maxCoins1(int[] nums) {
        // If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
        // If i - 1 or i + 1 goes out of bounds of the array,
        // then treat it as if there is a balloon with a 1 painted on it.
        // For this reason will add 1 and 1 on both sides
        int n = nums.length;
        int[] copy = new int[n + 2];
        // modifying the array
        copy[0] = copy[n + 1] = 1;
        System.arraycopy(nums, 0, copy, 1, n);
        // we will choose balloon from the 1 to nth index
        return maxCoins1(1, n, copy);
    }

    static int maxCoins1(int i, int j, int[] nums) {
        // i > j means there is no balloon in between
        if (i > j) return 0;
        int max = Integer.MIN_VALUE;
        // we will choose every balloon for the range i to j and assume that it is the last balloon to be burst.
        // the series was earlier like 1,2,3...i-1,i..k,...j,j+1
        // after the deletion the series will be like 1,2,3...i-1,k,j+1 as kth item is the last in [i,j]
        // so the cost will be i-1 * k * j+1, and now we will now call the function with i to k-1 and k+1 to j
        for (int k = i; k <= j; k++) {
            int cost = nums[i - 1] * nums[k] * nums[j + 1]
                    + maxCoins1(i, k - 1, nums)
                    + maxCoins1(k + 1, j, nums);
            max = Math.max(max, cost);
        }
        return max;
    }
}
