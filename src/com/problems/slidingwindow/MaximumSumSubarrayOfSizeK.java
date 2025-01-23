package com.problems.slidingwindow;

/*
 * Problem link : 
 * https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
 * 
 * Solution link :
 * Aditya Verma :
 * https://www.youtube.com/watch?v=KtpqeN0Goro&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=3
 * */
public class MaximumSumSubarrayOfSizeK {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	private static void type3() {
		int[] arr = {4, 1, 1, 2, 5, 2, 3, 1, 0, 3};
		int k = 4;
		int n = arr.length;
		int max = Integer.MIN_VALUE, sum = 0;
		int left = 0, right = 0;
		while (right < n) {
			if (right - left < k)
				sum += arr[right++];
			else
				sum = sum + arr[right++] - arr[left++];
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}


	private static void type2() {
		int[] arr = {4, 1, 1, 2, 5, 2, 3, 1, 0, 3};
		int k = 4;
		int n = arr.length;
		int max = Integer.MIN_VALUE, sum = 0;
		int left = 0, right = 0;
		// we are letting the window until the the window size becomes k
		while (right < k) {
			sum = sum + arr[right++];
		}
		// once it become k, in the next iteration it's coming to else
		// so if the window size is 4
		// then now left will be 0 and right will be 4
		// and sum is arr[0]+arr[1]+arr[2]+arr[3]
		// we are checking for the first window
		max = Math.max(max, sum);
		// now left =0 and right =k
		while (right < n) {
			// now we will calculate sum our current window
			// where right=k and left=1
			// so now we will add arr[4] and subtract arr[0]
			// thus sum is arr[1]+arr[2]+arr[3]+arr[4]
			sum = sum + arr[right++] - arr[left++];
			// checking for the current window
			max = Integer.max(max, sum);
		}
		System.out.println(max);
	}

	// Brute force approach
	private static void type1() {
	}

}
