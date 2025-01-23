package com.problems.dp;


/*
 * Problem link :
 * https://www.naukri.com/code360/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=-GtpxG6l_Mc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=10
 * https://www.youtube.com/watch?v=GS_OqZb2CWc
 *
 * https://takeuforward.org/data-structure/partition-set-into-2-subsets-with-min-absolute-sum-diff-dp-16/
 *
 */
public class MinimumSubsetSumDifference {

	// TODO if we consider only positive number then it is just a problem of DP target sum
	//  but if we have negative numbers to consider then we will need man in the middle algorithm
	// You are given an integer array nums of 2 * n integers.
	// You must partition nums into two arrays of length n to minimize the absolute difference of the sum of the arrays.
	// To partition nums, put each element of nums into one of the two arrays.
	// Return the minimum possible absolute difference.
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// TODO similar to previous type, only works with positive integer
	// same as the previous type but with space and time optimization
	private static void type4() {
		int[] nums = {76, 8, 45, 20, 74, 84, 28, 1};
		int n = nums.length;

		int sum = 0;
		for (int num : nums) sum += num;
		int half = sum / 2;

		// we have taken till half
		boolean[] prev = new boolean[half + 1];
		// we can always create zero sums with the empty subset, remaining cells will be false
		prev[0] = true;
		for (int i = 1; i <= n; i++) {
			boolean[] curr = new boolean[half + 1];
			// we can always create empty subset for sum 0
			curr[0] = true;
			for (int j = 1; j <= half; j++) {
				if (nums[i - 1] <= j) curr[j] = prev[j - nums[i - 1]] || prev[j];
				else curr[j] = prev[j];
			}
			prev = curr;
		}

		// there will be two sets s1 and s2, then difference will be abs(s2-s1)
		// s2 can be represented by (sum-s1), so abs(s2-s1) => abs(sum-2*s1)
		int diff = sum;
		for (int s1 = 1; s1 <= half; s1++)
			if (prev[s1])
				diff = Math.min(diff, sum - 2 * s1);
		System.out.println(diff);

	}


	// TODO only works with positive integer
	// intuition for this solution
	// we will follow the is sum possible solution and try to produce all the sum possible
	// here is the trick, the last row of the memo can give us all the sum possible
	// we will start with the total sum possible which is sum of all the elements
	// TODO also we could do 2 optimizations here
	//  as we see we only need half elements only so we can use the array to sum/2 +1
	//  and we only need one row to compute answer, we could use two 1D array like dp[] and prev[]
	//  to store current row and hold previous row
	private static void type3() {
		int[] nums = {76, 8, 45, 20, 74, 84, 28, 1};
		int n = nums.length;

		int sum = 0;
		for (int num : nums) sum += num;

		boolean[][] memo = new boolean[n + 1][sum + 1];
		// here n is 0, and for n equal to 0 we cannot make any element
		for (int i = 1; i <= sum; i++) memo[0][i] = false;
		// here the target sum is 0, so even with zero elements we can create an empty set
		for (int i = 0; i <= n; i++) memo[i][0] = true;

		// now we will fill up individual place
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= sum; j++)
				if (nums[i - 1] <= j)
					memo[i][j] = memo[i - 1][j - nums[i - 1]] || memo[i - 1][j];
				else
					memo[i][j] = memo[i - 1][j];


		// there will be two sets s1 and s2, then difference will be abs(s2-s1)
		// s2 can be represented by (sum-s1), so abs(s2-s1) => abs(sum-2*s1)
		int diff = sum;
		int half = sum / 2;
		for (int s1 = 1; s1 <= half; s1++)
			if (memo[n][s1])
				diff = Math.min(diff, sum - 2 * s1);
		System.out.println(diff);
	}

	// with memoization
	private static void type2() {
	}

	// with normal recursion
	private static void type1() {
	}
}
