package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/largest-odd-number-in-string/
 *
 * Solution link:
 *
 *
 *
 * */
public class LargestOddNumberInString {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we will follow the greedy approach,
    // the largest odd number will always end with odd number,
    // so if we find the first odd number, then
    // the answer will be 0..n
    private static void type2() {
        String num = "35427";
        int n = num.length() - 1;
        while (n >= 0) {
            if (isOdd(num.charAt(n))) break;
            n--;
        }
        String answer = n == -1 ? "" : num.substring(0, n + 1);
        System.out.println(answer);
    }

    private static boolean isOdd(char ch) {
        return ((ch - '0') & 1) == 1;
    }

    // brute force approach
    private static void type1() {
        String num = "35427";
        char[] arr = num.toCharArray();
        int n = arr.length;
        int sum, factor, digit;
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum = 0;
            factor = 1;
            for (int j = i; j >= 0; j--) {
                digit = arr[j] - '0';
                sum += digit * factor;
                factor *= 10;
                if (sum % 2 == 1) max = Math.max(max, sum);
            }
        }
        String answer = String.valueOf(max);
        System.out.println(answer);
    }
}
