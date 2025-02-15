package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=R3T0olAhUq0
 * https://neetcode.io/solutions/bitwise-and-of-numbers-range
 *
 * https://www.youtube.com/watch?v=gLZZyvXTRLk
 */
public class BitwiseANDOfNumbersRange {

    // todo this problem is quite tricky to understand at first
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // todo most optimized approach but this is not very intuitive, see type2 for better intuition
    //  lets say the range is 1010000 -> 1011101
    //  if we divide the numbers like 101+0000 and 101+1101
    //  the common prefix is 101 so the result will be 1010000
    //  because we know that the last 4 numbers can range from 0000 to 1111
    //  so their and result will be 0000, it's the common parts which matters
    //  so we will start from i=0 and keep unsetting the right bits till left and right are equal
    private static void type3() {
        int left = 5, right = 7;
        int ans = rangeBitwiseAnd3(left, right);
        System.out.println(ans);
    }

    private static int rangeBitwiseAnd3(int left, int right) {
        if (left == 0) return 0;
        int mask = 0;
        // we will be chopping of elements from the right side from both the numbers
        while (left != right) {
            // the mask will be like 00000111 and ~mask will be 11111000
            // so if we do and operation with ~mask it will unset the last bits
            mask = (mask << 1) + 1;
            left = (left & (~mask));
            right = (right & (~mask));
        }
        return left;
    }

    // todo optimized approach
    //  the intuition is not very hard here
    //  for any and operation the result will be 0 when any bit is 0
    //  lets say we have 5 and 7 => 00101 -> 00111
    //  numbers in that range => 00101, 00110, 00111
    //  for i=0 digit we know it will it will be 0 after every one number
    //  for i=1 digit we know it will it will be 0 after every two numbers
    //  like 0010 => 0011 => 00101
    //  for i=2 digit we know it will it will be 0 after every four numbers
    //  like 00100 => 00101 => 00110 => 00111 => 01000
    //  lets say the number is 0101001 and we are considering i=3 bit
    //  the last number for which the and operation in i=3 bit will be 1 is 0101111
    //  if the right is greater than 0101111 then then there will be a number like 0110000
    //  for which total and result on i=3 will be 0
    //  we will start from 0 bit
    private static void type2() {
        int left = 5, right = 7;
        int ans = rangeBitwiseAnd2(left, right);
        System.out.println(ans);
    }

    // 101, 110, 111
    private static int rangeBitwiseAnd2(int left, int right) {
        int mask = 0;
        // the mask will be like 00000111
        for (int i = 0; i < 32; i++) {
            mask = (mask << 1) + 1;
            int bit = (left >> i) & 1; // getting the i-th bit of left num
            if (bit == 0) continue;
            int rangeLast = left | mask; // range last will be like 0101111
            // if the range last is greater than the right num, then we have found the common prefix.
            // the mask is like 00001111... so the ~mask will be 11110000… and if we do and operation with left num, we will get the common prefix.
            // before doing that, we need to do right shift the mask as mask has the ith bit as well, but we also need that
            if (rangeLast >= right) {
                return (left & (~(mask >> 1)));
            }
        }
        return 0;
    }

    // brute force approach
    private static void type1() {
        int left = 5, right = 7;
        int ans = rangeBitwiseAnd1(left, right);
        System.out.println(ans);
    }

    public static int rangeBitwiseAnd1(int left, int right) {
        int ans = left;
        for (int num = left + 1; num <= right; num++) {
            ans &= num;
        }
        return ans;
    }

}
