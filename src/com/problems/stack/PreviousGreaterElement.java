package com.problems.stack;

import java.util.Stack;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=T5s96ynzArg&list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd&index=3
 * 
 * */
public class PreviousGreaterElement {
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// same as the previous type2
	// just a little compact,
	// we have reduced the unnecessary if checks
	private static void type3() {
		int[] arr = { 1, 3, 2, 4, 3, 3 };
		int n = arr.length;
		Stack<Integer> stack = new Stack<>();
		int[] answer = new int[n];
		// as we are checking the previous larger element, so we will start from the beginning
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && stack.peek() <= arr[i]) stack.pop();
			answer[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.add(arr[i]);
		}
		print(answer);
	}

	// same as the next greater element
	// as we will calculate the previous greater elements do we will
	// go from the start
	private static void type2() {
		int[] arr = { 1, 3, 2, 4, 3, 3 };
		int n = arr.length;
		Stack<Integer> stack = new Stack<>();
		int[] answer = new int[n];
		// as we are checking previous larger element so we will start from the begining
		for (int i = 0; i < n; i++) {
			if (stack.isEmpty()) {
				answer[i] = -1;
			} else {
				if (stack.peek() > arr[i]) {
					answer[i] = stack.peek();
				} else {
					while (!stack.isEmpty() && stack.peek() <= arr[i]) stack.pop();
					if (stack.isEmpty()) answer[i] = -1;
					else answer[i] = stack.peek();
				}
			}
			stack.add(arr[i]);
		}
		print(arr);
		print(answer);
	}

	// brute force
	private static void type1() {

	}

}