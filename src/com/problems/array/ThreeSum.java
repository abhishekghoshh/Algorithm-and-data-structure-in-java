package com.problems.array;

import java.util.*;

import static com.util.ArrayUtil.maxN;
import static com.util.ArrayUtil.minN;

/*
 * Problem link :
 * https://leetcode.com/problems/3sum/
 * https://neetcode.io/problems/three-integer-sum
 * https://www.naukri.com/code360/problems/three-sum_6922132
 * https://www.naukri.com/code360/problems/893028
 *
 * Solution link :
 * https://www.youtube.com/watch?v=DhFh8Kw7ymk
 * https://www.youtube.com/watch?v=onLoX6Nhvmg
 * https://www.youtube.com/watch?v=jzZsG8n2R9A
 *
 * Blogs :
 * https://takeuforward.org/data-structure/3-sum-find-triplets-that-add-up-to-a-zero/
 * https://neetcode.io/solutions/3sum
 * */

// Tags: array, sorting, two pointers
public class ThreeSum {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// todo two pointer approach
	// time complexity O(n*log(n)) + O(n^2)
	// first we will sort the array, so all the numbers will come increasing manner and duplicates will come together
	// space complexity O(1)
	private static void type2() {
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> answer = threeSum2(nums);
		System.out.println(answer);
	}

	private static List<List<Integer>> threeSum2(int[] nums) {
		int n = nums.length;
		Arrays.sort(nums);
		List<List<Integer>> answer = new ArrayList<>();
		int left, right, leftItem, rightItem;

		for (int i = 0; i < n - 2; i++) {
			int num = nums[i];
			// if previous num is also same then we will skip for the number
			if (i > 0 && nums[i - 1] == num) continue; //ignore duplicates
			// num[n-2] and num[n-1] are the highest number in that array
			// if num + num[n-2] and num[n-1] is less than 0, then num will not able create sum with any other elements as well
			// skipping num as num is too small
			if (num + nums[n - 2] + nums[n - 1] < 0) continue;
			// nums[i] + nums[i + 1] + nums[i + 2] will be the smallest in this series
			// if the sum of first 3 is greater than 0 then the sum for remaining items will also be greater than 0,
			// so we will break the loop al together as there will be no answer in future
			if (num + nums[i + 1] + nums[i + 2] > 0) break;
			left = i + 1;
			right = n - 1;
			while (left < right) {
				leftItem = nums[left];
				rightItem = nums[right];
				long sum = (long) num + leftItem + rightItem;
				if (sum == 0) {
					answer.add(List.of(num, leftItem, rightItem));
					// skipping the duplicates from the left side
					while (left < n && nums[left] == leftItem) left++;
					// skipping the duplicates from the right side
					while (right > i && nums[right] == rightItem) right--;
				} else if (sum < 0) {
					// finding the next start and skipping the duplicates
					while (left < n && nums[left] == leftItem) left++;
				} else {
					// find the next end skipping duplicates
					while (right > i && nums[right] == rightItem) right--;
				}
			}
		}
		return answer;
	}

	// todo brute force approach
	// answer will contain duplicate and to remove duplicates again we have to use a set
	// time complexity O(n^3)
	private static void type1() {
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> answer = threeSum1(nums);
		System.out.println(answer);
	}

	private static List<List<Integer>> threeSum1(int[] nums) {
		int n = nums.length;
		List<List<Integer>> answer = new ArrayList<>();
		Set<String> set = new HashSet<>();
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					if ((long) nums[i] + nums[j] + nums[k] == 0) {
						int lowest = minN(nums[i], nums[j], nums[k]);
						int highest = maxN(nums[i], nums[j], nums[k]);
						int middle = -highest - lowest;
						String id = lowest + "-" + middle + "-" + highest;
						if (!set.contains(id)) {
							set.add(id);
							answer.add(List.of(lowest, middle, highest));
						}
					}
				}
			}
		}
		return answer;
	}

}
