package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/expression-add-operators/description/
 * https://www.codingninjas.com/studio/problems/find-x-raised-to-power-n-_626560
 *
 * Solution link
 * https://www.youtube.com/watch?v=S6OG5pGWxIw
 * */
public class ExpressionAddOperators {
    // TODO check the solution one more time
    //  also there is one constraint
    //  Note that operands in the returned expressions should not contain leading zeros.
    //  that means 10*5 is valid but 1*05 is not valid
    //  check both solution one more time
    public static void main(String[] args) {
        type1();
        type2();
    }

    // same as previous here we are using the char array instead of raw string
    private static void type2() {
        String num = "105";
        int target = 5;

        List<String> answer = addOperators2(num, target);
        System.out.println(answer);
    }

    public static List<String> addOperators2(String num, int target) {
        char[] arr = num.toCharArray();
        List<String> res = new ArrayList<>();
        int n = num.length();
        char[] path = new char[n * 2 - 1];
        long cur = 0;
        for (int i = 0; i < n; i++) {
            char ch = arr[i];
            cur = cur * 10 + (ch - '0');
            path[i] = ch;
            addOperators2(n, i + 1, 0, cur, arr, path, i + 1, res, target);
            if (i == 0 && ch == '0') break;
        }
        return res;
    }

    private static void addOperators2(int n, int len, long sum, long pre, char[] num,
                                      char[] bucket, int idx, List<String> res, long target) {
        if (idx == n) {
            if (sum + pre == target) res.add(new String(bucket, 0, len));
            return;
        }
        int j = len + 1;
        long cur = 0;
        for (int i = idx; i < n; i++) {
            char ch = num[i];
            cur = cur * 10 + (ch - '0');
            bucket[len] = '+';
            bucket[j++] = ch;
            addOperators2(n, j, sum + pre, cur, num, bucket, i + 1, res, target);
            bucket[len] = '-';
            addOperators2(n, j, sum + pre, -cur, num, bucket, i + 1, res, target);
            bucket[len] = '*';
            addOperators2(n, j, sum, pre * cur, num, bucket, i + 1, res, target);
            if (i == idx && ch == '0') break;
        }
    }


    private static void type1() {
        String num = "105";
        int target = 5;

        List<String> answer = new ArrayList<>();
        addOperators1(0, 0, 0, "", num, target, answer);
        System.out.println(answer);
    }

    private static void addOperators1(int start, long prev, long total,
                                      String expr, String num, int target, List<String> answer) {
        // If we've reached the end of the string, check if the currentTotal equals the target value
        if (start == num.length()) {
            if (total == target) answer.add(expr);
            return;
        }
        long next = 0;
        // Try all possible splits of the remainder of the string
        for (int i = start; i < num.length(); i++) {
            // Skip numbers with leading zeros (unless the number itself is zero)
            if (i != start && num.charAt(start) == '0') break;
            // Parse the current number substring
            char ch = num.charAt(i);
            next = next * 10 + (ch - '0');

            // If this is the first operand (no operator before it)
            if (start == 0) {
                addOperators1(i + 1, next, next, expr + next, num, target, answer);
            } else {
                // Try adding the '+' operator
                addOperators1(i + 1, next, (total + next), (expr + "+" + next), num, target, answer);
                // Try adding the '-' operator
                addOperators1(i + 1, -next, (total - next), (expr + "-" + next), num, target, answer);
                // Try adding the '*' operator
                // if the seq is 2+2*3,so the prev is 2 and the total is 4,
                // but according to the bodmas rule, multiplication has higher precedence
                // we can do (2+2)*3 => we need to do like this 2+(2*3)
                addOperators1(i + 1,
                        (prev * next),
                        (total - prev) + (prev * next),
                        (expr + "*" + next),
                        num, target, answer);
            }
        }
    }



}
