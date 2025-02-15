package com.problems.array;

/*
 * Problem link :
 * https://leetcode.com/problems/vowels-game-in-a-string/description/
 *
 * Solution link :
 */
public class VowelsGameInAString {
    public static void main(String[] args) {
        type1();
    }

    // the problem looks like a recursive problem, but we think about the testcases
    // then for 1 alice will choose the whole string
    // for 2 alice will first choose 1 vowel, bob will choose empty substring, alice will again choose the remaining
    // for 3 alice choose the entire string
    // for 4 there could be 2 configurations => 1 + 2 + 1 or 3 + 0 + 1
    // similarly for all other numbers alice will always win, no matter what bob does
    // as they are both playing optimally for winning and alice is starting at first position,
    // so she has the advantage
    private static void type1() {
        String s = "leetcoder";
        boolean ans = doesAliceWin(s);
        System.out.println(ans);
    }

    public static boolean doesAliceWin(String s) {
        for (char ch : s.toCharArray()) {
            // if there is at least one vowel, then alice will win always
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                return true;
        }
        // if there are no vowels then we will return false
        return false;
    }
}
