package com.problems.dp;

import java.util.Arrays;

/*
 * Problem link:
 * https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1
 * https://www.naukri.com/code360/problems/number-of-subsets_3952532
 *
 * Solution link:
 * https://www.youtube.com/watch?v=F7wqWbqYn9g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=9
 * https://www.youtube.com/watch?v=ZHyb-A2Mte4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=18
 * 
 * https://www.codingninjas.com/codestudio/library/count-of-subsets-with-sum-equal-to-x
 * https://takeuforward.org/data-structure/count-subsets-with-sum-k-dp-17/
 */
public class CountOfSubsetSum {

	static int MOD = (int) (1e9 + 7);

	// TODO if the zero cases and MOD cases were not there then we could remove this checks
	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
		type6();
	}

	// slightly optimized from the previous one
	private static void type6() {
		int[] nums = {1, 1, 1, 1, 1};
		int target = 3;
		int n = nums.length;

		// preprocessing
		int zeros = pushAndCountZeros(n, nums);
		// our new n will be
		n -= zeros;
		int[] dp = new int[target + 1];
		// here the target is 0, and we have 0 numbers to choose, but we can always choose an empty set
		dp[0] = 1;

		// we will fill up the spaces one by one
		// if we think closely we need prev as we want the previous row value.
		// and let's say cur[j] = m + prev[j-x]
		// and we are starting from 0...w, but if we use the one array only in the current iteration,
		// j-x value is already changed but still we need prev[j-x] value
		// we will do a small trick we will start from the last then j-x index will not be changed
		// we will start from n...0
		for (int i = 0; i < n; i++) {
			int num = nums[i];
			for (int sum = target; sum >= num; sum--)
				// int the question it is asked to do the mod operation as some of the test cases can cause integer overflow
				dp[sum] = (int) ((long) dp[sum - num] + dp[sum]) % MOD;
		}

		int count = dp[target];
		count *= (1 << zeros);
		System.out.println(count);
	}


	// tabulation with more space optimization
	// we will only use a single array, check knapsack if you do not understand this solution
	private static void type5() {
		int[] nums = {1, 1, 1, 1, 1};
		int target = 3;
		int n = nums.length;

		// preprocessing
		int zeros = pushAndCountZeros(n, nums);
		// our new n will be
		n -= zeros;
		int[] dp = new int[target + 1];
		// here the target is 0, and we have 0 numbers to choose, but we can always choose an empty set
		dp[0] = 1;

		// we will fill up the spaces one by one
		// if we think closely we need prev as we want the previous row value.
		// and let's say cur[j] = m + prev[j-x]
		// and we are starting from 0...w, but if we use the one array only in the current iteration,
		// j-x value is already changed but still we need prev[j-x] value
		// we will do a small trick we will start from the last then j-x index will not be changed
		// we will start from n...0
		for (int i = 1; i <= n; i++)
			for (int sum = target; sum >= 0; sum--)
				if (nums[i - 1] <= sum)
					// int the question it is asked to do the mod operation as some of the test cases can cause integer overflow
					dp[sum] = (int) ((long) dp[sum - nums[i - 1]] + dp[sum]) % MOD;

		int count = dp[target];
		count *= (1 << zeros);
		System.out.println(count);
	}

	// tabulation with space optimization
	// with two 1D arrays using curr and prev array
	private static void type4() {
		int[] nums = {1, 1, 1, 1, 1};
		int target = 3;
		int n = nums.length;

		// preprocessing
		int zeros = pushAndCountZeros(n, nums);
		// our new n will be
		n -= zeros;
		int[] prev = new int[target + 1];

		// here the target is 0, and we have 0 numbers to choose, but we can always choose an empty set
		prev[0] = 1;

		// we will fill up the spaces one by one
		for (int i = 1; i <= n; i++) {
			int[] curr = new int[target + 1];
			curr[0] = 1;
			for (int sum = 1; sum <= target; sum++)
				if (nums[i - 1] <= sum)
					// int the question it is asked to do the mod operation as some of the test cases can cause integer overflow
					curr[sum] = (int) ((long) prev[sum - nums[i - 1]] + prev[sum]) % MOD;
				else
					curr[sum] = prev[sum];
			// assigning curr to the prev
			prev = curr;
		}
		int count = prev[target];
		count *= (1 << zeros);
		System.out.println(count);
	}

	// similar to equal sum partition
	// using top-down approach
	private static void type3() {
		int[] nums = {1, 1, 1, 1, 1};
		int target = 3;
		int n = nums.length;

		// preprocessing
		int zeros = pushAndCountZeros(n, nums);
		// our new n will be
		n -= zeros;
		int[][] dp = new int[n + 1][target + 1];

		// we do not need to initialize this as the default int array value is 0
		// with n equal to 0, we cannot make any target sum
		for (int i = 1; i <= target; i++) dp[0][i] = 0;

		// but to make target sum equal to 0, we have always one option, that is an empty set
		for (int i = 1; i <= n; i++) dp[i][0] = 1;

		// here the target is 0, and we have 0 numbers to choose, but we can always choose an empty set
		dp[0][0] = 1;

		// we will fill up the spaces one by one
		for (int i = 1; i <= n; i++)
			for (int sum = 1; sum <= target; sum++)
				if (nums[i - 1] <= sum)
					// int the question it is asked to do the mod operation as some of the test cases can cause integer overflow
					dp[i][sum] = (int) ((long) dp[i - 1][sum - nums[i - 1]]
							+ dp[i - 1][sum]) % MOD;
				else
					dp[i][sum] = dp[i - 1][sum];

		int count = dp[n][target];
		count *= (1 << zeros);
		System.out.println(count);
	}

	// TODO lets count the zeros separately and separate them from the actual array
	//  we will push them at the end of the array,
	// the previous solution will not be accepted in gfg or coding ninjas
	// as it misses an edge case,
	// let's say nums = [0,1,3], and target is 4
	// we will have 2 solutions 0,1,3 and 1,3.
	// but in recursion, we are going from back to index 0
	// once it reaches target 0 at nums[i]==1
	// it will return from that it will not consider the possibility of having any 0 on the left side,
	// so it misses 0,1,3.
	// one simple trick we can do is, we can move all 0s on the right side,
	// and count the zero elements then we can compute for the 0 later.
	// for one 0 there would be 2 choices, either to take or not
	// for m number of 0s then the answer will be count * 2^m
	private static void type2() {
		int[] nums = {0, 1, 3};
		int target = 4;
		// preprocessing
		int n = nums.length;
		int zeros = pushAndCountZeros(n, nums);
		// our new n will be
		int N = n - zeros;
		// we will create dp with the new N
		int[][] dp = new int[N + 1][target + 1];
		// marking all the cells as unvisited
		for (int[] row : dp) Arrays.fill(row, -1);

		int count = countOfSubset1(nums, N, target, dp);
		// now we will add for the zeros
		count *= (1 << zeros);
		System.out.println(count);
	}

	private static int pushAndCountZeros(int n, int[] nums) {
		// this function will push all the zeros to the end of the array then we will count them
		// pushing all the zeros at the last of the array
		int low = 0, high = n - 1;
		while (low <= high) {
			if (nums[low] == 0 && nums[high] != 0) {
				swap(nums, low, high);
				low++;
				high--;
				continue;
			}
			if (nums[low] != 0) low++;
			if (nums[high] == 0) high--;
		}
		int zeros = 0;
		// count the zeros from the last
		for (int i = n - 1; i >= 0 && nums[i] == 0; i--) zeros++;
		return zeros;
	}

	static void swap(int[] nums, int low, int high) {
		int temp = nums[low];
		nums[low] = nums[high];
		nums[high] = temp;
	}

	// TODO This will work only when there is no 0 elements in the array
	//  this will fail if there is any zero in the array
	// using memoization
	// similar to equal partition sum problem
	private static void type1() {
		int[] nums = {0, 1, 3};
		int target = 4;
		int n = nums.length;
		int[][] dp = new int[n + 1][target + 1];
		// marking all the cells as unvisited
		for (int[] row : dp) Arrays.fill(row, -1);
		int count = countOfSubset1(nums, n, target, dp);
		System.out.println(count);
	}

	private static int countOfSubset1(int[] nums, int n, int target, int[][] dp) {
		// we can always create target 0 in one way i.e., empty subset
		if (target == 0) return 1;
		// if n is 0 or the target is less than 0, then we cannot make anything
		if (n == 0 || target < 0) return 0;
		// return if recursion is already complete
		if (dp[n][target] != -1) return dp[n][target];
		// else we have 2 ways either to take it or not
		long count = (long) countOfSubset1(nums, n - 1, target - nums[n - 1], dp)
				+ countOfSubset1(nums, n - 1, target, dp);
		// int the question it is asked to do the mod operation as some of the test cases can cause integer overflow
		return dp[n][target] = (int) (count % MOD);
	}

}
