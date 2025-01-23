package com.problems.recursion;

import java.util.LinkedList;
import java.util.List;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/-binary-strings-with-no-consecutive-1s._893001
 *
 * Solution link :
 *
 * */
public class GenerateAllBinaryStringsWithNoConsecutiveOnes {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as previous
    // we will also carry a flag for the last char
    // so that we do not need to check the last character from the string itself
    private static void type3() {
        int n = 3;
        List<String> answer = new LinkedList<>();
        generateString3(n, new StringBuilder(), answer, '-');
        System.out.println(answer);
    }

    public static void generateString3(int n, StringBuilder sb, List<String> answer, char last) {
        // if n is 0 then we will add the string to the answer
        if (n == 0) {
            answer.add(sb.toString());
            return;
        }
        // like previous we have added everytime
        sb.append('0');
        generateString3(n - 1, sb, answer, '0');
        sb.deleteCharAt(sb.length() - 1);

        // we will add 1 only if the last character is not 1
        if (last != '1') {
            sb.append('1');
            generateString3(n - 1, sb, answer, '1');
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // same as previous approach
    // here we have minified the condition
    private static void type2() {
        int n = 3;
        List<String> answer = new LinkedList<>();
        generateString2(n, new StringBuilder(), answer);
        System.out.println(answer);
    }

    public static void generateString2(int n, StringBuilder sb, List<String> answer) {
        // if the n is 0 then we will add the string into the answer
        if (n == 0) {
            answer.add(sb.toString());
            return;
        }
        // everytime, we will add 0 into the string and go to the next recursion
        sb.append('0');
        generateString2(n - 1, sb, answer);
        sb.deleteCharAt(sb.length() - 1);

        // we will only add 1 if the string is empty or the last character is 0
        // and go to the next recursion
        if (sb.isEmpty() || sb.charAt(sb.length() - 1) == '0') {
            sb.append('1');
            generateString2(n - 1, sb, answer);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    // using recursion,
    // we will use backtracking here
    // everytime we will add 0, but we will add 1 if the last character is not 1.
    // we will start with n, and after every addition we will reduce the n by 1
    // if n is 0 then we will add the string into the answer list
    private static void type1() {
        int n = 3;
        List<String> answer = new LinkedList<>();
        generateString1(n, new StringBuilder(), answer);
        System.out.println(answer);
    }

    public static void generateString1(int n, StringBuilder sb, List<String> answer) {
        // adding into the answer list
        if (n == 0) {
            answer.add(sb.toString());
            return;
        }
        // if last char is 0 then we will add 0 and 1 both
        if (sb.isEmpty() || sb.charAt(sb.length() - 1) == '0') {
            sb.append('0');
            generateString1(n - 1, sb, answer);
            sb.deleteCharAt(sb.length() - 1);

            sb.append('1');
            generateString1(n - 1, sb, answer);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            // if the last char is 1, then we will add only 0
            sb.append('0');
            generateString1(n - 1, sb, answer);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
