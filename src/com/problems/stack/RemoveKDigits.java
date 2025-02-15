package com.problems.stack;

import java.util.Stack;
/*
 * Problem link :
 * https://leetcode.com/problems/remove-k-digits/description/
 * https://www.naukri.com/code360/problems/remove-k-digits_1461221
 *
 * Solution link :
 * https://www.youtube.com/watch?v=cFabMOnJaq0
 * https://www.youtube.com/watch?v=3QJzHqNAEXs
 *
 * */
public class RemoveKDigits {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // todo same as type2 just optimized
    //  here we are mostly using the array as stack
    private static void type3() {
        String num = "1432219";
        int k = 3;
        String answer = removeKdigits3(num, k);
        System.out.println(answer);
    }

    private static String removeKdigits3(String num, int k) {
        int n = num.length();
        if (k == n) return "0";
        char[] arr = num.toCharArray();
        int n2 = n - k; // after removing k digits length will be
        // creating a stack with the char array
        char[] stack = new char[n];
        int top = -1;
        for (char ch : arr) {
            // we will only pop if k > 0 and the current character is lesser than the last
            while (k > 0 && top != -1 && stack[top] > ch) {
                k--;
                top--;
            }
            stack[++top] = ch;
        }
        // also we need to remove the front 0s
        int start = 0;
        // skipping the leading 0
        while (start < n2 && stack[start] == '0') start++;
        // as the stack is now basically a char array, so we can create the String directly from that array
        String ans = String.valueOf(stack, start, n2 - start);
        // if the ans is empty then we will return "0"
        return ans.isEmpty() ? "0" : ans;
    }

    // todo optimized solution using stack,
    // we certainly know one thing if the integers are 2 5 4 5 9 1
    // then the lowest number can be possible with these integers, is 123559
    // todo the lowest number will be monotonically increasing,
    // so we go from left to right and try to maintain stack of monotonic increasing
    // if the number is 254591 and we need to choose between first 5 or first 4
    // then we will certainly choose the 5 to exclude
    // because no matter what will be the remaining number
    // if we exclude 5 it will be 24 and if we exclude 4 it will be 25
    // todo we go from left to right and maintain a monotonic stack
    private static void type2() {
        String num = "1432219";
        int k = 3;
        String answer = removeKdigits2(num, k);
        System.out.println(answer);
    }

    private static String removeKdigits2(String num, int k) {
        int n = num.length();
        if (k == n) return "0";
        Stack<Character> st = new Stack<>();
        // we will traverse the array, and try to maintain a monotonic increasing stack
        for (char ch : num.toCharArray()) {
            // we will only pop if k > 0 and the current character is lesser than the last
            while (k > 0 && !st.isEmpty() && st.peek() > ch) {
                k--;
                st.pop();
            }
            st.push(ch);
        }
        // if there is any remaining k then we will remove from the last
        // because we know that it is a monotonic increasing array and the highest item will be in the end
        while (k > 0 && !st.isEmpty()) {
            k--;
            st.pop();
        }
        // now our work is to build the number from stack
        // either we can create an array and then convert that into string, or we can use a string builder
        // currently the string is in reverse
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
            sb.append(st.pop());
        // we need to remove the front 0 from the string but the string reversed, so we will remove from the last
        while (!sb.isEmpty()) {
            int len = sb.length();
            char last = sb.charAt(len - 1);
            if (last != '0') break; // last character is not 0 then we will break
            sb.deleteCharAt(len - 1); // else we will delete
        }
        return sb.isEmpty() ? "0" : sb.reverse().toString(); // finally we will reverse and return
    }

    // brute force approach
    // find all the combinations possibles for the string
    // excluding k characters
    private static void type1() {

    }
}
