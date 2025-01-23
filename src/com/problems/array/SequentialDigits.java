package com.problems.array;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // todo tell this is the interview
    // the intuition is very simple
    // if the low = 1000, high = 33000
    // we will start from 1234 then will add 1111 till 6789 (the last is not 0)
    // so it will generate 1234, 2345, 3456, 4567, 5678, 6789
    // then on next iteration we will start 12345 and add 11111
    // if the previous base was 1234 then we will multiply with 10 and add (lastBit + 1)
    // 1234*10 + 5 => 12345
    // for the adder we will do like this 1111*10 + 1 => 11111
    private static void type2() {
        int low = 100;
        int high = 300;
        List<Integer> ans = sequentialDigits2(low, high);
        System.out.println(ans);
    }

    public static List<Integer> sequentialDigits2(int low, int high) {
        // count will return how many digits low and high has
        int n1 = count(low), n2 = count(high);
        // we will first calculate for n1 then in the loop we will increment the base and the adder
        // base will be like 1234.... and the adder will be like 1111....
        int base = 0, adder = 0;
        for (int i = 1; i <= n1; i++) {
            base = base * 10 + i;
            adder = adder * 10 + 1;
        }
        List<Integer> list = new ArrayList<>();
        for (int n = n1; n <= n2; n++) {
            int num = base;
            // moving till the last digit is not equal to 0;
            // generating all the sequential digits
            while ((num % 10) != 0) {
                if (low <= num && num <= high)
                    list.add(num);
                num += adder;
            }
            // updating the base 1234 to 12345
            base = (base * 10) + ((base % 10) + 1);
            // updating the adder 1111 to 11111
            adder = (adder * 10) + 1;
        }
        return list;
    }

    static int count(int num) {
        int n = 0;
        while (num > 0) {
            num /= 10;
            n++;
        }
        return n;
    }

    // brute force approach
    private static void type1() {
    }
}
