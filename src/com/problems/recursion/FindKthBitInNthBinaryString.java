package com.problems.recursion;

/*
 * Problem link :
 * https://leetcode.com/problems/find-kth-bit-in-nth-binary-string
 *
 * Solution is :
 *
 * */
public class FindKthBitInNthBinaryString {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int n = 4;
        int k = 11;
        char ch = findKthBit2(n, k);
        System.out.println(ch);
    }

    // S1 = "0"
    // S2 = "011"
    // S3 = "0111001"
    // S4 = "011100110110001"
    // clearly we can se some cases
    // if n=1 then it is always 0 because it is the starting point.
    // or if k = 1 then also it is 0
    // if k is the last element, then it is always 1
    // so, we have three terminal cases.
    // if we think how to find length, then just by some example, we can find out that
    // the length is (2^n-1), and it is valid for all the S1,S2...Sn
    // now we have to think about other cases.
    // let's see how a string construct
    // Sn = Sm + 1 + (rev inv Sm)
    // we can divide the string in three parts,
    // (first half) + (mid element) + (last half, where mid-element is always 1
    // we have two remaining cases for the first half
    // if we look closely then we will find that finding in the first half for S(n,k)
    // is equal to find in the total string of S(n-1,k) , because first half is essentially the previous string.
    // so, we have the last half which is the reversed and inverted of the first half.
    // so if we can find the corresponding index from the first half, then we can just reverse the bit
    // and return that in the answer
    public static char findKthBit2(int n, int k) {
        // if n=1 then it is always 0 because it is the starting point.
        // if k = 1 then also it is 0
        if (n == 1 || k == 1) return '0';
        int generatedLength = (1 << n) - 1;
        // if k is the last element, then it is always 1
        if (k == generatedLength) return '1';
        int mid = 1 + generatedLength / 2;
        if (k == mid) {
            // the middle part is always 1
            return '1';
        } else if (k < mid) {
            // finding in the first half for S(n,k) is equal to find in the total string of S(n-1,k)
            return findKthBit2(n - 1, k);
        } else {
            //  corresponding index from the first half
            int revertedIndex = generatedLength - k + 1;
            char ch = findKthBit2(n - 1, revertedIndex);
            // reverting the character
            return ch == '1' ? '0' : '1';
        }
    }

    // simple brute force approach
    private static void type1() {
        int n = 4;
        int k = 11;
        char ch = findKthBit1(n, k);
        System.out.println(ch);
    }

    public static char findKthBit1(int n, int k) {
        if (n == 1) return '0';
        // we will construct nth string
        char[] arr = construct(n);
        return arr[k - 1];
    }

    private static char[] construct(int n) {
        if (n == 1) return new char[]{'0'};
        char[] child = construct(n - 1);
        int len = 2 * child.length + 1;
        char[] arr = new char[len];
        int ptr = 0;
        // calculating the first half and last half in the same loop
        for (; ptr < child.length; ptr++) {
            char ch = child[ptr];
            arr[ptr] = ch;
            arr[len - ptr - 1] = (ch == '1') ? '0' : '1';
        }
        // adding the middle element
        arr[ptr] = '1';
        return arr;
    }
}
