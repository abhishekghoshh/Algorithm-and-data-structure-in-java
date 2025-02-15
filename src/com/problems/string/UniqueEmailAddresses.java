package com.problems.string;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem link :
 * https://leetcode.com/problems/unique-email-addresses/description/
 *
 * Solution link :
 *
 *
 * https://neetcode.io/solutions/unique-email-addresses
 */
// Tags : String, Array
public class UniqueEmailAddresses {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // little better than the previous
    private static void type2() {
        String[] emails = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        int ans = numUniqueEmails2(emails);
        System.out.println(ans);
    }

    private static int numUniqueEmails2(String[] emails) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (String email : emails) {
            char[] arr = email.toCharArray();
            int i = 0, n = arr.length;
            while (i < n) {
                if (arr[i] == '.') {
                    i++;
                    continue;
                }
                sb.append(arr[i++]);
                if (arr[i] == '+' || arr[i] == '@') {
                    while (arr[i] != '@') i++;
                    break;
                }
            }
            while (i < n) sb.append(arr[i++]);
            set.add(sb.toString());
            sb.setLength(0);
        }
        return set.size();
    }

    // brute force but optimized
    // requires only one iteration for every string
    private static void type1() {
        String[] emails = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        int ans = numUniqueEmails1(emails);
        System.out.println(ans);
    }

    public static int numUniqueEmails1(String[] emails) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (String email : emails) {
            boolean addAll = false;
            boolean hadPlus = false;
            for (char ch : email.toCharArray()) {
                // once we find the @ we will add all the remaining
                if (addAll) {
                    sb.append(ch);
                    continue;
                }
                // if it + then we will set the hadPlus as true
                if (ch == '+') hadPlus = true;
                if (ch == '@') {
                    addAll = true;
                    sb.append(ch);
                }
                if (Character.isLetter(ch) && !hadPlus) sb.append(ch);
            }
            set.add(sb.toString());
            sb.setLength(0);
        }
        return set.size();
    }
}
