package com.problems.math;

/*
 *
 * problem links :
 * https://www.naukri.com/code360/problems/check-armstrong_589
 * https://leetcode.com/problems/armstrong-number/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=1xNbjMdbjug
 *
 * https://takeuforward.org/maths/check-if-a-number-is-armstrong-number-or-not/
 * */
public class ArmstrongNumbers {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int n = 371;
        boolean ans = checkArmstrong(n);
        System.out.println(ans);
    }

    private static boolean checkArmstrong(int n) {
        if (n == 0) return true;
        int copy = n;
        // let's find the digit count first
        int count = 0;
        while (copy > 0) {
            count++;
            copy = copy / 10;
        }
        copy = n;
        int num = 0;
        // we will find the armstrong value then, and check both are same or not
        while (copy > 0) {
            int digit = copy % 10;
            copy = copy / 10;
            num += (int) Math.pow(digit, count);
        }
        return (n == num);
    }
}
