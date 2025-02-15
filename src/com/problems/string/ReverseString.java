package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/reverse-string/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=_d0T_2Lk2qA
 *
 * https://neetcode.io/solutions/reverse-string
 */
public class ReverseString {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String s = "Hello world";
        char[] arr = s.toCharArray();
        reverseString(arr);
        System.out.println(new String(arr));
    }

    public static void reverseString(char[] s) {
        int n = s.length;
        int i = 0, j = n - 1;
        while (i < j) {
            char ch = s[i];
            s[i] = s[j];
            s[j] = ch;
            i++;
            j--;
        }
    }

}
