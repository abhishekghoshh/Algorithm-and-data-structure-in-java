package com.problems.bitmanipulation;

import static com.util.PrintUtl.print;

/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/bit-manipulation_8142533
 *
 * Solution link :
 *
 *
 *
 * */
public class BitManipulation {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int num = 25, i = 3;
        int[] answer = bitManipulation(num, i);
        print(answer);
    }

    public static int[] bitManipulation(int num, int i) {
        int mask = 1 << (i - 1);
        int bit = (num & mask) > 0 ? 1 : 0;
        int setBit = num | mask;
        int clearBit = num & (~mask);
        return new int[]{bit, setBit, clearBit};
    }

}
