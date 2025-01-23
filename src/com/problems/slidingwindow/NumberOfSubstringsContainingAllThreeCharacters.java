package com.problems.slidingwindow;
/*
 *
 * problem links :
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters
 * https://www.codingninjas.com/studio/problems/count-substring-with-abc_8160465
 *
 * Solution link :
 *
 * */
public class NumberOfSubstringsContainingAllThreeCharacters {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    private static void type3() {
    }

    // TODO best possible solution
    // found an optimized approach at the first attempt
    // so, we are starting from one index, and we are looping until types == 3
    // once we find types is 3 we surely know any character appended after that will
    // also have types is 3, so the substring string with index i1 and let's say we get
    // types is 3 at i2, then (n-i2) will be total number of substring possible for the
    // substring starting with i1
    // like for abcaba -> substring will be abc,abca,abcab,abcaba
    // for i1 = 0 and i2 = 2
    // the substring count will be n-i2 => 6-2 => 4
    // once we find the types == 3 then we will try to shrink the window from left
    // until type is less than 3
    private static void type2() {
        String s = "abcabc";
        char[] arr = s.toCharArray();
        int n = arr.length;
        int end = 0, start = 0, types = 0, ch, chToRemove;
        int count = 0;
        int[] freq = new int[3];
        for (; end < n; end++) {
            ch = arr[end] - 'a';
            freq[ch]++;
            if (freq[ch] == 1) types++;
            while (types == 3) {
                count += (n - end);
                chToRemove = arr[start++] - 'a';
                freq[chToRemove]--;
                if (freq[chToRemove] == 0) types--;
            }
        }
        System.out.println(count);

    }
    // Brute force approach
    private static void type1() {
    }
}
