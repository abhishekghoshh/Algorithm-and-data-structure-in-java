package com.problems.string;

/*
 * Problem link :
 * https://www.naukri.com/code360/problems/decode-string_696319
 * https://www.geeksforgeeks.org/problems/decode-the-string2444/1
 *
 * Solution link :
 *
 * https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
 * https://www.naukri.com/code360/problem-details/encode-and-decode_1467061
 */
public class DecodeString {
    public static void main(String[] args) {
        type1();
    }

    // using recursion
    private static void type1() {
        String str = "3[a2[b]]";
        String ans = decodeString1(str);
        System.out.println(ans);
    }


    private static String decodeString1(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;

        return "";
    }
}
