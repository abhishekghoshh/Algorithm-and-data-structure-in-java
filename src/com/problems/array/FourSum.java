package com.problems.array;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/4sum/
 * https://www.codingninjas.com/studio/problems/4sum_5713771
 * https://www.codingninjas.com/codestudio/problems/983605
 * 
 * Solution link
 * https://www.youtube.com/watch?v=eD95WRfh81c
 * https://www.youtube.com/watch?v=4ggF3tXIAp0&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=22
 * https://www.youtube.com/watch?v=EYeR-_1NRlQ
 *
 * https://takeuforward.org/data-structure/4-sum-find-quads-that-add-up-to-a-target-value/
 * https://neetcode.io/solutions/4sum
 * */

// Tags: Arrays, Two Pointers
public class FourSum {

	public static void main(String[] args) {
		type1();
		type2();
	}


	// todo better than the previous
	//  as the array is sorted we can skip one iteration at least
	//  also the array is sorted we can easily skip the duplicates
	// 2 pointer approach
	// time complexity O(n^3) + O(n*log(n))
	// space complexity O(1)
	private static void type2() {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		List<List<Integer>> answer = fourSum2(nums, target);
		System.out.println(answer);
	}

	private static List<List<Integer>> fourSum2(int[] nums, int target) {
		Arrays.sort(nums); // so that we can get the numbers one by one in increasing manner
		int n = nums.length;
		List<List<Integer>> answer = new ArrayList<>();
		for (int i = 0; i <= n - 4; i++) {
			int num1 = nums[i];
			// if previous num is also same then we will skip for the number
			if (i > 0 && num1 == nums[i - 1]) continue;
			// in the current series i, i+1, i+2 and i+3 is lowest
			// if the sum of then is greater than target, then any number on the right side will be able to make the target as well,
			// so we will break the loop, as there will be no more answer
			if ((long) num1 + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
			// in the current series  n-1, n-2 and n-3 is the highest
			// if the num + the sum of them is lesser than target then num will not able to make sum with others also,
			// so we will skip for the current num
			if ((long) num1 + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;
			// here we will choose the 2nd number in the four sum series
			for (int j = i + 1; j <= n - 3; j++) {
				int num2 = nums[j];
				// if previous num is also same then we will skip for the number
				// here we are checking with (i+1) as j is starting from (i+1), so has to be atleast i+2 to has a prev num
				if (j > i + 1 && num2 == nums[j - 1]) continue;
				// in the current series i, j, j+1 and j+1 is lowest
				// if the sum of then is greater than target, then any number on the right side will be able to make the target as well,
				// so we will break the loop, as there will be no more answer
				if ((long) num1 + num2 + nums[j + 1] + nums[j + 2] > target) break;
				// in the current series  n-1, n-2 is the highest
				// if the num1 + num2 + the sum of them is lesser than target then num will not able to make sum with others also,
				// so we will skip for the current num
				if ((long) num1 + num2 + nums[n - 2] + nums[n - 1] < target) continue;
				int left = j + 1;
				int right = n - 1;
				// now will start 2 pointer from here, it is similar to the three sum
				while (left < right) {
					long leftItem = nums[left];
					long rightItem = nums[right];
					long sum = num1 + num2 + leftItem + rightItem;
					if (sum == target) {
						answer.add(List.of(num1, num2, nums[left], nums[right]));
						//increasing left and skipping duplicates from the left
						while (left < n && nums[left] == leftItem) left++;
						//decreasing right and skipping duplicates from the right
						while (right > j && nums[right] == rightItem) right--;
					} else if (sum < target) {
						//increasing left and skipping duplicates from the left
						while (left < n && nums[left] == leftItem) left++;
					} else {
						//decreasing right and skipping duplicates from the right
						while (right > j && nums[right] == rightItem) right--;
					}
				}
			}
		}
		return answer;
	}

	// brute force approach
	// creating a custom data structure so that duplicates will get ruled out
	private static void type1() {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		List<List<Integer>> answer = fourSum1(nums, target);
		System.out.println(answer);
	}

	private static List<List<Integer>> fourSum1(int[] nums, int target) {
		int n = nums.length, low, high, mid;
		Arrays.sort(nums); // so that we can get the numbers one by one in increasing manner
		List<List<Integer>> ans = new ArrayList<>();
		Set<FourPoint> set = new HashSet<>();

		for (int i = 0; i < n; i++) {
			long num1 = nums[i];
			for (int j = i + 1; j < n; j++) {
				long num2 = nums[j];
				for (int k = j + 1; k < n; k++) {
					long num3 = nums[j];
					long rem = target - (num1 + num2 + num3);
					// now we will use 2 pointer approach here and try to find the remaining one number
					low = k + 1;
					high = n - 1;
					while (low <= high) {
						mid = low + (high - low) / 2;
						if (nums[mid] == rem) {
							set.add(new FourPoint(nums[i], nums[j], nums[k], nums[mid]));
							break;
						} else if (nums[mid] < rem) {
							low = mid + 1;
						} else {
							high = mid - 1;
						}
					}
				}
			}
		}
		for (FourPoint p : set) ans.add(List.of(p.x1, p.x2, p.x3, p.x4));
		return ans;
	}

	private record FourPoint(int x1, int x2, int x3, int x4) {

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FourPoint other = (FourPoint) obj;
			if (x1 != other.x1)
				return false;
			if (x2 != other.x2)
				return false;
			if (x3 != other.x3)
				return false;
			return x4 == other.x4;
		}

		public List<Integer> list() {
			return List.of(x1, x2, x3, x4);
		}
	}

}
