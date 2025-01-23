package com.problems.array;

import static com.util.PrintUtl.print;
import static com.util.ArrayUtil.swap;

/*
 * Problem link
 * https://leetcode.com/problems/next-permutation/
 * https://leetcode.com/problems/next-greater-element-iii/
 * https://www.codingninjas.com/codestudio/problems/893046
 *
 * solution link
 * https://www.youtube.com/watch?v=JDOXKqF60RQ&t=1s
 * https://www.youtube.com/watch?v=LuLCLgMElus&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=9
 *
 * https://takeuforward.org/data-structure/next_permutation-find-next-lexicographically-greater-permutation/
 * */
public class NextPermutation {

	// a number is linearly increasing from the back to a certain point and we have
	// to find that point
	// In 13542 -> break point is 3 that mean we can divide the number by 13 - 542.
	// now we have to change this prefix such a way it will be the minimum biggest
	// number like for 13 the choices are 14 and 15
	// So we will choose 14
	// as from the right side it is increasing so the right most element for
	// which a[index]<a[i] will satisfy this
	// then we will swap the numbers a[index] and a[i]
	// so now the prefix is greater
	// so my number is now 14 - 532
	// as we have now increased the prefix so the for the remaining part will be as
	// minimum as possible for the next element
	// so previously it was increasing from the back that's it was highest so if we
	// reverse the number then it will be become the lowest
	// so the number will become 14 - 235
	public static void main(String[] args) {
		int[] nums = { 5, 4, 3, 2, 1 };
		print(nums);
		int n = nums.length;
		// first traverse from the right and find the first element where a[i]<a[i+1]
		// like 14532 -> here 4 < 5
		int i = n - 2;
		while (i >= 0) {
			if (nums[i] < nums[i + 1])
				break;
			i--;
		}
		if (i == -1) {// the number is a decreasing number like 54321
			reverse(nums, 0);
		} else {
			// first traverse from the right and find the first element where a[breakingPoint] < a[swappingIndex]
			int swappingIndex = n - 1;
			while (swappingIndex > i) {
				if (nums[i] < nums[swappingIndex]) {
					break;
				}
				swappingIndex--;
			}
			swap(nums, i, swappingIndex);
			reverse(nums, i + 1);
		}
		print(nums);
	}

	private static void reverse(int[] nums, int i) {
		int start = i;
		int last = nums.length - 1;
		while (start < last) {
			swap(nums, start, last);
			start++;
			last--;
		}
	}

}
