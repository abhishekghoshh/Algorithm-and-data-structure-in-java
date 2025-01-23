package com.problems.string;

/*
 *
 * problem links :
 * https://leetcode.com/problems/largest-3-same-digit-number-in-string/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=vcrOpJQHsSE
 *
 * https://neetcode.io/solutions/largest-3-same-digit-number-in-string
 * */
public class Largest3SameDigitNumberInString {
    public static void main(String[] args) {
        type1();
    }

    // optimized approach
    // this is very easy to implement
    // we will go through all the characters one by one
    // and take variable for prev char, curr char, max same character count
    private static void type1() {
    }

    static String largestGoodInteger(String num) {
        int count = 0, prev = -1;
        int ans = -1;
        for (char ch : num.toCharArray()) {
            int curr = ch - '0';
            // if prev and curr are not same then we will reinitialize the counter again
            if (prev != curr) {
                count = 1;
                prev = curr;
            } else {
                count++;
                // if the character count reaches to 3 then we will quickly check xxx becomes the ans or not
                if (count == 3) {
                    int sum = curr * 100 + curr * 10 + curr;
                    ans = Math.max(ans, sum);
                }
            }
        }
        if (ans == -1) return "";
        // 000 int value is not possible that's why we are manually writing the if-clause to return "000"
        if (ans == 0) return "000";
        return String.valueOf(ans);
    }
}
