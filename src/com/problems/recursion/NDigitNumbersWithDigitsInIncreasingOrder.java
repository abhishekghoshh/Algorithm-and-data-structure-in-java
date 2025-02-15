package com.problems.recursion;

import java.util.ArrayList;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/n-digit-numbers-with-digits-in-increasing-order5903/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=xlKrk3ZO3iM&list=PL_z_8CaSLPWdbOTog8Jxk9XOjzUs3egMP&index=11
 *
 * https://www.youtube.com/watch?v=YdRTk77iXXE
 */
public class NDigitNumbersWithDigitsInIncreasingOrder {

    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // using backtracking
    private static void type3() {
        int n = 2;
        ArrayList<Integer> result = new ArrayList<>();
        int[] nums = new int[n];
        // handling the 0 case differently
        increasingNumbers3(n, 0, 0, nums, result);
        if (n == 1) result.add(0, 0);
        System.out.println(result);
    }

    private static void increasingNumbers3(int n, int index, int prev, int[] nums, ArrayList<Integer> result) {
        if (index == n) {
            int num = 0;
            for (int i = 0; i < n; i++) num = num * 10 + nums[i];
            result.add(num);
            return;
        }
        for (int digit = prev + 1; digit <= 9; digit++) {
            nums[index] = digit;
            increasingNumbers3(n, index + 1, digit, nums, result);
        }
    }

    // using recursion
    private static void type2() {
        int n = 1;
        ArrayList<Integer> result = new ArrayList<>();
        // handling the 0 case differently
        increasingNumbers2(n, 0, "", result);
        if (n == 1) result.add(0, 0);
        System.out.println(result);
    }

    private static void increasingNumbers2(int n, int prev, String number, ArrayList<Integer> result) {
        if (n == 0) {
            result.add(Integer.parseInt(number));
            return;
        }
        for (int digit = prev + 1; digit <= 9; digit++) {
            increasingNumbers2(n - 1, digit, number + digit, result);
        }
    }

    // using normal recursion
    private static void type1() {
        int n = 2;
        ArrayList<Integer> result = increasingNumbers1(n);
        // handling the 0 case differently
        if (n == 1) result.add(0, 0);
        System.out.println(result);
    }

    static ArrayList<Integer> increasingNumbers1(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        if (n == 1) {
            for (int digit = 1; digit < 10; digit++) list.add(digit);
            return list;
        }
        ArrayList<Integer> prev = increasingNumbers1(n - 1);
        for (int num : prev) {
            int last = num % 10;
            for (int digit = last + 1; digit < 10; digit++)
                list.add(num * 10 + digit);
        }
        return list;
    }

}
