package com.problems.special.jumpgame;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/jump-game-ii/
 * https://www.codingninjas.com/studio/problems/jump-game_893178
 * https://www.geeksforgeeks.org/problems/minimum-number-of-jumps-1587115620/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=BRnRPLNGWIo&list=PLJtzaiEpVo2yaP5v5bq0-QJgU0lO3TrEi&index=2
 *
 * https://github.com/Algorithms-Made-Easy/Leetcode-Challenge/blob/main/45.%20Jump%20Game%20II
 * */
public class JumpGame2 {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        type1();
        type2();
    }

    // this is better approach
    // time complexity O(n) and space complexity is O(1)
    // we are calculating the jump per window.
    // lets says we are on 0th index which has value 3,
    // so we can go to any index up to 3
    // so our first window is from [0,3]
    // we will check which is the max index that we can reach from this range
    // and once we reach to the end we will increase the jump
    // let's say from 1 we can go till 5 which is the max
    // so once we reach the end of the range [0,3]
    // we will again set our range to [3,5]
    // and max is 8 which is from 4, ideally the jump sequence is 0->1->4...
    private static void type2() {
        int[] nums = {2, 3, 1, 1, 4};
        int n = nums.length;
        int end = 0, max = 0;
        int jump = 0;
        for (int i = 0; i < n - 1; i++) {
            // we will check the max for all the max indices on that range
            max = Math.max(max, i + nums[i]);
            // we reach to the end we will increase the jump and update the end of the range
            if (i == end) {
                jump++;
                end = max;
            }
        }
        System.out.println(jump);
    }


    // using dynamic programming
    // time complexity O(n^2)
    private static void type1() {
        int[] nums = {2, 3, 1, 1, 4};
        int ans = jump1(nums);
        System.out.println(ans);
    }

    // todo it is different from the Algorithms Made Easy brute force solution
    // very simple and intuitive approach brute force approach
    // we start from 0, we will maintain a dp array which will indicate minimum jump required to fo to that index
    // for 0th cell it is 0, as we are standing right there
    // we will initialize all the remaining cells with infinity
    // so for each cell we will go to all the indices that we can go from the current index
    // and we will try to minimize jump required on all the indices
    public static int jump1(int[] nums) {
        int n = nums.length;
        // early return if n is 1 then we can directly return 1
        if (n == 1) return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            // required jump to go to the index i
            int jump = dp[i];
            // we will go to all the indices
            int boundary = Math.min(n - 1, i + nums[i]);
            for (int j = i + 1; j <= boundary; j++) {
                // now we will minimize the jump for all the index j
                dp[j] = Math.min(dp[j], jump + 1);
            }
        }
        return dp[n - 1];
    }

}
