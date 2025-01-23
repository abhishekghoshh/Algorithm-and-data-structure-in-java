package com.problems.dp;

/*
 * Problem link:
 * https://leetcode.com/problems/target-sum/description/ => the intuition is little bit different but the solution is same
 * https://www.naukri.com/code360/problems/partitions-with-given-difference_3751628
 * https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=ot_XBHyqpFc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=11
 * https://www.youtube.com/watch?v=zoilQD1kYSg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=19
 *
 * https://takeuforward.org/data-structure/count-partitions-with-given-difference-dp-18/
 */
public class CountNumberOfSubsetWithGivenSumDifference {


	// Exactly the same as the CountOfSubsetSum problem, but here the subset difference is given
	// here we have to find the subset sum (s1)
	public static void main(String[] args) {
		type1();
		type2();
	}

	// little optimization previous on how we are making subset sum 1 or s1
	private static void type2() {
		int target = -1;
		int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};

		int n = nums.length;
		int sum = 0;
		for (int num : nums) sum += num;
		// one optimization we can think here is if we minimize the value of s1
		// then we will have to make less amount of dp and also less iteration.
		// 2 things we can notice is that
		// s1-s2 or the target can be negative or positive
		// s1 = (sum+target)/2 and s2 = (sum-target)/2
		// see if the target is negative, then s1 will be lesser.
		// and if the target is negative, then s2 will be lesser.
		// we can take either s1 or s2 for our computation, and both will give us the same answer
		int s1 = Math.min((sum - target) / 2, (sum + target) / 2);
		// TODO remaining part of the type1
	}


	// as per the question s1 - s2 = target,
	// but we know one thing s1 + s2 = total sum [always]
	// so s1 is (sum + target)/2
	// sum + target must be divisible by 2 and the sum should be lesser than diff otherwise it is not a valid input
	// TODO works on greater than zero elements, but with zero element we have do the preprocessing, check the CountOfSubsetSum problem
	private static void type1() {
		int target = 1;
		int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
		int count = findTargetSumWays1(nums, target);
		System.out.println(count);
	}

	static int findTargetSumWays1(int[] nums, int target) {
		int n = nums.length;
		int sum = 0;
		for (int num : nums) sum += num;
		// if sum + target is odd, then we cannot make subsets
		// if the sum is lesser than the target, then we cannot make the target by
		// subtracting one partition to another
		if ((sum + target) % 2 != 0
				|| sum < Math.abs(target)) return 0;

		// we count use our intuition from this step
		// s1 + s2 = sum and s1 - s2 = target
		// so s1 will be (sum + target)/2
		int s1 = (sum + target) / 2;

		// TODO the following code is the most optimized solution of the CountOfSubsetSum problem
		// preprocessing
		int zeros = pushAndCountZeros(n, nums);
		// our new n will be
		n -= zeros;
		int[] dp = new int[s1 + 1];
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
			for (int s = s1; s >= num; s--)
				// int the question it is asked to do the mod operation as some of the test cases can cause integer overflow
				dp[s] += dp[s - num];
		}

		int count = dp[s1];
		count *= (1 << zeros);
		return count;
	}

	static int pushAndCountZeros(int n, int[] nums) {
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
}
