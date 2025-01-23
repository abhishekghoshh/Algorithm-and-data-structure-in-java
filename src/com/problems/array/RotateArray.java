package com.problems.array;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/rotate-array/description/
 * https://leetcode.com/problems/find-the-encrypted-string/description/
 * https://www.codingninjas.com/studio/problems/rotate-array_1230543
 *
 * Solution is :
 * https://www.youtube.com/watch?v=wvcQg43_V8U
 * https://www.youtube.com/watch?v=BHr381Guz3Y
 *
 * https://takeuforward.org/data-structure/rotate-array-by-k-elements/
 * https://neetcode.io/solutions/rotate-array
 * */
public class RotateArray {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
	}

	// for rotating to the right, change the index of the reverse method
	// s = "dart", k = 3
	// ans = "tdar" (it is left rotation by k spaces)
	// we can just do the same with little modification
	// reverse(0,n-1) => reverse(0,n-k-1) => reverse(n-k,n-1)
	// or we can compute k2 = n-k
	// and compute the right rotation of k2
	// in a circle k right rotation means n-k left rotation and vice versa
	private static void type4() {
		String s = "dart";
		int k = 3;
		String ans = getEncryptedString(s, k);
		System.out.println(ans);
	}

	public static String getEncryptedString(String s, int k) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		if (n == 1) return s;
		k = k % n;
		reverse(arr, 0, n - 1);
		reverse(arr, 0, n - k - 1);
		reverse(arr, n - k, n - 1);
		return new String(arr);
	}

	static void reverse(char[] arr, int start, int end) {
		while (start < end) {
			char temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	// todo explain this in the interview
	//  lets say the numbers are 1, 2, 3, 4, 5, 6, 7
	//  and after rotation k=3 left rotation it will be 5 6 7 1 2 3 4
	//  lets make the intuition from the answer itself
	//  if we divide the array into 2 parts arr[0,k) arr[k,n)
	//  if we just reverse them individually then it would become 7 6 5 4 3 2 1
	//  now it become something we have seen already
	//  it was the complete reverse of the array,
	//  so if we reverse the whole array we will get the original array
	//  so our answer will be like -> reverse(0,n-1) => reverse(0,k-1) => reverse(k,n-1)
	// time complexity O(2n)
	// space complexity O(1)
	private static void type3() {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;
		rotate3(k, nums);
	}

	// reverse(0,n-1) => reverse(0,k-1) => reverse(k,n-1)
	private static void rotate3(int k, int[] nums) {
		int n = nums.length;
		k = k % n;
		reverse(nums, 0, n - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, n - 1);
		print(nums);
	}


	public static void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

	// todo do not try to explain it to the interview
	// time complexity O(n*k)
	// space complexity O(1)
	private static void type2() {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;
		rotate2(nums, k);
		print(nums);
	}

	private static void rotate2(int[] nums, int k) {
		int n = nums.length;
		k = k % n;
		// shift k times
		for (int i = 0; i < k; i++) {
			// copy the last item first
			int last = nums[n - 1];
			// shift right all the elements by one step
			for (int j = n - 1; j > 0; j--) {
				nums[j] = nums[j - 1];
			}
			// assign the last to zeroth items
			nums[0] = last;
		}
	}

	// create one extra array
	// copy elements to that array
	// again copy to that original array
	// space complexity O(n)
	// time complexity O(2n)
	private static void type1() {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;
		rotate1(nums, k);
		print(nums);
	}

	private static void rotate1(int[] nums, int k) {
		int n = nums.length;
		k = k % n;
		int[] copy = new int[n];
		for (int i = 0; i < n; i++) {
			int j = (i + k) % n;
			copy[j] = nums[i];
		}
		System.arraycopy(copy, 0, nums, 0, n);
	}

}
