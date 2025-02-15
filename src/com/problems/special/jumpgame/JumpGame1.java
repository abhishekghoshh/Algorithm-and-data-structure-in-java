package com.problems.special.jumpgame;

/*
 *
 * problem links :
 * https://leetcode.com/problems/jump-game/
 * https://www.codingninjas.com/studio/problems/jump-game_3164697
 *
 * Solution link :
 * https://www.youtube.com/watch?v=C6AZyfj-Kyw&list=PLJtzaiEpVo2yaP5v5bq0-QJgU0lO3TrEi&index=1
 *
 *
 * */
public class JumpGame1 {

    public static void main(String[] args) {
        type1();
    }


    // simple array problem
    // we have to check if we can ultimately go to the last cell or not,
    // ideally if every cell have at least 1 then we will be able to reach the last cell
    // the problem will occur only if there is a series of 1 and there is suddenly a 0, but we can not code only for that
    // let us generalize the solution, we will take one variable max.
    // and everytime we will calculate that from the current index if how far we can go
    // if that exceeds the max, then we will update the max
    // and at the last we will check if the max is bigger or equal to the last cell or not
    private static void type1() {
        int[] nums = {2, 3, 1, 1, 4};
        boolean ans = canJump1(nums);
        System.out.println(ans);
    }

    public static boolean canJump1(int[] nums) {
        int n = nums.length;
        // if n equal to 1 that means we are already on the last cell
        if (n == 1) return true;
        int max = 0;
        for (int i = 0; i < n; i++) {
            // everytime we will check how much we can go from the current index
            max = Math.max(max, i + nums[i]);
            // if the current index is the max index possible which mean, max could not be updated more
            // we cannot go beyond the current index
            if (i == max) break;
        }
        // at the last we will check if the max is bigger or equal to the last cell or not
        return max >= n - 1;
    }
}
