package com.problems.recursion;

/*
 * Problem link :
 * https://leetcode.com/problems/count-good-numbers/description/
 * https://www.codingninjas.com/studio/problems/good-numbers_625508
 *
 * Solution is :
 * https://www.youtube.com/watch?v=CctVpEGgNf0
 * */
public class CountGoodNumbers {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    static int MOD = 1000000007;
    static final int EVEN_NUMBER_COUNT = 5; // 0, 2, 4, 6, 8
    static final int PRIME_NUMBER_COUNT = 4;// 2, 3, 5, 7


    // we will use recursion and binary search here
    // from the previous type we understood that it is either
    // multiplying with 4 or 5 again and again, based on even or odd number,
    // so rather multiplying everytime we can use pow function.
    // we know if the count of digit is even then both EVEN_NUMBER_COUNT and PRIME_NUMBER_COUNT
    // will be multiplied exactly n/2 times.
    // and if the count is odd, then EVEN_NUMBER_COUNT will be multiplied n/2 +1 times
    // and PRIME_NUMBER_COUNT will be multiplied n/2 times
    private static void type3() {
        int n = 50;
        int result = goodNumbers3(n);
        System.out.println(result);
    }

    private static int goodNumbers3(int n) {
        long result = 1;
        result = pow(result, PRIME_NUMBER_COUNT, n / 2);
        result = pow(result, EVEN_NUMBER_COUNT, n / 2);
        if (n % 2 == 1) result = (result * EVEN_NUMBER_COUNT) % MOD;
        return (int) result;
    }

    // we will use a seed value unlike a normal pow function
    // seed value is result of the previous function
    // also it will use the mod operation
    private static long pow(long seed, long x, long n) {
        long result = seed;
        while (n > 0) {
            if (n % 2 == 0) {
                n = n / 2;
                x = (x * x) % MOD;
            } else {
                result = (result * x) % MOD;
                n--;
            }
        }
        return result;
    }

    // we will use recursion here
    // we do not need to construct 50th digit numbers and check it is a good number or not
    private static void type2() {
        int n = 50;
        int result = (int) goodNumbers2(n);
        System.out.println(result);
    }

    // everytime we will check that on which digit we are currently standing.
    // the digit count is even; that means the digit layout is [odd,even,odd,even,odd...even]
    // if it is odd then, the digit layout is [even,odd,even,odd...even]
    // the last digit will always be the even digit
    // every time we remove one digit from the first
    public static long goodNumbers2(long n) {
        // n==1 means it is the last digit or the oth digit, so we will return EVEN_NUMBER_COUNT
        if (n == 1) return EVEN_NUMBER_COUNT;
        // we will check that, on which digit we are currently standing
        if (n % 2 == 0)
            return (PRIME_NUMBER_COUNT * goodNumbers2(n - 1)) % MOD;
        else
            return (EVEN_NUMBER_COUNT * goodNumbers2(n - 1)) % MOD;
    }

    // brute force approach will be traverse every item and find and then
    // see if it is a good number or not
    private static void type1() {

    }
}
