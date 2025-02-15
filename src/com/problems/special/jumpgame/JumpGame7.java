package com.problems.special.jumpgame;

/*
 *
 * problem links :
 * https://leetcode.com/problems/jump-game-vii/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=bZxWLuiqHes&list=PLJtzaiEpVo2yaP5v5bq0-QJgU0lO3TrEi&index=7
 *
 * https://github.com/Algorithms-Made-Easy/Leetcode-Challenge/blob/main/1871.%20Jump%20Game%20VII
 * */
public class JumpGame7 {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String s = "011010000011110000";
        int minJump = 4;
        int maxJump = 7;
        boolean result = canReach1(s, minJump, maxJump);
        System.out.println(result);
    }

    // we are using the sliding window here
    // we will check in the window of [max-jump----min-jump]
    // we have a min jump, so we cannot directly jump from the
    public static boolean canReach1(String s, int minJump, int maxJump) {
        char[] ch = s.toCharArray();
        int n = ch.length;
        boolean[] dp = new boolean[n];
        // if any array index has 0 character and reachable from previous we will set it to 0
        // as '0' is the starting character, and it has no previous characters, so we will set the dp[0] as true
        dp[0] = true;
        int reachable = 0;
        for (int i = 1; i < n; i++) {
            // adding reachable zero to the window
            if (i - minJump >= 0)
                reachable += dp[i - minJump] ? 1 : 0;
            // removing reachable zero from the window
            // i-maxJump is the limit then we will remove i-maxJump - 1
            if (i - maxJump - 1 >= 0)
                reachable -= dp[i - maxJump - 1] ? 1 : 0;
            // so if reachable to still greater than 0 that means there is are some 0 atleast,
            // from where it can jump to the current index.
            // but still we need to have 0 on than the current index
            dp[i] = reachable > 0 && ch[i] == '0';
        }
        return dp[n - 1];
    }
}
