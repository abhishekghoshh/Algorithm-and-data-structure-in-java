package com.problems.array;
/*
 *
 * problem links :
 *
 *
 * Solution link :
 */

public class CountPairsThatFormCompleteDay2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // intuition is
    // for an index,hours[i] % 24 will be ranging from 0,23
    // where 0 means it divided by 24 and the remaining 1..23 means not divided
    // for hours[i] + hours[j] divided by 24 means,
    // if hour[j]'s reminder is 15, then hour[i]'s reminder should be 9
    // so for jth element if we find how many elements are in the left that has reminder 9
    // then our problem is solved
    // if we start from 0..n then along the way we will store the reminder frequencies in an array
    // so (i<j) constraint will also be valid in that case
    // if the problem was (i>j) then we will start from the last or n..0
    private static void type2() {
        int[] hours = {12, 12, 30, 24, 24};
        int[] dp = new int[24];
        long count = 0;
        for (int hour : hours) {
            int curr = hour % 24;
            int rem = (24 - curr) % 24;
            count += dp[rem];
            dp[curr]++;
        }
        System.out.println(count);
    }

    // brute force approach
    private static void type1() {
        int[] hours = {12, 12, 30, 24, 24};
        int n = hours.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((hours[i] + hours[j]) % 24 == 0) count++;
            }
        }
        System.out.println(count);
    }
}
