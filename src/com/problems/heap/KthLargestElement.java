package com.problems.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

import static com.util.ArrayUtil.swap;

/*
 * 
 * problem links :
 * https://leetcode.com/problems/kth-largest-element-in-an-array
 * https://neetcode.io/problems/kth-largest-element-in-an-array
 * https://www.naukri.com/code360/problems/k-largest-elements_796005
 * https://www.naukri.com/code360/problems/kth-smallest-and-largest-element-of-array_1115488
 *
 * Solution link :
 * https://www.youtube.com/watch?v=XEmy13g1Qxc
 * https://www.youtube.com/watch?v=yAs3tONaf3s
 * https://www.youtube.com/watch?v=aXJ-p3Qa4TY
 * 
 * https://takeuforward.org/data-structure/kth-largest-smallest-element-in-an-array/
 * https://neetcode.io/solutions/kth-largest-element-in-an-array
 * */

public class KthLargestElement {
	public static void main(String[] args) {
		type1(); // normal sort
		type2(); // quick sort
		type3(); // count sort
		type4(); // using heap todo discuss this in the interview

	}

	// todo best approach using min heap
	//  we will use min heap
	//  and only store the k largest elements in the heap
	//  after all the iteration we will return the heap top element
	//  min heap will bubble up the minimum elements, and will maintain k size heap
	//  lower elements will bubble up and we will remove that
	//  total time complexity nlog(k) and size O(k)
	private static void type4() {
		int[] nums = {1, 4, 6, 2, 8, 5, 3, 9, 0, 7};
		int k = 3;
		int answer = findKthLargest2(nums, k);
		System.out.println(answer);
	}

	private static int findKthLargest2(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int num : nums) {
			if (minHeap.size() < k)
				minHeap.offer(num);
			else {
				// if the current item is greater than
				if (minHeap.peek() < num) {
					minHeap.poll();
					minHeap.offer(num);
				}
			}
		}
		return minHeap.peek();
	}

	// TODO best solution int the leetcode using the count sort but don't do it in the interview
	//  it is very much dependent on the input range
	private static void type3() {
		int[] nums = { 1, 4, 6, 2, 8, 5, 3, 9, 0, 7 };
		int k = 3;
		int answer = findKthLargest4(nums, k);
		System.out.println(answer);
	}

	private static int findKthLargest4(int[] nums, int k) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int num : nums) {
			if (num < min) min = num;
			if (num > max) max = num;
		}
		int N = max - min + 1;
		int offset = -min;
		int[] freq = new int[N];
		for (int num : nums) freq[num + offset]++;
		int f = 0;
		for (int i = N - 1; i >= 0; i--) {
			f += freq[i];
			if (k <= f) return i - offset;
		}
		return -1;
	}

	// todo using quick sort, don't ever discuss it in the interview
	// partition mechanism,
	// So in quick sort we partition the array,
	// and on every iteration we place one item to its original position,
	// so after each iteration we will check that if it is a kth element or not
	private static void type2() {
		int[] nums = { 1, 4, 6, 2, 8, 5, 3, 9, 0, 7 };
		int k = 3;
		int answer = findKthLargest3(nums, k);
		System.out.println(answer);
	}

	private static int findKthLargest3(int[] nums, int k) {
		int n = nums.length;
		int low = 0, high = n - 1;
		int ans = -1;
		while (low <= high) {
			// we will use the partition technique used is quick sort
			// after one partition the one item will be placed in its place
			// and all lesser elements will be placed in it's left and all larger elements
			// will be placed in its right
			int idx = partition(nums, low, high);
			// if idx == k-1 then we can just return the idx element
			if (idx == k - 1) {
				ans = nums[idx];
				break;
			}
			// else depending upon the idx value we will shrink the array size
			if (idx < k - 1) low = idx + 1;
			else high = idx - 1;
		}
		return ans;
	}

	// quick sort partition mechanism
	private static int partition(int[] nums, int low, int high) {
		int pivot = nums[low];
		int left = low + 1;
		int right = high;
		while (left <= right) {
			if (nums[left] < pivot && pivot < nums[right]) {
				swap(nums, left, right);
				left++;
				right--;
			}
			if (nums[left] >= pivot) left++;
			if (nums[right] <= pivot) right--;
		}
		swap(nums, low, right);
		// after this, all elements before r will be greater than arr[r] and all elements
		// after r will be less than arr[r]
		return right;
	}


	// todo brute force approach
	private static void type1() {
		int[] nums = {1, 4, 6, 2, 8, 5, 3, 9, 0, 7};
		int k = 3;
		int answer = findKthLargest1(nums, k);
		System.out.println(answer);
	}

	private static int findKthLargest1(int[] nums, int k) {
		int n = nums.length;
		Arrays.sort(nums);
		return nums[n - k];
	}
}
