package com.problems.greedy;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/assign-cookies/description/
 * https://www.naukri.com/code360/problems/assign-cookies_8390826
 *
 * Solution link :
 * https://www.youtube.com/watch?v=DIX2p7vb9co
 * https://www.youtube.com/watch?v=JW8fgvoxPTg
 *
 * https://neetcode.io/solutions/assign-cookies
 * */

// Tags: Array, Greedy
public class AssignCookies {
    public static void main(String[] args) {
        type1();
        type2();
    }



    // optimized approach
    // first we will sort the entire array
    // now we will use two pointers one on children another on the cookies
    // now we will go through all the cookies, we have cookies and children both sorted from lowest to highest,
    // we can only give a cookie to a child if cookie size <= student greed
    private static void type2() {
        int[] g = {1, 2, 3};
        int[] s = {1, 1};
        int count = findContentChildren2(g, s);
        System.out.println(count);
    }

    private static int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(s);
        Arrays.sort(g);
        int m = g.length, n = s.length;
        int count = 0;
        // going through all the cookies
        // increment the greed only the cookie can satisfy that
        for (int i = 0, j = 0; i < n && j < m; i++) {
            if (g[j] <= s[i]) {
                count++;
                j++;
            }
        }
        return count;
    }

    // brute force approach
    private static void type1() {

    }
}
