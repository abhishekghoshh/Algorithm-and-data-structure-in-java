package com.problems.math;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/sum-of-all-divisors-from-1-to-n4738/1
 * Solution link :
 * https://www.youtube.com/watch?v=UmzdzwsAsLg
 */
public class SumOfAllDivisorsFrom1ToN {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // we will use a clever technique
    // lets take the number 8 and calculate all its divisor
    // 1 -> 1
    // 2 -> 1,2
    // 3 -> 1,3
    // 4 -> 1,2,4
    // 5 -> 1,5
    // 6 -> 1,2,3,6
    // 7 -> 1,7
    // 8 -> 1,2,4,8
    // lets see which number is present for how many numbers
    // 1 is present in 8 time, 2 is present 4 times, 3 is 2 times
    // 4 is 2 times, 5 is 1, 6 is 1 and 7 is 1 and 8 is 1 times
    // seems like there is a pattern
    // count of every element(i) => Math.floor(N/i)
    // so the total sum contribution for 'i' would be i * Math.floor(N/i)
    private static void type1() {
        int N = 8;
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += (long) i * (N / i);
        }
        System.out.println(sum);
    }

    // brute force approach,
    // but it will get TLE as n will 10^6
    // the time complexity here is O(N^2) => 10^12
    private static void type2() {
        int N = 8;
        long ans = sumOfDivisors1(N);
        System.out.println(ans);
    }

    static long sumOfDivisors1(int N) {
        if (N == 1) return 1;
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) sum += i;
        }
        return sum + sumOfDivisors1(N - 1);
    }
}
