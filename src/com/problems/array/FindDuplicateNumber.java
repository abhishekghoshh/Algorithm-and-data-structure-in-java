package com.problems.array;

import java.util.Arrays;

/*
 * Problem link :
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 * https://neetcode.io/problems/find-duplicate-integer
 * https://www.naukri.com/code360/problems/1112602
 *
 * Solution link :
 * https://www.youtube.com/watch?v=32Ll35mhWg0&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=1
 * https://www.youtube.com/watch?v=wjYnzkAhcNk
 *
 * https://takeuforward.org/data-structure/find-the-duplicate-in-an-array-of-n1-integers/
 * https://neetcode.io/solutions/find-the-duplicate-number
 * */
public class FindDuplicateNumber {

	/*
	 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
	 *
	 * 1 <= n <= 105
	 * nums.length == n + 1
	 * 1 <= nums[i] <= n
	 * */
	public static void main(String[] args) {
		type0();

		type1(); // using sort then linear scan

		type2(); // using boolean or int array
		type3();

		type4(); // todo using swap sort approach try to discuss this in the interview

		type5(); // using fast and slow pointer nice but do not discuss it
	}

	// todo check again linked list cycle approach
	// it's same as cycle detection in linked list
	// we will take two pointer slow and fast
	// and move them by one and two
	// there will always be collision as for some point nums[i]=nums[j]
	// let say slow and fast pointer moves c+ml+x and c+nl+x
	// where c is common space, m and n is rotation taken before collision
	// l is the cycle length
	// x is collision distance from starting point of cycle
	// and let's say slow pointer goes d distance before collision
	// then fast pointer will be going 2d distance before collision
	// 2d-d = (c+nl+x) - (c+ml+x) => d = l*(n-m)
	// so d is multiple of cycle length
	// l*(n-m) = c+ml+x => c+x = l*(n-2m)
	// so c+x is also multiple of cycle length
	// that means after collision point if we go to x distance
	// then we will obviously get the starting of the cycle
	// which is also the duplicate number
	// time complexity of this more than O(n)
	// as it may rotate some cycles
	private static void type5() {
		// int nums[] = { 1, 3, 4, 2, 8, 6, 5, 9, 8, 10, 11 };
		int[] nums = {1, 3, 4, 2, 2};
		int slow = findDuplicate6(nums);
		System.out.println("Duplicate element is " + slow);
	}

	private static int findDuplicate6(int[] nums) {
		int slow, fast;
		slow = nums[0];
		fast = nums[0];
		while (true) {
			slow = nums[slow];
			fast = nums[nums[fast]];
			if (slow == fast) {
				break;
			}
		}
		// at this point we detect the cycle
		slow = nums[0];
		// now slow points to first
		// fast points to collision
		// now move both pointers to one step
		// their collision point will be duplicate point
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}

	// todo check again swap sort without using extra space
	// given array must be mutable
	private static void type4() {
		int[] nums = { 1, 3, 4, 2, 2 };
		int ans = findDuplicate5(nums);
		System.out.println("Duplicate element is " + ans);
	}

	private static int findDuplicate5(int[] nums) {
		int i = 0;
		int n = nums.length;
		while (i < n) {
			int num = nums[i];
			// if then current index hold the current i+1 then it is right
			// then we will go to the next index
			if (num == i + 1) {
				i++;
				continue;
			}
			// ideally num should be in num-1 index but if num-1 already has num then num is duplicate
			if (nums[num - 1] == num) {
				return num;
			} else {
				nums[i] = nums[num - 1];
				nums[num - 1] = num; // now num is in its correct place
			}
		}
		return -1;
	}

	// using set with extra space on single iteration
	private static void type3() {
		int[] nums = { 1, 3, 4, 2, 2 };
		int ans = findDuplicate4(nums);
		System.out.println("Duplicate element is " + ans);
	}

	private static int findDuplicate4(int[] nums) {
		int n = nums.length;
		boolean[] set = new boolean[n + 1];
		for (int num : nums) {
			if (set[num]) return num;
			set[num] = true;
		}
		return -1;
	}

	// count sort using extra space on a single iteration
	private static void type2() {
		int[] nums = { 1, 3, 4, 2, 2 };
		int duplicateElement = findDuplicate3(nums);
		System.out.println("Duplicate element is " + duplicateElement);
	}

	private static int findDuplicate3(int[] nums) {
		int n = nums.length;
		int[] freq = new int[n + 1];
		int duplicateElement = 0;
		for (int num : nums) {
			freq[num]++;
			if (freq[num] > 1) return num;
		}
		return duplicateElement;
	}

	/// sort the array and check linear
	// time complexity O(n + n*log(n))
	private static void type1() {
		int[] nums = { 1, 3, 4, 2, 2 };
		int ans = findDuplicate2(nums);
		System.out.println(ans);
	}

	private static int findDuplicate2(int[] nums) {
		int length = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i < length - 1; i++) {
			if (nums[i] == nums[i + 1]) return nums[i];
		}
		return -1;
	}

	// brute force approach o(n^2)
	private static void type0() {
		int[] nums = { 1, 3, 4, 2, 2 };
		int ans = findDuplicate1(nums);
		System.out.println(ans);
	}

	private static int findDuplicate1(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (nums[i] == nums[j]) return nums[i];
			}
		}
		return -1;
	}

}
