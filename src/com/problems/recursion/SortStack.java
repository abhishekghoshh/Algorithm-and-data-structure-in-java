package com.problems.recursion;

import java.util.Stack;

import static com.util.ArrayUtil.stack;
import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://www.naukri.com/code360/problems/sort-a-stack_985275
 * https://www.naukri.com/code360/problems/sort-stack_1229505
 * https://www.geeksforgeeks.org/problems/sort-a-stack/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=MOGBRkkOhkY&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=7
 * 
 * */

public class SortStack {


	public static void main(String[] args) {
		type1();
	}

	// it will sort the stack in the reverse sorted manner
	// that means the bigger element will be at the top
	// and the smaller element will be at the bottom
	private static void type1() {
		Stack<Integer> stack = stack(5, 2, 7, 1, 6, 9, 8, 3, 4);
		sort(stack);
		print(stack);
	}

	private static void sort(Stack<Integer> stack) {
		// if there is no element, then we will just return
		if (stack.isEmpty()) return;
		// the intuition is we will remove the last element then sort the stack
		// then will place the last element to its exact position
		int lastItem = stack.pop();
		sort(stack);
		push(stack, lastItem);
	}

	private static void push(Stack<Integer> stack, int item) {
		// if stack is empty, or the top element of the stack is lesser than the item,
		// we can add the element
		if (stack.isEmpty() || stack.peek() <= item) {
			stack.add(item);
			return;
		}
		// if the top element is bigger, then we will pop the element again call the push.
		// we are thinking that push will eventually push the item in its position
		// after placing the item then we are again pushing the last popped element
		int top = stack.pop();
		push(stack, item);
		stack.push(top);
	}
}
