package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/generate-parentheses/description/
 * https://neetcode.io/problems/generate-parentheses
 * https://www.geeksforgeeks.org/problems/generate-all-possible-parentheses/1
 * https://www.naukri.com/code360/problems/generate-all-parenthesis_920445
 * 
 * Solution link
 * https://www.youtube.com/watch?v=eyCj_u3PoJE&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=17
 * https://neetcode.io/solutions/generate-parentheses
 *
 * https://www.youtube.com/watch?v=s9fokUqJ76A
 * */
public class GenerateAllBalancedParenthesis {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// same as type1
	// we are nt using string buffer here
	// rather we are using an array
	// unlike the last one, here we will count the used opening and closing braces.
	// once the open and close counter is equal to the n, we will add the string to the answer list
	// also we will not use string builder this time.
	// a simple char array would be enough
	// we will place the brackets in (open+close) th position
	private static void type2() {
		int n = 3;
		char[] bucket = new char[2 * n];
		List<String> answer = new ArrayList<>();
		traverse(answer, bucket, 0, 0, n);
		System.out.println(answer);
	}

	private static void traverse(List<String> list, char[] bucket, int open, int close, int n) {
		// invalid case, we can return
		if (open > n) return;

		// this means we have balanced parenthesis
		if (open == n && close == n) {
			list.add(new String(bucket));
			return;
		}
		// placing the opening braces, we do not need to remove it
		// because it will be overwritten by closing braces in another function call
		bucket[open + close] = '(';
		traverse(list, bucket, open + 1, close, n);

		// we will place the closing brace in the same position
		// if there is at least one opening bracket greater than the closing bracket
		if (open > close) {
			bucket[open + close] = ')';
			traverse(list, bucket, open, close + 1, n);
		}
	}

	// using recursion and backtracking
	private static void type1() {
		int n = 3;
		List<String> answer = new ArrayList<>();
		StringBuilder bucket = new StringBuilder();
		// we will start with 0 braces
		// we will just count how much is remaining to use
		// at this point there is n opening and n closing parenthesis can be added
		traverse(n, n, bucket, answer);
		System.out.println(answer);
	}

	// open and close is the counter for the remaining opening and closing brackets
	private static void traverse(int open, int close, StringBuilder bucket, List<String> answer) {
		// if opening braces is less than zero, there is no possible way to make any balanced parenthesis
		// closingBracesLeft will never be less zero.
		// because we are adding close bracket solely depending on the number of the opening bracket
		// so, we will not check the closing brackets count here
		if (open < 0) return;

		// if both are zero, then it is a balancing parenthesis
		if (open == 0 && close == 0) {
			answer.add(bucket.toString());
			return;
		}

		// at any point of time we can add opening parenthesis
		bucket.append("(");
		traverse(open - 1, close, bucket, answer);
		bucket.deleteCharAt(bucket.length() - 1);

		// we can only add closing parenthesis when there is least one opening
		// parenthesis before; that means the count of remaining opening parenthesis is less
		// than the count of remaining closing parenthesis
		if (open < close) {
			bucket.append(")");
			traverse(open, close - 1, bucket, answer);
			bucket.deleteCharAt(bucket.length() - 1);
		}
	}

}
