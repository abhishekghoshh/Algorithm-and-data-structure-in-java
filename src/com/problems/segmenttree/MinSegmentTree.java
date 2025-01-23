package com.problems.segmenttree;

/*
 * Problem link :
 * 
 * 
 * Solution link :
 * 
 * 
 */
public class MinSegmentTree {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		int[] arr = { 3, 6, 1, 2, 5, 8, 6, 2, 8, 4, 5, 2, 0 };
		SegmentTree segmentTree = new SegmentTree(arr);
		System.out.println(segmentTree.min(0, 9));
		System.out.println(segmentTree.min(2, 5));
		segmentTree.update(3, 0);
		System.out.println(segmentTree.min(2, 5));
	}

	public static class SegmentTree {
		int n;
		int[] tree;
		int arrSize;

		public SegmentTree(int[] arr) {
			// todo for any array of size n the length of the tree can be at max 4n
			arrSize = arr.length;
			n = 4 * arrSize;
			tree = new int[n];
			build(arr, tree, 0, arrSize - 1, 0);
		}

		// time complexity is equivalent to the number of nodes visited
		// the size of the tree can be at max 4n,
		// so time complexity will be at max O(4n)
		private void build(int[] arr, int[] tree, int left, int right, int index) {
			if (left == right) {
				tree[index] = arr[left];
			} else {
				int mid = (left + right) / 2;
				build(arr, tree, left, mid, 2 * index + 1);
				build(arr, tree, mid + 1, right, 2 * index + 2);
				tree[index] = Math.min(tree[2 * index + 1], tree[2 * index + 2]);
			}
		}

		// for min operation
		// there can be 3 cases
		// partial overlap, no overlap, complete overlap
		// 1.partial overlap of node with the range (L,R)
		// then return min(left,right)
		// 2.no overlap with the range (L,R)
		// then return INT_MAX
		// 3.complete overlap with the range (L,R)
		// then return tree(index)
		public int min(int start, int end) {
			return min(start, end, 0, arrSize - 1, 0, tree);
		}

		// time complexity is approximately O(log(n))
		// for very small range no overlap will be occurring most
		// for very big range complete overlap will be occurring most

		// [treeRangeStart, treeRangeEnd] is the segment tree range.
		// [start,end] is the query range
		// (treeRangeEnd < start) means our query range lies right side of the segment tree
		// (treeRangeStart > end) means our query range lies left side of the segment tree.
		// [start, end] [treeRangeStart, treeRangeEnd] or [treeRangeStart, treeRangeEnd] [start, end]
		private int min(int start, int end, int treeRangeStart, int treeRangeEnd, int treeIndex, int[] tree) {
			// no overlap
			if (treeRangeStart > end || treeRangeEnd < start) {
				return Integer.MAX_VALUE;
			} else if (start <= treeRangeStart && treeRangeEnd <= end) {
				// complete overlap that means our query range lies in between the tree range
				return tree[treeIndex];
			} else {
				// partial overlap
				int mid = treeRangeStart + (treeRangeEnd - treeRangeStart) / 2;
				int leftMin = min(start, end, treeRangeStart, mid, 2 * treeIndex + 1, tree);
				int rightMin = min(start, end, mid + 1, treeRangeEnd, 2 * treeIndex + 2, tree);
				return Math.min(leftMin, rightMin);
			}
		}

		public void update(int index, int num) {
			update(index, num, 0, arrSize - 1, 0, tree);
		}

		// time complexity O(log(4n))
		private void update(int index, int num, int treeRangeStart, int treeRangeEnd, int treeIndex, int[] tree) {
			// we have reached to the point where we need to update
			if (treeRangeStart == treeRangeEnd) {
				tree[treeIndex] = num;
			} else {
				int treeRangeMid = treeRangeStart + (treeRangeEnd - treeRangeStart) / 2;
				int treeLeftIndex = 2 * treeIndex + 1, treeRightIndex = 2 * treeIndex + 2;
				// 'index' lies in the left
				if (index <= treeRangeMid) {
					update(index, num, treeRangeStart, treeRangeMid, treeLeftIndex, tree);
				} else {
					// 'index' lies in the right
					update(index, num, treeRangeMid + 1, treeRangeEnd, treeRightIndex, tree);
				}
				// as the child range min is updated, so we need to recheck if the current range min is also updated or not
				tree[treeIndex] = Math.min(tree[treeLeftIndex], tree[treeRightIndex]);
			}

		}

	}
}
