package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/reverse-bits/description/
 * https://neetcode.io/problems/reverse-bits
 *
 * Solution link :
 * https://www.youtube.com/watch?v=UcoN6UjAI64
 * https://neetcode.io/solutions/reverse-bits
 */
public class ReverseBits {
    public static void main(String[] args) {
        type1();
    }


    // todo optimized approach
    //  this is very similar to the reverse of the number in decimal,
    //  but here rather divide by 10 we will divide by 2 and take the reminder from 2
    //  though it is similar to do (n % 2) and (n & 1) and (n / 2) and (n >>> 1)
    //  we will use >>> instead of >> as the number might be in negative so >> will not work
    private static void type1() {
        int n = 964176192;
        int ans = reverseBits(n);
        System.out.println(ans);
    }


    public static int reverseBits(int n) {
        int ans = 0;
        // as integer has 32 bits, so we will loop it 32 times
        for (int i = 0; i < 32; i++) {
            int digit = (n & 1);
            ans = ans * 2 + digit;
            n = n >>> 1; // shifting to the right by 1
        }
        return ans;
    }
}
