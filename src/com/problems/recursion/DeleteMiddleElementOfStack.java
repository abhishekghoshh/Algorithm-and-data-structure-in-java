package com.problems.recursion;

import java.util.Stack;
/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/delete-middle-element-of-a-stack/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=oCcUNRMl7dA&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=8
 * 
 * */
public class DeleteMiddleElementOfStack {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// recursive type2
	private static void type3() {
		Stack<Integer> stack = stack(5, 2, 7, 1, 6, 9, 8, 3, 4);
		// we will pass three-parameter
		// stack, current index,stack size
		deleteMiddle2(stack, 1, stack.size());
		System.out.println(stack);
	}

	private static void deleteMiddle2(Stack<Integer> stack, int current, int size) {
		if (stack.isEmpty()) return;
		int item = stack.pop();
		deleteMiddle2(stack, current + 1, size);
		if (current != (size / 2) + 1) stack.push(item);
	}

	// Iteratively
	private static void type2() {
		Stack<Integer> stack = stack(5, 2, 7, 1, 6, 9, 8, 3, 4);
		int n = stack.size();
		Stack<Integer> temp = new Stack<>();
		// we will add the top n/2 elements to the temporary stack
		while (stack.size() > (n / 2) + 1)
			temp.push(stack.pop());
		// popping the middle element
		stack.pop();
		// again adding the elements from the temp stack to the original stack
		while (!temp.isEmpty()) stack.push(temp.pop());
		System.out.println(stack);
	}

	// recursive
	// recursively reach to the n/2 +1 th element and pop it
	private static void type1() {
		Stack<Integer> stack = stack(5, 2, 7, 1, 6, 9, 8, 3, 4);
		// we will pass three-parameter stack, current index,stack size
		deleteMiddle1(stack, stack.size(), stack.size());
		System.out.println(stack);
	}

	private static void deleteMiddle1(Stack<Integer> stack, int i, int n) {
		// if current index == size/2 +1, so it is the element that we have to pop
		if (i == (n / 2) + 1) {
			stack.pop();
			return;
		}
		// we will first pop the last, and we will assume
		// the function will delete the middle element then we will again add the element
		int item = stack.pop();
		deleteMiddle1(stack, i - 1, n);
		stack.push(item);
	}

	private static Stack<Integer> stack(int... arr) {
		Stack<Integer> stack = new Stack<>();
		for (int item : arr) {
			stack.push(item);
		}
		return stack;
	}

}
