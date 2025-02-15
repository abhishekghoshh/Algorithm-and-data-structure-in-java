package com.problems.math;

/*
 * problem links :
 * https://leetcode.com/problems/reverse-integer/description/
 * https://neetcode.io/problems/reverse-integer
 *
 * Solution link :
 * https://www.youtube.com/watch?v=1xNbjMdbjug&t=1s
 * https://takeuforward.org/maths/reverse-digits-of-a-number
 *
 * https://www.youtube.com/watch?v=HAgLH58IgJQ
 * https://neetcode.io/solutions/reverse-integer
 * */
public class ReverseInteger {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // most optimized approach
    private static void type3() {
        int x = -123;
        int reverse = reverse(x);
        System.out.println(reverse);
    }

    public static int reverse(int x) {
        boolean negative = x < 0;
        if (negative) x = -x;
        int i = x, bound = Integer.MAX_VALUE, reverse = 0, digit;
        int tens = 1;
        while (i > 9) {
            tens *= 10;
            i = i / 10;
        }
        i = x;
        while (tens > 0) {
            digit = i % 10;
            i = i / 10;
            if (tens == 1000000000 && digit > 2) return 0;
            bound -= (digit * tens);
            if (bound < 0) return 0;
            reverse = reverse + digit * tens;
            tens = tens / 10;
        }
        if (negative) reverse = -reverse;
        return reverse;
    }

    // optimized approach
    // but this approach uses long data
    // which is prohibited in the question
    private static void type2() {
        int x = -123;
        int reverse = reverse2(x);
        System.out.println(reverse);
    }

    public static int reverse2(int x) {
        long y = 0;
        int i = 0;
        if (x < 0) {
            i = 1;
            x = -x;
        }
        while (x > 0) {
            y *= 10;
            y += x % 10;
            x = x / 10;
        }
        if (y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) return 0;
        if (i == 0) return (int) y;
        return (int) -y;
    }

    // brute force approach
    private static void type1() {
        int x = -123;
    }
}
