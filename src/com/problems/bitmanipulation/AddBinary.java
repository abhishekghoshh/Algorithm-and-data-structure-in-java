package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/add-binary/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=keuWJ47xG8g
 * https://neetcode.io/solutions/add-binary
 */
public class AddBinary {
    public static void main(String[] args) {
        type1();
    }

    // todo optimized approach
    private static void type1() {
        String a = "1010", b = "1011";
        String ans = addBinary(a, b);
        System.out.println(ans);
    }

    public static String addBinary(String a, String b) {
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        // as the bits are in reverse order of the string,
        // so we will start from the end of the string
        int n1 = arr1.length, n2 = arr2.length;
        int i = n1 - 1, j = n2 - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry == 1) {
            int bit1 = (i >= 0) ? (arr1[i--] - '0') : 0;
            int bit2 = (j >= 0) ? (arr2[j--] - '0') : 0;
            int sum = bit1 + bit2 + carry;
            // it sums 1 or 3 then it will add 1 to the result, if it is 0 or 2, then it will add 0
            sb.append(sum & 1);
            // if the sum is greater than 1, then carry will be 1, otherwise 0
            carry = sum >> 1;
        }
        return sb.reverse().toString();
    }
}
