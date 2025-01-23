package com.problems.array;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/set-mismatch/description/
 * https://www.interviewbit.com/problems/repeat-and-missing-number-array/
 * https://www.naukri.com/code360/problems/873366
 * https://www.naukri.com/code360/problems/missing-and-repeating-numbers_6828164
 *
 * Solution link :
 * https://www.youtube.com/watch?v=2D0D8HE6uak
 * https://www.youtube.com/watch?v=5nMGY4VUoRY&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=4
 * https://www.youtube.com/watch?v=d-ulaeRBA64
 *
 * https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/
 * https://neetcode.io/solutions/set-mismatch
 * */

// Tags : Arrays, Hashing, Bit manipulation
public class RepeatAndMissingNumber {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
	}


	// best and optimized approach
	// important
	// use XOR approach
	// this is based on X^X = 0 and X^0 = X
	// lets say a is missing and b is duplicate
	// now XOR(nums) ^ XOR(1..N)
	// then it will be XOR of a and b
	// as all the numbers of nums will be cancelled out with XOR(1..N)
	// and in nums there will two b which will also cancelled out
	// the value will only be a^b remaining from XOR(1..N)
	private static void type5() {
		int[] nums = {3, 1, 2, 5, 3};
		int[] ans = findErrorNums5(nums);
		print(ans);
	}

	// todo check it once more
	public static int[] findErrorNums5(int[] nums) {
		int missing = 0;
		int repeat = 0;
		int xor = 0;
		int n = nums.length;

		// Get the xor of all array elements and numbers from 1 to n
		for (int i = 0; i < n; i++) xor = xor ^ nums[i] ^ (i + 1);
		// Get the rightmost set bit from xor
		// ~(xor - 1) is same as -xor
		int rightestSetBit = xor & -xor;
		// like for 12 => 1100 it's 4 or 100
		// for 17 => 10001 it's 1
		// for 14 => 1110 it's 2 or 10

		/*
		 * Now divide elements into two sets by comparing rightmost set bit of xor1 with
		 * the bit at the same position in each element. Also, get XORs of two sets. The
		 * two XORs are the output elements. The following two for loops serve the
		 * purpose
		 */
		for (int i = 0; i < n; i++) {
			// we are checking nums[i] in which bucket
			if ((nums[i] & rightestSetBit) != 0) missing = missing ^ nums[i];
			else repeat = repeat ^ nums[i];
			// we are checking 1..N are in which bucket
			if (((i + 1) & rightestSetBit) != 0) missing = missing ^ (i + 1);
			else repeat = repeat ^ (i + 1);
		}
		// NB! numbers can be swapped, maybe do a check
		int count = 0;
		for (int num : nums) {
			if (missing == num) count++;
		}
		if (count != 0) {
			int temp = missing;
			missing = repeat;
			repeat = temp;
		}
		return new int[]{repeat, missing};
	}

	// using mathematics and linear algebra
	// we know summation of n and summation of n^2 by formula
	// we have all the numbers
	// lets say a is missing and b is duplicate
	// so a-b = sum(arr) - sum(1..n)
	// and a^2 -b^2 = sum(arr^2) - sum(a^2 .. n^2)
	// so (a+b)*(a-b) = sum(arr^2) - sum(a^2 .. n^2)
	// from that we can find a+b
	// now we have a+b and a-b, we can easily find a and b
	// only limitation is that
	// we are doing square there might be a chance of overflow
	private static void type4() {
		int[] nums = {3, 1, 2, 5, 3};
		int[] ans = findErrorNums4(nums);
		print(ans);

	}

	public static int[] findErrorNums4(int[] nums) {
		int n = nums.length;
		int calculatedSum = 0;
		int calculatedSquaredSum = 0;
		for (int item : nums) {
			calculatedSum = calculatedSum + item;
			calculatedSquaredSum = calculatedSquaredSum + item * item;
		}
		int sum = n * (n + 1) / 2;
		int squaredSum = n * (n + 1) * (2 * n + 1) / 6;
		int difference = sum - calculatedSum;
		int addition = (squaredSum - calculatedSquaredSum) / difference;
		int missing = (addition + difference) / 2;
		int repeat = (addition - difference) / 2;
		return new int[]{repeat, missing};
	}

	// if the list is modifiable then we can use swap sort,
	// then we don't have to use extra space
	private static void type3() {
		int[] nums = {3, 1, 2, 5, 3};
		int[] ans = findErrorNums3(nums);
		print(ans);
	}

	// swap sort is also a good technique
	// todo check it once more
	public static int[] findErrorNums3(int[] nums) {
		int n = nums.length;
		int i = 0;
		int repeat = 0, missing = 0;
		while (i < n) {
			if (nums[i] == i + 1 || nums[nums[i] - 1] == nums[i]) {
				i++;
			} else {
				// swap i and nums[i]
				int temp = nums[i];
				nums[i] = nums[temp - 1];
				nums[temp - 1] = temp;
			}
		}
		for (i = 0; i < n; i++) {
			if (i + 1 != nums[i]) {
				repeat = nums[i];
				missing = i + 1;
			}
		}
		return new int[]{repeat, missing};
	}

	// then we can use one extra array to store the frequency
	// after storing the frequency
	// we can iterate through the frequency array and find missing and duplicate
	private static void type2() {
		int[] nums = {3, 1, 2, 5, 3};
		int[] ans = findErrorNums2(nums);
		print(ans);
	}

	public static int[] findErrorNums2(int[] nums) {
		int n = nums.length;
		int[] freq = new int[n + 1];
		// counting the freq first
		for (int num : nums) {
			freq[num]++;
		}
		int repeat = 0, missing = 0;
		// then from the freq array we will find out the repeat and the missing element
		for (int i = 1; i <= n; i++) {
			if (freq[i] < 1) {
				missing = i;
			}
			if (freq[i] > 1) {
				repeat = i;
			}
		}
		return new int[]{repeat, missing};
	}

	// Do not think of this approach ever
	// brute force approach
	// time complexity O(2n^2)
	// space complexity O(1)
	private static void type1() {
		int[] nums = {3, 1, 2, 5, 3};
		int[] ans = findErrorNums1(nums);
		print(ans);
	}

	public static int[] findErrorNums1(int[] nums) {
		int n = nums.length;
		int repeat = 0;
		int missing = 0;
		// finding the repeat number
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (nums[i] == nums[j]) {
					repeat = nums[i];
					break;
				}
			}
		}
		// finding the missing number
		for (int i = 1; i <= n; i++) {
			boolean found = false;
			for (int num : nums) {
				if (num == i) {
					found = true;
					break;
				}
			}
			if (!found) {
				missing = i;
				break;
			}
		}
		return new int[]{repeat, missing};
	}

}
