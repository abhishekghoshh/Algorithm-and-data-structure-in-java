package com.problems.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * problem links :
 * https://leetcode.com/problems/palindrome-number/description/
 * Solution link :
 * https://www.youtube.com/watch?v=1xNbjMdbjug&t=1230s
 *
 * https://takeuforward.org/data-structure/check-if-a-number-is-palindrome-or-not/
 * */
public class PalindromeNumber {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // rather saving it in list
    // if we can reverse the number and still the number is same
    // then it will be palindrome
    private static void type2() {
        int x = 121;
        boolean ans = isPalindrome2(x);
        System.out.println(ans);
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int num = x, rev = 0;
        // reversing the number
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x = x / 10;
        }
        return num == rev;
    }

    // using extra list to save all the digits first
    // then we will check on the list
    private static void type1() {
        int x = 121;
        boolean ans = isPalindrome1(x);
        System.out.println(ans);
    }

    public static boolean isPalindrome1(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        List<Integer> list = new ArrayList<>();
        // saving the digits in a list
        while (x > 0) {
            list.add(x % 10);
            x = x / 10;
        }
        // now checking from the list
        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (!Objects.equals(list.get(i), list.get(j))) return false;
            i++;
            j--;
        }
        return true;
    }
}
