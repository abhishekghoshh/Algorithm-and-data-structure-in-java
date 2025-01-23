package com.problems.string;

/*
 * Problem link:
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=mgQ4O9iUEbg
 *
 * https://neetcode.io/solutions/minimum-remove-to-make-valid-parentheses
 */

// Tags: Array, String, Stack
public class MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // first we will store all the invalid indices in an array
    // for every opening parenthesis we will store (i+1)
    // and for closing parenthesis if there is already a opening parenthesis or not
    // if yes then we will set the previous index as 0 to remove that (i+1)
    // else there might be 2 conditions
    // either it the first closing parenthesis or there is already closing parenthesis
    // in both we will store -(i+1) to mark it is a closing parenthesis
    // todo we could use a stack but we used array as stack
    private static void type2() {
        String s = "lee(t(c)o)de)";
        String ans = minRemoveToMakeValid2(s);
        System.out.println(ans);
    }


    public static String minRemoveToMakeValid2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n == 1 && (arr[0] == '(' || arr[0] == ')')) return "";
        // storing all the invalid indices
        int[] invalidParenthesis = new int[n];
        int top = 0;
        for (int i = 0; i < n; i++) {
            char ch = arr[i];
            // if the current character is ( then we will add it to the invalidParenthesis
            // and later if there is any ) then we will make this cell as 0 again to remove the index
            if (ch == '(') {
                invalidParenthesis[top++] = (i + 1);
            } else if (ch == ')') {
                // if the top is 0 (no opening parenthesis present) or the last parenthesis is also a closing
                // then we will add the index -(i+1) to the invalidParenthesis
                if (top == 0 || invalidParenthesis[top - 1] < 0)
                    invalidParenthesis[top++] = -(i + 1);
                else // remove the last cell
                    invalidParenthesis[--top] = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        top = 0;
        for (int i = 0; i < n; i++) {
            char ch = arr[i];
            int skippingIndex = invalidParenthesis[top];
            // if skippingIndex is 0, that means the array is now empty we can just add the character
            if (skippingIndex == 0) {
                sb.append(ch);
                continue;
            }
            // getting the actual index from that converted index (i+1) or -(i+1)
            skippingIndex = (skippingIndex > 0) ? (skippingIndex - 1) : (-skippingIndex - 1);
            // if the skippingIndex is the current index that means we will not store this character either ( or )
            // and update the top variable
            if (skippingIndex == i) {
                top++;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private static void type1() {

    }
}
