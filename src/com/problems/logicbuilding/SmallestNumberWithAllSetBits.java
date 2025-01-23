package com.problems.logicbuilding;

/*
 * Problem link :
 * https://leetcode.com/contest/weekly-contest-426/problems/smallest-number-with-all-set-bits/description/
 *
 * Solution link :
 *
 */
public class SmallestNumberWithAllSetBits {
    public static void main(String[] args) {
        type1();
    }

    // brute force also the simplest solution
    private static void type1() {
        int ans = smallestNumber1(10);
    }

    // if the bits size is b then 2^b-1 has the most set bit
    // we can directly check in the if-clause and return accordingly
    public static int smallestNumber1(int n) {
        if (n <= 1) return 1;
        if (n <= 3) return 3;
        if (n <= 7) return 7;
        if (n <= 15) return 15;
        if (n <= 31) return 31;
        if (n <= 63) return 63;
        if (n <= 127) return 127;
        if (n <= 255) return 255;
        if (n <= 511) return 511;
        if (n <= 1023) return 1023;
        return -1;
    }
}
