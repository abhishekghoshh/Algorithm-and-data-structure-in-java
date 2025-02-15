package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 * https://neetcode.io/problems/longest-increasing-subsequence
 * https://www.naukri.com/code360/problems/longest-increasing-subsequence_630459
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ekcwMsSIzVc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=42
 * https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
 *
 * https://www.youtube.com/watch?v=on2hvxBXJH4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=44
 * https://takeuforward.org/data-structure/longest-increasing-subsequence-binary-search-dp-43/
 *
 * https://www.youtube.com/watch?v=cjWnW0hdF1Y
 * https://neetcode.io/solutions/longest-increasing-subsequence
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        // intuition is a little bit different, it uses 1D array to store all the list
        type4();
        type5();
        // if the n = 10^5 then for previous approaches, the time taken will be O(n^10)
        // that's why this is the best approach
        type6();
    }

    // todo best approach but don't start with this in the interview
    //  start with type1 or type4 maybe
    // We will not store lengths in the indexes,
    // rather we will store the nums in a lisLen array
    // let's think about the brute force solution.
    // We will have to find all the increasing subsequence and find the longest among them.
    // However, that will take time and space, so if we can store all the sequences in the same array.
    // Now if the new element is greater than the last element, then we will push the element in the lisLen array,
    //
    // else will try to find where it can be placed. We will always try to minimize the array elements
    // let's say the array of lengths is like [-INF, 2, 4, 6, 10]
    // so 1 len subsequence ends with 2, 2 length 4, 3 length 6 and 4 length 10
    // now the current num is 7, so there is a num 6 in previous with the length 3.
    // if we can add 7 then it will make 4 length subsequences,
    // but we already have it that is 10, now our question is should we change it?
    // yes, we have to because in later stage if we find 8 or 9 then it will also compete for length 4,
    // but it should be considered as length 5.
    // so if the new element is lesser than the previous same length element, then only we will update
    // we can find the place either linearly or with the binary search approach
    private static void type6() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int len = lengthOfLIS6(nums);
        System.out.println(len);
    }

    private static int lengthOfLIS6(int[] nums) {
        int n = nums.length;
        // max length of LIS can be n
        int[] lisLen = new int[n + 1];
        // for length 0 there will be nothing, so we have initialized it to -INF
        lisLen[0] = Integer.MIN_VALUE;
        int len = 0;
        for (int num : nums) {
            // if the current num is greater than the last number on LIS, then we will add the num to len(LIS) + 1
            if (lisLen[len] < num) {
                lisLen[++len] = num;
            } else {
                int i = len;
                // we could either use linear approach or binary search approach here
                while (lisLen[i] >= num) i--;
                // i+1 will be the suitable place for num, but we need to check if it is smaller or not
                if (num < lisLen[i + 1]) lisLen[i + 1] = num;
            }
        }
        return len;
    }

    // similar to the previous one with some optimization
    // we will also use a prevMax index variable just to check if the current num is
    // greater than nums[prevMax], that means dp[prevMax] had the max dp value
    // and nums[prevMax] < num, so we could directly assign dp[i] = dp[prevMax] + 1
    // and prevMax = i
    // todo this can be a optimization from the previous approach
    private static void type5() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int max = lengthOfLIS5(nums);
        System.out.println(max);
    }

    private static int lengthOfLIS5(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int prevMax = 0;
        for (int i = 1; i < n; i++) {
            // rather, checking all the indices if we can directly
            // check the current index is greater than the maxI, then we can directly increment from maxI
            if (nums[prevMax] < nums[i]) {
                dp[i] = dp[prevMax] + 1;
                prevMax = i;
            } else {
                // from i-1 to 0 we will check if the num is greater than prev or not,
                // and max len from all the lesser prev value will be our ans
                int len = 0;
                for (int prev = i - 1; prev >= 0; prev--) {
                    if (nums[prev] < nums[i])
                        len = Math.max(len, dp[prev]);
                }
                // len+1 will be the current value of i
                // if there is no lesser previous value then the default len value will be 0
                dp[i] = len + 1;
                if (dp[prevMax] < dp[i]) prevMax = i;
            }

        }
        return dp[prevMax];
    }

    // todo tell this in the interview
    // this is also tabulation,
    // but the intuition is different
    // dp[i] signifies longest increasing subsequence ends at index i
    // we will use 1D array and store dp value for every index
    // starting from index-1 to 0
    // this is helpfully for printing the longest sequence
    private static void type4() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int max = lengthOfLIS4(nums);
        System.out.println(max);
    }

    private static int lengthOfLIS4(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < n; i++) {
            int maxLen = 0;
            // from i-1 to 0 we will check if the num is greater than prev or not
            // and max len from all the lesser prev value will be our ans
            for (int prev = i - 1; prev >= 0; prev--) {
                if (nums[prev] < nums[i])
                    maxLen = Math.max(maxLen, dp[prev]);
            }
            // len+1 will be the current value of i
            // if there is no lesser previous value then the default len value will be 0
            dp[i] = maxLen + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // top-down approach
    // tabulation
    // TODO check this approach once again
    private static void type3() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int ans = lengthOfLIS3(nums);
        System.out.println(ans);
    }

    private static int lengthOfLIS3(int[] nums) {
        int n = nums.length;
        // n+1 as prev can be -1, so we have to do the coordinate shifting
        // just like the recursive approach, for iterative also the 2nd parameter will be in +1 state
        int[][] dp = new int[n + 1][n + 1];

        // following the recurrence relation from the recursive approach,
        // i will be in reverse approach
        for (int i = n - 1; i >= 0; i--) {
            // from the intuition at least we can understand the prev will be starting from i-1
            // otherwise it would make no sense
            for (int prev = i - 1; prev >= -1; prev--) {
                int taking = 0;
                if (prev == -1 || nums[prev] < nums[i]) {
                    // we will do i+1 and i+1
                    // +1 for the 2nd i
                    taking = 1 + dp[i + 1][i + 1];
                }
                // prev+1 for shifting
                int notTaking = dp[i + 1][prev + 1];
                // same for dp, the answer will be the max
                dp[i][prev + 1] = Math.max(taking, notTaking);
            }
        }
        return dp[0][0];
    }

    // todo nice optimization from previous, can be demonstrated in the interview
    // using memoization technique with recursion
    // bottom-up approach.
    // it is not that efficient as we are using nXn and n can be pretty higher
    private static void type2() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int ans = lengthOfLIS2(nums);
        System.out.println(ans);
    }

    private static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        // n+1 as prev can be -1, so we have to do the coordinate shifting
        // the second parameter will always be +1 state
        int[][] dp = new int[n][n + 1];
        return lengthOfLIS2(0, -1, n, nums, dp);
    }

    public static int lengthOfLIS2(int i, int prev, int n, int[] nums, int[][] dp) {
        // it means i is out of boundary
        if (i >= n) return 0;
        // using prev+1 for dp value
        if (dp[i][prev + 1] != 0) return dp[i][prev + 1];
        // here again we have 2 options, either to consider the current index or not
        int taking = 0;
        // but we can only consider the current element when it is greater the previous element
        // or the previous is -1, (-1 means we have not started the sequence yet)
        if (prev == -1 || nums[i] > nums[prev]) {
            taking = 1 + lengthOfLIS2(i + 1, i, n, nums, dp);
        }
        // here we are not taking the current item
        int notTaking = lengthOfLIS2(i + 1, prev, n, nums, dp);
        // returning the max out of them
        return dp[i][prev + 1] = Math.max(taking, notTaking);
    }

    // using brute approach with recursion and recurrence relation
    private static void type1() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int ans = lengthOfLIS1(nums);
        System.out.println(ans);
    }

    private static int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        return lengthOfLIS1(0, -1, n, nums);
    }

    public static int lengthOfLIS1(int i, int prev, int n, int[] nums) {
        // it means index is out of boundary
        if (i >= n) return 0;
        // here again we have 2 options, either to consider the current index or not
        int taking = 0;
        // but we can only consider the current element when it is greater the previous element
        // or the previous is -1, (-1 means we have not started the sequence yet)
        if (prev == -1 || nums[prev] < nums[i]) {
            taking = 1 + lengthOfLIS1(i + 1, i, n, nums);
        }
        // here we are not taking the current item
        int notTaking = lengthOfLIS1(i + 1, prev, n, nums);
        // returning the max out of them
        return Math.max(taking, notTaking);
    }

}
