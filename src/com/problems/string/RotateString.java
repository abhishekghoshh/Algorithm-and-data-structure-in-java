package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/rotate-string/description/
 * https://www.codingninjas.com/studio/problems/check-if-one-string-is-a-rotation-of-another-string_1115683
 *
 * Solution link:
 *
 *
 *
 * */
public class RotateString {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String s = "abcde", goal = "cdeab";
//        if (s.length() != goal.length()) return false;
        char[] sArr = s.toCharArray(), gArr = goal.toCharArray();
        int n = sArr.length, p2;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            counter = 0;
            for (int p1 = 0; p1 < n; p1++) {
                p2 = (p1 + i) % n;
                if (sArr[p1] != gArr[p2]) break;
                counter++;
            }
            if (counter == n) break;
        }
        boolean answer = counter == n;
        System.out.println(answer);
    }
}
