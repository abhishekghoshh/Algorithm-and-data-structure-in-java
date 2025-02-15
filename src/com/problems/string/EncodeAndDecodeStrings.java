package com.problems.string;

import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/encode-and-decode-strings/description/
 * https://neetcode.io/problems/string-encode-and-decode
 * https://www.lintcode.com/problem/659/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=B1k_sxOSgv8
 *
 * https://neetcode.io/solutions/encode-and-decode-strings
 * https://medium.com/@miniChang8/leetcode-encode-and-decode-strings-4dde7e0efa1c
 */

// Tags : Array, String, Hashing
public class EncodeAndDecodeStrings {
    public static void main(String[] args) {
        type1();
    }


    private static void type1() {
        List<String> strs = List.of("neet", "code", "love", "you");
        String encodedString = encode(strs);
        System.out.println(encodedString);
        List<String> decodedString = decode(encodedString);
        System.out.println(decodedString);
    }

    private static final String LS = ">>&&<<";

    public static String encode(List<String> list) {
        // if the list is empty then we will return null;
        if (list.isEmpty()) return null;
        StringBuilder sb = new StringBuilder();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            sb.append(list.get(i));
            // we will only append the separator if it is not the last element
            if (i != n - 1) sb.append(LS);
        }
        return sb.toString();
    }

    public static List<String> decode(String str) {
        // if the string is null then we will return and empty list
        if (null == str) return List.of();
        // else we will split the string based one separator
        return List.of(str.split(LS));
    }
}
