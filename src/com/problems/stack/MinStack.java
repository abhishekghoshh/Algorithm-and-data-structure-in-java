package com.problems.stack;

import java.util.Objects;
import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/min-stack/submissions/
 * https://www.naukri.com/code360/problems/min-stack_3843991
 * https://neetcode.io/problems/minimum-stack
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=asf9P2Rcopo&list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd&index=10
 * https://www.youtube.com/watch?v=V09NfaGf2ao
 * https://www.youtube.com/watch?v=qkLl7nAwDPo
 * 
 * https://takeuforward.org/data-structure/implement-min-stack-o2n-and-on-space-complexity/
 * https://neetcode.io/solutions/min-stack
 * */
public class MinStack {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// TODO optimized approach see it again
	private static void type3() {
		MinStack3 stack = new MinStack3();
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(3);
		stack.push(3);
		System.out.println("current stack is " + stack);
		System.out.println("popped element " + stack.pop());
		System.out.println("popped element " + stack.pop());
		System.out.println("popped element " + stack.pop());
		System.out.println("current stack is " + stack);
	}

	public static class MinStack3 {


		// we will store the elements in the stack,
		// and we will also use one variable for storing the current minimum element
		// we will derive one logic to store the previous logic
		private final Stack<Long> stack;
		private Long min;

		// space complexity is O(n)
		// if the item is lesser than the current min then we will push (2*item - min)
		// suppose our current min is x and new item is x-1, so will add (2*(x-1) - x) => which is x-2
		// which will be our corrupted value, so the newly added item will always be lesser the current min
		// as our current min is x-1
		// so while popping when we encounter any value lesser than the current min then
		// we should know that it is the curred value that we have stored earlier
		// so after popping the current min we have to again find the previous min
		// that we can find easily by doing this=> (2*(x-1) - (x-2)) => x
		public MinStack3() {
			stack = new Stack<>();
			min = Long.MAX_VALUE;
		}

		// time complexity O(1)
		public void push(int value) {
			// if the stack is empty then we can add the item to stack and that element will also be the minimum
			Long val = (long) value;
			if (stack.isEmpty()) {
				min = val;
				stack.add(val);
				return;
			}
			if (val < min) {
				// if the element is lesser than the current min
				// then we will store the (2 * val - previous min) into the stack
				// (2 * val - previous min) will always be lesser than equal to the current min
				// which will be our indicator to know that the current top of the stack is the min element
				// (2 * val - min) is not the actual value, it is a corrupted value
				long corruptedVal = 2 * val - min;
				stack.add(corruptedVal);
				// updating min with the current val
				min = val;
			} else {
				// if the element is greater than the current min
				// then we can just directly add the element to the stack
				stack.add(val);
			}
		}

		public int pop() {
			if (stack.isEmpty()) return Integer.MIN_VALUE;
			long val = stack.pop();
			// if the current top is lesser than the min element then it is the corrupted value
			// so, we will try to find our previous min
			if (val < min) {
				long oldMin = min;
				min = 2 * min - val;
				val = oldMin;
			}
			return (int) val;
		}

		public int top() {
			Long val = stack.peek();
			// if val is lesser than the min then it is the corrupted value, we should return the min
			if (val < min) return min.intValue();
			return val.intValue();
		}

		public int peek() {
			return !stack.isEmpty() ? stack.peek().intValue() : Integer.MIN_VALUE;
		}

		public int min() {
			return !stack.isEmpty() ? min.intValue() : Integer.MIN_VALUE;
		}

		@Override
		public String toString() {
			return "{\"stack\": " + stack + ", \"min\": " + min + "}";
		}
	}

	// almost a brute force approach
	// rather creating 2 stacks we can just push pairs in the stack [val,min]
	private static void type2() {
		MinStack2 stack = new MinStack2();
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(3);
		stack.push(3);
		System.out.println("current stack is " + stack);
		stack.pop();
		System.out.println("popped element " + stack.top());
		stack.pop();
		System.out.println("popped element " + stack.getMin());
		stack.pop();
		System.out.println("popped element " + stack.top());
		System.out.println("current stack is " + stack);
	}

	static class MinStack2 {
		final Stack<int[]> stack;

		public MinStack2() {
			stack = new Stack<>();
		}

		public void push(int val) {
			if (stack.isEmpty()) {
				stack.push(new int[]{val, val});
			} else {
				int min = Math.min(val, stack.peek()[1]);
				stack.push(new int[]{val, min});
			}
		}

		public void pop() {
			stack.pop();
		}

		public int top() {
			return stack.peek()[0];
		}

		public int getMin() {
			return stack.peek()[1];
		}
	}

	// TODO there is an issue in this solution
	// we will use two stack
	// one for storing all the elements
	// another for storing the min elements only
	private static void type1() {
		MinStack1 stack = new MinStack1();
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(3);
		stack.push(3);
		System.out.println("stack is " + stack);
		stack.pop();
		stack.pop();
		stack.pop();
		System.out.println("stack is " + stack);
	}

	public static class MinStack1 {
		// we will use two stack
		// one for storing all the elements
		// another for storing the min elements only
		private final Stack<Integer> stack;
		private final Stack<Integer> minStack;

		// space complexity is O(2n)
		public MinStack1() {
			stack = new Stack<>();
			minStack = new Stack<>();
		}

		// time complexity O(1)
		public int top() {
			return !stack.isEmpty() ? stack.peek() : Integer.MIN_VALUE;
		}

		// time complexity O(1)
		public int getMin() {
			return !minStack.isEmpty() ? minStack.peek() : Integer.MIN_VALUE;
		}

		// time complexity O(1)
		public void pop() {
			if (stack.isEmpty()) return;
			if (Objects.equals(stack.peek(), minStack.peek()))
				minStack.pop();
			stack.pop();
		}

		// time complexity O(1)
		public void push(int val) {
			if (minStack.isEmpty() || val <= minStack.peek())
				minStack.push(val);
			stack.push(val);
		}

		@Override
		public String toString() {
			return "{\"elements\": " + stack + ", \"min\": " + minStack + "}";
		}
	}
}