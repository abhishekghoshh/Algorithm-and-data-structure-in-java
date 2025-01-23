package com.problems.fenwicktree;

/*
 * Problem link :
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=DPiY9wFxGIw
 */
public class FenwickTreePrerequisites {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // check bit is set or not
    private static void type1() {
        int num = 710;
        int i = 3; // 0 indexed 3rd place means actually it is 2nd
        System.out.println("710 in binary is " + Integer.toBinaryString(num));
        int mask = (1 << (i - 1));
        System.out.println("mask in binary is " + Integer.toBinaryString(mask));
        int bit = (num & mask) == 0 ? 0 : 1;
        System.out.println("bit is " + bit);
    }

    // extract the first set bit from left
    private static void type2() {
        int num = 710;
        System.out.println("710 in binary is " + Integer.toBinaryString(num));
        int lastBit = num & (-num);
        System.out.println("last bit in binary is " + Integer.toBinaryString(lastBit));
    }

    // remove the last set bit
    private static void type3() {
        int num = 710;
        System.out.println("710 in binary is " + Integer.toBinaryString(num));
        int ans = num - (num & (-num));
        System.out.println("after extracting last bit the binary is " + Integer.toBinaryString(ans));
    }
}
