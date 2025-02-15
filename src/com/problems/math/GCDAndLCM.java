package com.problems.math;

/*
 *
 * problem links :
 * https://www.geeksforgeeks.org/problems/lcm-and-gcd4516/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=1xNbjMdbjug
 *
 * https://takeuforward.org/data-structure/find-gcd-of-two-numbers/
 * */
public class GCDAndLCM {
    // LCM -> Lowest common multiple
    // HCF -> Highest common factor | GCD -> Greatest common divisor
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    private static void type2() {
        long a = 25, b = 10;
        long[] ans = lcmAndGcd2(a, b);
        System.out.println(ans[0] + " " + ans[1]);
    }

    static long[] lcmAndGcd2(long A, long B) {
        long mul = A * B;
        long gcd = gcd2(A, B);
        long lcm = mul / gcd;
        return new long[]{lcm, gcd};
    }

    private static long gcd2(long a, long b) {
        // Continue loop as long as both a and b are greater than 0
        while (a > 0 && b > 0)
            // If 'a' is greater than b, subtract b from a and update a
            if (a > b)
                // Update 'a' to the remainder of a divided by b
                a = a % b;
                // If b is greater than or equal to 'a', subtract a from b and update b
            else
                // Update b to the remainder of b divided by a
                b = b % a;
        return (a == 0) ? b : a;
    }

    // brute force approach
    // we know one thing that is lcm * gcd = num1 * num2
    // so if we find the gcd then we can easily calculate lcm
    // for gcd we will take the min(num1,num2) then from min to 1
    // we will check if the number is a factor of both the num1 and num2
    // if yes then we will return the number
    private static void type1() {
        long a = 25, b = 10;
        long[] ans = lcmAndGcd1(a, b);
        System.out.println(ans[0] + " " + ans[1]);
    }

    static long[] lcmAndGcd1(long A, long B) {
        long mul = A * B;
        long gcd = gcd1(A, B);
        long lcm = mul / gcd;
        return new long[]{lcm, gcd};
    }

    private static long gcd1(long a, long b) {
        long gcd = Math.min(a, b);
        for (long i = gcd; i >= 1; i--)
            if (a % i == 0 && b % i == 0) return i;
        return -1;
    }
}
