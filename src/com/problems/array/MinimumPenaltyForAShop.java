package com.problems.array;

/*
 * Problem link:
 * https://leetcode.com/problems/minimum-penalty-for-a-shop/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=0d7ShRoOFVE
 *
 * https://neetcode.io/solutions/minimum-penalty-for-a-shop
 */
public class MinimumPenaltyForAShop {
    // todo there are two rules that we need to follow
    //   For every hour when the shop is open and no customers come, the penalty increases by 1.
    //   For every hour when the shop is closed and customers come, the penalty increases by 1
    public static void main(String[] args) {
        type1();
    }

    // optimized approach
    // first we will count Y
    // if we know Y then the value of the N will be simply n - Y.
    // but we will calculate the N along the way when we traverse the array
    private static void type1() {
        String customers = "YYNY";
        int ans = bestClosingTime1(customers);
        System.out.println(ans);
    }

    // this is easier to explain
    public static int bestClosingTime1(String customers) {
        char[] arr = customers.toCharArray();
        int n = arr.length;
        int Y = 0, N = 0;
        for (char ch : arr) {
            if (ch == 'Y') Y++;
        }
        // if there is a customer on every day, then we need to close it on the last day
        if (Y == n) return n;
        // if there is no customer on any day, then we will close the first day
        if (Y == 0) return 0;
        // now will traverse the array and calculate the index with the minimum penalty.
        // let's say we close on ith day,
        // then the penalty would be total N on the left side of i and total Y on the right side of 'i'.
        // so we will take a variable N which will calculate the prefix sum of N, and along the way we will decrease the Y
        int idx = 0, min = Y;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'Y') Y--;
            else N++;
            // total penalty
            int penalty = N + Y;
            // we will close after that day
            if (penalty < min) {
                min = penalty;
                idx = i + 1;
            }
        }
        return idx;
    }


}
