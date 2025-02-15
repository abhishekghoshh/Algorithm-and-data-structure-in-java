package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/number-of-bit-changes-to-make-two-integers-equal/description/
 * Solution link :
 *
 */
public class NumberOfBitChangesToMakeTwoIntegersEqual {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int n = 13, k = 4;
        int ans = minChanges(n, k);
        System.out.println(ans);
    }

    // Choose any bit in the binary representation of n that is equal to 1 and change it to 0.
    // Return the number of changes needed to make n equal to k. If it is impossible, return -1.
    // So we just need where n has 1 bit and k has 0 bit, but if n has 0 but k has 1, then
    // we cannot turn n to k, so we will return -1
    public static int minChanges(int n, int k) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 0 && (k & 1) == 1) return -1;
            if ((n & 1) == 1 && (k & 1) == 0) count++;
            n = n >> 1;
            k = k >> 1;
        }
        if (k > 0) return -1;
        return count;
    }
}
