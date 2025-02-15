package com.problems.dp;

/*
 * Problem link :
 * https://leetcode.com/problems/wildcard-matching/description/
 * https://www.naukri.com/code360/problems/wildcard-pattern-matching_701650
 *
 * Solution link :
 * https://www.youtube.com/watch?v=ZmlQ3vgAOMo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=35
 * https://takeuforward.org/data-structure/wildcard-matching-dp-34/
 */
public class WildcardMatching {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
        type5();
    }

    // TODO check it later
    // It is the most efficient technique
    // it uses two pointer technique
    private static void type5() {
        String s = "adceb", p = "*a*b";
        boolean isMatch = isMatch5(s, p);
        System.out.println(isMatch);
    }

    public static boolean isMatch5(String s, String p) {
        int n1 = s.length(); // Get the length of the input string
        int n2 = p.length(); // Get the length of the pattern string

        int i1 = 0, i2 = 0; // Pointers for traversing the input string and pattern
        int startIndex = -1; // Index of the last encountered '*' in the pattern
        int match = 0; // Index of the character in the input string where the last '*' matched

        while (i1 < n1) { // Iterate through the input string
            if (i2 < n2 && ((s.charAt(i1) == p.charAt(i2)) || p.charAt(i2) == '?')) {
                // If the current characters in the input string and pattern match, or the pattern character is '?'
                i1++; // Move to the next character in the input string
                i2++; // Move to the next character in the pattern
            } else if (i2 < n2 && p.charAt(i2) == '*') {
                // If the current character in the pattern is '*'
                startIndex = i2; // Store the index of the '*' in the pattern
                match = i1; // Store the index of the character in the input string where the '*' matches
                i2++; // Move to the next character in the pattern
            } else if (startIndex != -1) {
                // If there was a previously encountered '*' in the pattern
                i2 = startIndex + 1; // Move the pattern pointer to the character after the '*'
                match++; // Advance the input string pointer to try the next character match
                i1 = match; // Update the input string pointer to the new match index
            } else {
                // If none of the above conditions are met, it means the pattern cannot match the input string
                return false;
            }
        }

        // At this point, we have reached the end of the input string
        while (i2 < n2 && p.charAt(i2) == '*') {
            // Skip any remaining '*' characters in the pattern
            i2++;
        }

        // If we have reached the end of the pattern after skipping the '*' characters, it means the pattern matches the input string
        return i2 == n2;
    }

    // bottom-up approach with space optimization
    // we will use only two arrays for storing current and previous row values
    private static void type4() {
        String s = "adceb", p = "*a*b";
        boolean isMatch = isMatch4(s, p);
        System.out.println(isMatch);
    }

    private static boolean isMatch4(String s, String p) {
        char[] arr1 = s.toCharArray(), arr2 = p.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        boolean[] prev = new boolean[n2 + 1];

        // if both n1 and n2 are zero, we will set it as 0
        prev[0] = true;
        // for all n1 as zero, checking if from start the pattern has all the * all not
        for (int i = 1; i <= n2 && arr2[i - 1] == '*'; i++)
            prev[i] = true;

        // check the recursion for finding all the recurrence relations
        for (int i = 1; i <= n1; i++) {
            boolean[] curr = new boolean[n2 + 1];
            // for all n2 as zero, the value of the dp will be 0
            curr[0] = false;
            for (int j = 1; j <= n2; j++) {
                if (arr1[i - 1] == arr2[j - 1] || arr2[j - 1] == '?')
                    curr[j] = prev[j - 1];
                else if (arr2[j - 1] == '*')
                    curr[j] = prev[j] || curr[j - 1];
                else
                    curr[j] = false;
            }
            // assigning the curr as the prev
            prev = curr;
        }
        return prev[n2];
    }

    // bottom-up approach with tabulation
    // we will use 2D array to store the dp values
    private static void type3() {
        String s = "adceb", p = "*a*b";
        boolean isMatch = isMatch3(s, p);
        System.out.println(isMatch);
    }

    private static boolean isMatch3(String s, String p) {
        char[] arr1 = s.toCharArray(), arr2 = p.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];

        // if both n1 and n2 are zero, we will set it as 0
        dp[0][0] = true;
        // for all n1 as zero, checking if from start the pattern has all the * all not
        for (int i = 1; i <= n2 && arr2[i - 1] == '*'; i++)
            dp[0][i] = true;

        // for all n2 as zero, the value of the dp will be 0
        for (int i = 1; i < n1; i++) dp[i][0] = false;

        // check the recursion for finding all the recurrence relations
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (arr1[i - 1] == arr2[j - 1] || arr2[j - 1] == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (arr2[j - 1] == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                else
                    dp[i][j] = false;
            }
        }
        return dp[n1][n2];
    }

    // bottom-up approach with recursion as memoization
    // 0 means not traversed, -1 is false, and 1 is true
    private static void type2() {
        String s = "adceb", p = "*a*b";
        boolean isMatch = isMatch2(s, p);
        System.out.println(isMatch);
    }

    public static boolean isMatch2(String s, String p) {
        char[] arr1 = s.toCharArray(), arr2 = p.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        return isMatch2(n1, n2, arr1, arr2, dp);
    }

    public static boolean isMatch2(int n1, int n2, char[] arr1, char[] arr2, int[][] dp) {
        // if both n1 and n2 is 0 that means both of the strings are consumed
        if (n1 == 0 && n2 == 0) return true;
        // if n2 is 0, that means pattern string is consumed and we will return 0
        if (n2 == 0) return false;
        // if the first string is consumed entirely, then to match with the pattern.
        // the pattern must have all the characters as *
        // if it is then we will return true else false
        if (n1 == 0) {
            while (n2 > 0) {
                if (arr2[n2 - 1] != '*') return false;
                n2--;
            }
            return true;
        }
        // if the cell is computed, then we will return
        if (dp[n1][n2] != 0) return dp[n1][n2] == 1;

        // if both the character matches or pattern has ? as character, then will
        // consider it as a match
        boolean isMatch = false;
        if (arr2[n2 - 1] == arr1[n1 - 1] || arr2[n2 - 1] == '?') {
            isMatch = isMatch2(n1 - 1, n2 - 1, arr1, arr2, dp);
        } else if (arr2[n2 - 1] == '*') {
            // if it is * then we have two options, either to consider * as a single character or an empty character
            // n1-1 means we are using * to match a character in the original string.
            // n2-1 means we are treating the * as an empty character
            isMatch = isMatch2(n1 - 1, n2, arr1, arr2, dp)
                    || isMatch2(n1, n2 - 1, arr1, arr2, dp);
        }
        // saving to the int array
        dp[n1][n2] = isMatch ? 1 : -1;
        return isMatch;
    }

    // brute force approach using recursion
    private static void type1() {
        String s = "adceb", p = "*a*b";
        boolean isMatch = isMatch1(s, p);
        System.out.println(isMatch);
    }

    public static boolean isMatch1(String s, String p) {
        char[] arr1 = s.toCharArray(), arr2 = p.toCharArray();
        int n1 = arr1.length, n2 = arr2.length;
        return isMatch1(n1, n2, arr1, arr2);
    }

    public static boolean isMatch1(int n1, int n2, char[] arr1, char[] arr2) {
        // if both n1 and n2 is 0 that means both of the strings are consumed
        if (n1 == 0 && n2 == 0) return true;
        // if n2 is 0, that means pattern string is consumed and we will return 0
        if (n2 == 0) return false;
        // if the first string is consumed entirely, then to match with the pattern.
        // the pattern must have all the characters as *
        // if it is then we will return true else false
        if (n1 == 0) {
            while (n2 > 0) {
                if (arr2[n2 - 1] != '*') return false;
                n2--;
            }
            return true;
        }

        // if both the character matches or pattern has ? as character, then will
        // consider it as a match
        if (arr1[n1 - 1] == arr2[n2 - 1] || arr2[n2 - 1] == '?') {
            return isMatch1(n1 - 1, n2 - 1, arr1, arr2);
        } else if (arr2[n2 - 1] == '*') {
            // if it is * then we have two options, either to consider * as a single character or an empty character
            // n1-1 means we are using * to match a character in the original string.
            // n2-1 means we are treating the * as an empty character
            return isMatch1(n1 - 1, n2, arr1, arr2)
                    || isMatch1(n1, n2 - 1, arr1, arr2);
        } else {
            return false;
        }
    }
}
