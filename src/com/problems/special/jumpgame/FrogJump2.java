package com.problems.special.jumpgame;

import java.util.HashSet;
import java.util.Set;

/*
 *
 * problem links :
 * https://leetcode.com/problems/frog-jump-ii/
 *
 * Solution link :
 *
 * */
public class FrogJump2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // Greedy Solution
    // time complexity is O(n)
    // first let us think about the question and point out the given statements
    // You are given a 0-indexed integer array stones sorted in strictly increasing order representing the positions of stones in a river
    // Initially on the first stone, wants to travel to the last stone and then return to the first stone. However, it can jump to any stone at most once.
    // The length of a jump is the absolute difference between the position of the stone the frog is currently on and the position of the stone to which the frog jumps.
    // More formally, if the frog is at stones[i] and is jumping to stones[j], the length of the jump is |stones[i] - stones[j]|
    // The cost of a path is the maximum length of a jump among all jumps in the path.
    // Return the minimum cost of a path for the frog.
    // So before finding all the paths and find and try to minimize them if we can find what can be the best path
    // as the stones are in increasing order, the cost will be increasing if we skip any stone
    // so, we need to use stones as close as possible, so we can jump on the next consecutive stones, the cost will be minimum then
    // but there is another criteria that if we use one stone on first time then we can not use the same while returning back to the initial stone
    // so, if we use the consecutive stone then we can not use the same on return, so we can at most skip one stone, so that the frog can land on the
    // skipped stone on the return, so if we choose this path then cost will be minimized
    // now our work is to find the max jump out of all these jumps while skipping one stone
    private static void type2() {
        int[] stones = {0, 2, 5, 6, 7};
        int ans = maxJump2(stones);
        System.out.println(ans);
    }

    public static int maxJump2(int[] stones) {
        int n = stones.length;
        if (n == 2) return stones[1] - stones[0];
        int max = 0;
        // finding the distances of 2 stones, skipping 1 stone
        for (int i = 2; i < n; i++)
            max = Math.max(max, stones[i] - stones[i - 2]);
        return max;
    }

    // todo check the solution once again
    // time complexity is  O(nlog(high))
    // binary search on answer approach
    private static void type1() {
        int[] stones = {0, 2, 5, 6, 7};
        int n = stones.length;
        // the minimum jump possible is 1 and the maximum jump possible is (highest stone height - lowest stone height)
        int low = 1, high = stones[stones.length - 1] - stones[0];
        int ans = 0;
        // now we will minimize the jump
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (canJump(mid, stones, n)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        System.out.println(ans);
    }

    public static boolean canJump(int jump, int[] stones, int n) {
        Set<Integer> st = new HashSet<>();
        int height = stones[0];
        st.add(stones[0]);
        for (int i = 1; i < n; i++) {
            if (stones[i] - height > jump) {
                if (st.contains(stones[i - 1])) return false;
                st.add(stones[i - 1]);
                height = stones[i - 1];
            }
        }
        st.remove(stones[0]);
        height = stones[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (!st.contains(stones[i])) {
                if (height - stones[i] > jump) return false;
                height = stones[i];
            }
        }
        return true;
    }
}
