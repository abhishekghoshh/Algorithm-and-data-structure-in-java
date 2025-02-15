package com.problems.dp;

/*
 * Problem links:
 * https://leetcode.com/problems/count-substrings-divisible-by-last-digit/description/
 *
 * Solution link
 * https://leetcode.com/problems/count-substrings-divisible-by-last-digit/solutions/6396063/dp-solution-dp-i-num-rem-c-solution-o-n-10-10/
 * */
public class CountSubstringsDivisibleByLastDigit {
    public static void main(String[] args) {
        type1();
    }

    // todo complete it later
    //  check the solutions from the others
    private static void type1() {
        String s = "12936";
        long ans = countSubstrings1(s);
        System.out.println(ans);
    }

    public static long countSubstrings1(String s) {
        return 0L;
    }
}
