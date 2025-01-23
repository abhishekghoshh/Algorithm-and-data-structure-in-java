package com.problems.stack;

import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 * https://neetcode.io/problems/largest-rectangle-in-histogram
 * https://www.naukri.com/code360/problems/1058184
 * 
 * Solution link :
 * Aditya verma
 * https://www.youtube.com/watch?v=J2X70jj_I1o&list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd&index=7
 * 
 * Striver
 * https://www.youtube.com/watch?v=X0X6G-eWgQ8&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=82
 * https://www.youtube.com/watch?v=jC_cWLy7jSI&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=83
 * 
 * https://takeuforward.org/data-structure/area-of-largest-rectangle-in-histogram/
 *
 * Neetcode
 * https://www.youtube.com/watch?v=zx5Sw9130L0
 * https://neetcode.io/solutions/largest-rectangle-in-histogram
 * */

public class MaximumAreaOfHistogram {


	// todo for every index, we will check how much the current height can spread out
	//  let's take the heights as 1 4 2 6 3 1,
	//  and we are at 2 so in the left will spread till 4 and in right till 3, which make the total are 2 * 4 => 8
	public static void main(String[] args) {
		type1();

		// creating left range and right range so that we can find how much spread the current index height can have
		type2();
		type3();

		// on a single pass here we are counting the range for the current index
		type4();
		type5();
	}

	// Same as the previous one
	// instead of using stack
	// here we are using the array as stack
	private static void type5() {
		int[] histogram = { 1, 3, 2, 4, 3, 5, 3 };
		int n = histogram.length;
		int max = 0;
		int[] stack = new int[n + 1];
		int top = -1, height, width;
		for (int i = 0; i <= n; i++) {
			while (top != -1 && (i == n || histogram[stack[top]] > histogram[i])) {
				height = histogram[stack[top--]];
				width = (top == -1) ? i : i - stack[top] - 1;
				max = Math.max(max, height * width);
			}
			stack[++top] = i;
		}
		System.out.println("Max area of histogram is " + max);
	}

	// Time complexity O(2n)
	// space complexity O(n)
	// intuition is When we traverse the array by finding the next greater element,
	// we found that some elements were inserted into the stack which signifies that
	// after them the smallest element is themselves
	private static void type4() {
		int[] histogram = { 1, 3, 2, 4, 3, 5, 3 };
		int max = largestRectangleArea3(histogram);
		System.out.println("Max area of histogram is " + max);
	}

	private static int largestRectangleArea3(int[] histogram) {
		int n = histogram.length;
		int max = 0, height, width;
		Stack<Integer> stack = new Stack<>();
		// our basic approach was to find the barrier for each index
		// that means if we current height is 4 then on left and right we will find a
		// height lesser than 4
		// so we will loop from 0 to n
		// if we look closely in stack we are storing the heights(index) increasingly
		// suppose the heights in stack is 1 4 6 8
		// so for height 8 left barrier is 6
		// for height 6 the bound is 4 and so on
		// now the current height is 7 and top of the stack is 8
		// so we will pop here as we have found the boundary for 8
		// left boundary is 6 and right boundary is 7
		// so for height 8 the width will be (7th index - 6th index -1)
		// we will pop the top of the stack
		// so now the stack have 1 4 6 and current height is 7
		// we can push the height
		// we will only push to the stack when histogram[stack.peek()] <= histogram[i]
		// when it reaches to n then we will pop from the stack and compute the area
		for (int i = 0; i <= n; i++) {
			while (!stack.isEmpty() && (i == n || histogram[stack.peek()] > histogram[i])) {
				// the current considered height is histogram[stack.pop()]
				height = histogram[stack.pop()];
				// as histogram[stack.peek()] > histogram[i])
				// so the right height barrier is i
				// and the left height barrier is top of the stack
				// if the stack is empty then that mean in left there is no barrier
				// that means in left there is either all elements greater than or equal to
				// current height so width will be i
				width = (stack.isEmpty()) ? i : i - stack.peek() - 1;
				max = Math.max(max, height * width);
			}
			stack.push(i);
		}
		return max;
	}

	// same as previous just here we will use array the stack
	private static void type3() {

	}

	// we will find previous smaller index and
	// an height can spread in left and right
	// till the heights in left or right are greater than equal to the current height
	// so we will find left and right smaller index with the help of stack
	private static void type2() {
		int[] histogram = { 1, 3, 2, 4, 3, 5, 3 };
		int max = largestRectangleArea2(histogram);
		System.out.println("Max area of histogram is " + max);
	}

	private static int largestRectangleArea2(int[] histogram) {
		int n = histogram.length, max = 0, area;
		// time complexity o(2n) and space complexity o(2n)
		// to compute left smaller index
		int[] left = new int[n];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i])
				stack.pop();
			left[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.add(i);
		}
		// time complexity o(2n) and space complexity o(2n)
		// to compute right smaller index
		int[] right = new int[n];
		stack.clear();
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i])
				stack.pop();
			right[i] = stack.isEmpty() ? n : stack.peek();
			stack.add(i);
		}
		// O(n) to compute this
		for (int i = 0; i < n; i++) {
			area = (right[i] - left[i] - 1) * histogram[i];
			max = Math.max(max, area);
		}
		return max;
	}

	// brute force approach with 2 loops
	private static void type1() {
		int[] histogram = { 1, 3, 2, 4, 3, 5, 3 };
		int max = largestRectangleArea1(histogram);
		System.out.println("Max area of histogram is " + max);
	}

	private static int largestRectangleArea1(int[] histogram) {
		int length = histogram.length;
		int max = 0, area, left, right;
		for (int i = 0; i < length; i++) {
			left = right = i;
			while (left > 0 && histogram[left - 1] > histogram[i])
				left--;
			while (right < length - 1 && histogram[right + 1] > histogram[i])
				right++;
			area = (right - left + 1) * histogram[i];
			max = Math.max(max, area);
		}
		return max;
	}
}
