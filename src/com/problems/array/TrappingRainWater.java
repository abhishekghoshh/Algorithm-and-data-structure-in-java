package com.problems.array;

import java.util.Stack;

/*
 * problem link:
 * https://leetcode.com/problems/trapping-rain-water/description/
 * https://neetcode.io/problems/trapping-rain-water
 * https://www.naukri.com/code360/problems/630519
 *
 * Solution link :
 * https://www.youtube.com/watch?v=m18Hntz4go8&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=43
 * https://www.youtube.com/watch?v=FbGG2qpNp4U&list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd&index=10
 * https://www.youtube.com/watch?v=ZI2z5pq0TqA
 * 
 * https://takeuforward.org/data-structure/trapping-rainwater/
 * https://neetcode.io/solutions/trapping-rain-water
 * */

// Tags: Arrays, two pointers, Stack, Dynamic Programming
public class TrappingRainWater {

	public static void main(String[] args) {
		// updating from the brute force one by one
		type1();
		type2();
		type3();
		// using 2 pointer intuition from the previous types
		type4();
		// using stack
		type5();
	}

	// todo study one more time
	// using stack
	//
	// Let's think the water is something like this
	//     X
	//     X   X     X
	//   X X   X X   X
	// X X X X X X X X
	// 1 2 4 2 3 2 1 3
	// todo the intuition is something like this,
	//  we will try to maintain a decreasing stack
	//  we store water part by part
	//  if the series is like 11 10 6 2 5 9 2
	//  lets say we are on 6 and mid is 2, so we will take the minimum height which is 5
	//  so water on height 2 is (5-2)
	//  now on the stack is 11 10 6 5 and we are on 9 so, we will calculate for 6 as left start, 5 mid height and 9 as the right height
	//  now on the stack is 11 10 6 and we are on 9 so, we will calculate for 10 as left start, 6 mid height and 9 as the right height
	//  now on the stack 11 10 9, so the stack become decreasing
	//  one thing that we have noticed is we are adding water level by level
	private static void type5() {
		int[] height = {1, 2, 4, 1, 3, 2, 1, 3};
		int ans = trap5(height);
		System.out.println(ans);
	}

