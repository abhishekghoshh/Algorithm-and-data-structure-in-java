package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/reverse-bits/description/
 *
 * Solution link :
 *
 */
public class ReverseBits {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int n = 964176192;
        int ans = reverseBits(n);
        System.out.println(ans);
    }

    // this is very similar to the reverse of the number in decimal
    // but here rather divide by 10 we will divide by 2 and take the reminder from 2
    // though it is similar to do (n % 2) and (n & 1)
    // and (n / 2) and (n >>> 1)
    public static int reverseBits(int n) {
        int rev = 0;
        // as integer has 32 bits, so we will loop it 32 times
        for (int i = 0; i < 32; i++) {
            int digit = n & 1;
            rev = rev * 2 + digit;
            n = n >>> 1;
        }
        return rev;
    }
}
