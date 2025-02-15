package com.problems.array;
/*
 *
 * problem links :
 * https://leetcode.com/problems/largest-number/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=WDx6Y4i4xJ8
 *
 * https://neetcode.io/solutions/largest-number
 * */

import java.util.Arrays;
import java.util.Objects;

// Tags: Arrays
public class LargestNumber {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[] nums = {3, 30};
        String ans = largestNumber2(nums);
        System.out.println(ans);
    }

    public static String largestNumber2(int[] nums) {
        int n = nums.length;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++)
            arr[i] = String.valueOf(nums[i]);
        // it will sort in reverse
        Arrays.sort(arr, (num1, num2) -> isGreater(num1, num2) ? -1 : 1);
        String res = String.join("", arr);
        return res.charAt(0) == '0' ? "0" : res;
    }

    // checking which combination is greater either num1-num2 or num2-num1
    // or in simple words num1 > num2 if yes we will return true else false
    // comparator will return
    private static boolean isGreater(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();
        int i = 0;
        while (i < n1 + n2) {
            char c1 = i < n1 ? num1.charAt(i) : num2.charAt(i - n1);
            char c2 = i < n2 ? num2.charAt(i) : num1.charAt(i - n2);
            if (c1 > c2) return true;
            else if (c1 < c2) return false;
            i++;
        }
        return false;
    }

    // brute force approach
    // sorting the array
    private static void type1() {
        int[] nums = {3, 30, 34, 5, 9};
        String ans = largestNumber1(nums);
        System.out.println(ans);
    }

    public static String largestNumber1(int[] nums) {
        Integer[] copy = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(copy, (n1, n2) -> isGreater(n1, n2));
        StringBuilder sb = new StringBuilder();
        long sum = 0;
        for (Integer num : copy) {
            sum += num;
            sb.append(num);
        }
        if (sum == 0) return "0";
        return sb.toString();
    }

    private static int isGreater(Integer n1, Integer n2) {
        if (Objects.equals(n1, n2)) return 0;
        if (n1 == 0) return 1;
        if (n2 == 0) return -1;
        long num1 = n1 * base(n2) + n2;
        long num2 = n2 * base(n1) + n1;
        return (num1 > num2) ? -1 : 1;
    }

    static long base(int n) {
        long base = 1L;
        while (n > 0) {
            base *= 10;
            n = n / 10;
        }
        return base;
    }
}