	private static int trap5(int[] height) {
		int total = 0;
		int n = height.length;
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < n; i++) {
			int h = height[i];
			// it means there is a bar in the left which is lower than the current bar
			// where the water could be stored
			while (!st.isEmpty() && h > height[st.peek()]) {
				int mid = st.pop();
				int midHeight = height[mid];
				// if there is nothing more in the left then water could not be stored
				// like we are on 4 and the series is 2 4 ..... so water could not store
				if (st.isEmpty()) break;
				// there is something in the left, and we are on 5 and the series is like 4 2 5....
				int leftStart = st.peek();
				int leftHeight = height[leftStart];
				// this will be the width from the current range
				int width = i - (leftStart + 1);
				// we will take the lesser height
				int minHeight = Math.min(h, leftHeight);
				// if we consider only current height and the left start height then water level
				// which will be added is (height - middleHeight) * width
				total += (minHeight - midHeight) * width;
			}
			st.push(i);
		}
		return total;
	}

	// todo check it later one more time
	// 2 pointer approach
	// time complexity O(n)
	// space complexity O(1)
	// We need a minimum of leftMax and rightMax.So if we take the case when
	// height[l]<=height[r] we increase l++, so we can surely say that there is a
	// block with a height more than height[l] to the right of l. And for the same
	// reason when height[r]<=height[l] we can surely say that there is a block to
	// the left of r which is at least of height[r]. So by traversing these cases
	// and using two pointers approach, the time complexity can be decreased without
	// using extra space
	// TODO study it one more time
	private static void type4() {
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		// int[] height = { 1, 2, 4, 1, 3, 2, 1, 3 };
		int area = trap4(height);
		System.out.println("water collected " + area);
	}

	private static int trap4(int[] height) {
		int n = height.length;
		int left = 0, right = n - 1;
		int leftMax = 0, rightMax = 0;
		int water = 0;
		while (left < right) {
			int leftH = height[left], rightH = height[right];
			// find the lower bound among height[left] or height[right]
			// water label will always be equal to the lowest among left and right height
			if (leftH <= rightH) {
				// height[left] is lower, so water is trapper on the left side, update leftmax or ans
				if (leftH > leftMax)
					leftMax = leftH;
					// height[i] is less than leftmax so water can be stored
				else
					water += (leftMax - leftH);
				left++;
			} else {
				// height[right] is lower, so water is trapper on the right side, update rightmax or ans
				if (rightH > rightMax)
					rightMax = rightH;
					// height[i] is less than rightmax so water can be stored
				else
					water += (rightMax - rightH);
				right--;
			}
		}
		return water;
	}

	// time complexity O(2n)
	// space complexity O(n)
	// exactly similar to the previous solution
	// just for we are not creating any rightMax array
	// instead on that loop we are calculating the are
	private static void type3() {
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		// int[] height = { 1, 2, 4, 1, 3, 2, 1, 3 };
		int areaSum = trap3(height);
		System.out.println("water collected " + areaSum);
	}

	private static int trap3(int[] height) {
		int n = height.length;
		int max, water = 0;
		int[] leftBoundary = new int[n];
		max = height[0];
		// start from index 1 we will go till
		for (int i = 1; i < n - 1; i++) {
			leftBoundary[i] = max;
			max = Math.max(max, height[i]);
		}
		// now we will calculate the right side boundary and also calculate the water on each height
		int rightMax = height[n - 1];
		// we will go from n-2 to 1
		for (int i = n - 2; i >= 1; i--) {
			int h = height[i];
			// calculating the right max
			rightMax = Math.max(rightMax, h);
			// if the current height is either greater than or equal to left boundary or right boundary then we will skip
			if (h >= leftBoundary[i] || h >= rightMax) continue;
			// water level will be min height of the left side and right side
			int minHeight = Math.min(leftBoundary[i], rightMax);
			// we will add the water
			water += (minHeight - h);
		}
		return water;
	}

	// prefix and suffix max array
	// time complexity O(3n)
	// space complexity O(2n)
	// on every index we are calculating it left the highest wall and right highest wall
	// the water height on each block will be equal to the minimum of left highest and
	// right highest wall
	// there will be no water on 0th wall and n-1th wall
	private static void type2() {
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		// int[] height = { 1, 2, 4, 1, 3, 2, 1, 3 };

		int areaSum = trap2(height);
		System.out.println("water collected " + areaSum);
	}

	private static int trap2(int[] height) {
		int n = height.length;
		int water = 0;
		int max = Integer.MIN_VALUE;
		// calculating the left side boundary for every i
		int[] leftBoundary = new int[n];
		for (int i = 0; i < n; i++) {
			leftBoundary[i] = max;
			max = Math.max(max, height[i]);

		}
		max = Integer.MIN_VALUE;
		// calculating the right side boundary
		int[] rightBoundary = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			rightBoundary[i] = max;
			max = Math.max(max, height[i]);
		}

		for (int i = 1; i < n - 1; i++) {
			int currH = height[i];
			// if the current height is either greater than or equal to left boundary or right boundary then we will skip
			if (currH >= leftBoundary[i] || currH >= rightBoundary[i]) continue;
			int minHeight = Math.min(leftBoundary[i], rightBoundary[i]);
			water += (minHeight - currH);
		}
		return water;
	}


	// todo brute force approach
	//  for every wall we are calculating its left max height and right max height
	//  there will be no water on 0th wall and n-1th wall
	// O(n^2) time complexity
	private static void type1() {
		int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
		// int[] height = { 1, 2, 4, 1, 3, 2, 1, 3 };
		int areaSum = trap1(height);
		System.out.println("water collected " + areaSum);
	}

	private static int trap1(int[] height) {
		int n = height.length;
		int water = 0;
		// we will start from 1 and end on n-2
		for (int i = 1; i < n - 1; i++) {
			// calculating the left side max height
			int leftH = Integer.MIN_VALUE;
			for (int j = i - 1; j >= 0; j--) {
				if (height[j] > leftH) {
					leftH = height[j];
				}
			}
			// calculating right side max height
			int rightH = Integer.MIN_VALUE;
			for (int j = i + 1; j < n; j++) {
				if (height[j] > rightH) {
					rightH = height[j];
				}
			}
			// water stored will be min height * current height
			if (height[i] < Math.min(leftH, rightH)) {
				water += Math.min(leftH, rightH) - height[i];
			}
		}
		return water;
	}

}
