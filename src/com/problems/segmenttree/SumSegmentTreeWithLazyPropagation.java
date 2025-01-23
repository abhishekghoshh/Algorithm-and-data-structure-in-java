package com.problems.segmenttree;

/*
 * Problem link :
 *
 * Solution link :
 *
 */
public class SumSegmentTreeWithLazyPropagation {

	public static void main(String[] args) {
		type1();
	}

	// lazy segment tree is very useful for the range updates
	// it is quite similar to the normal segment tree while we introduce lazy array for the updates
	// lazy array holds the values that we need to update in the actual index
	// it is quite similar to the path compression technique which is done by union and find
	// we will only update the index if we are touching else we just keep it in the lazy array
	// hoping that it will be updated later once we query or update the index
	// once we update the index we will propagate the update value in the left child and
	// right child to their lazy indices
	private static void type1() {
		int[] arr = { 1, 3, 1, 2, 5 };
		SegmentTree segmentTree = new SegmentTree(arr);
		System.out.println(segmentTree.sum(0, arr.length - 1));
		System.out.println(segmentTree.sum(0, 2));
	}

	public static class SegmentTree {
		int n;
		int[] tree;
		int[] lazy;
		int arrSize;

		// this is exactly same as the normal segment tree
		public SegmentTree(int[] arr) {
			arrSize = arr.length;
			n = 4 * arrSize;
			tree = new int[n];
			lazy = new int[n];
			build(arr, tree, 0, arr.length - 1, 0);
		}

		// build is exactly same as the normal segment tree
		private void build(int[] arr, int[] tree, int left, int right, int index) {
			if (left == right) {
				tree[index] = arr[left];
			} else {
				int mid = (left + right) / 2;
				build(arr, tree, left, mid, 2 * index + 1);
				build(arr, tree, mid + 1, right, 2 * index + 2);
				tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
			}
		}

		public int sum(int left, int right) {
			return sum(tree, 0, arrSize - 1, left, right, 0);
		}

		private int sum(int[] tree, int treeRangeStart, int treeRangeEnd, int start, int end, int treeIndex) {
			int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
			// update the previous remaining update and propagate that later we can do the current operation
			if (lazy[treeIndex] != 0) {
				// high-low+1 will give the count of the range for which the node is responsible
				tree[treeIndex] += (treeRangeEnd - treeRangeStart + 1) * lazy[treeIndex];
				// if low!=high then it has children, so we can propagate the addition down
				if (treeRangeStart != treeRangeEnd) {
					lazy[leftIndex] += lazy[treeIndex];
					lazy[rightIndex] += lazy[treeIndex];
				}
				lazy[treeIndex] = 0;
			}
			// our main query operation
			if (treeRangeStart > end || treeRangeEnd < start) {
				return 0;
			} else if (treeRangeStart >= start && treeRangeEnd <= end) {
				return tree[treeIndex];
			} else {
				int mid = treeRangeStart + (treeRangeEnd - treeRangeStart) / 2;
				int leftSum = sum(tree, treeRangeStart, mid, start, end, leftIndex);
				int rightSum = sum(tree, mid + 1, treeRangeEnd, start, end, rightIndex);
				return leftSum + rightSum;
			}
		}

		public void update(int i, int num) {
			update(tree, i, num, 0, n - 1, 0);
		}

		private void update(int[] tree, int index, int num, int treeRangeStart, int treeRangeEnd, int treeIndex) {
			if (treeRangeStart == treeRangeEnd) {
				tree[treeIndex] = num;
			} else {
				int mid = treeRangeStart + (treeRangeEnd - treeRangeStart) / 2;
				if (index <= mid)
					update(tree, index, num, treeRangeStart, mid, 2 * treeIndex + 1);
				else
					update(tree, index, num, mid + 1, treeRangeEnd, 2 * treeIndex + 2);
				tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
			}
		}

		// update the node when you visit it
		// propagate the update to downwards
		// only for the complete overlap we will change the node and propagate
		public void update(int start, int end, int addition) {
			updateAndPropagate(start, end, 0, arrSize - 1, 0, addition, tree);
		}

		public void updateAndPropagate(int start, int end, int treeRangeStart, int treeRangeEnd, int treeIndex, int addition, int[] tree) {
			int leftIndex = 2 * treeIndex + 1, rightIndex = 2 * treeIndex + 2;
			// update the previous remaining update and propagate that later we can update the current index
			// this is the place where we are updating the lazy index for the previous iteration
			if (lazy[treeIndex] != 0) {
				// high-low+1 will give the count of the range for which the node is responsible
				tree[treeIndex] += (treeRangeEnd - treeRangeStart + 1) * lazy[treeIndex];
				// if low!=high then it has children, so we can propagate the addition down
				if (treeRangeStart != treeRangeEnd) {
					lazy[leftIndex] += lazy[treeIndex];
					lazy[rightIndex] += lazy[treeIndex];
				}
				lazy[treeIndex] = 0;
			}
			// actual addition for current addition
			if (treeRangeStart > end || treeRangeEnd < start) {
				// no overlap
				return;
			} else if (start <= treeRangeStart && treeRangeEnd <= end) {
				// complete overlap
				// update the current node by the count*addition
				tree[treeIndex] += (treeRangeEnd - treeRangeStart + 1) * addition;
				// if the node is responsible for more than one node, so can propagate the addition
				// this is the place we are updating the lazy index for the current update
				if (treeRangeStart != treeRangeEnd) {
					lazy[leftIndex] += addition;
					lazy[rightIndex] += addition;
				}
			} else {
				// partial overlap
				int mid = treeRangeStart + (treeRangeEnd - treeRangeStart) / 2;
				updateAndPropagate(start, end, treeRangeStart, mid, leftIndex, addition, tree);
				updateAndPropagate(start, end, mid + 1, treeRangeEnd, rightIndex, addition, tree);
				tree[treeIndex] = tree[leftIndex] + tree[rightIndex];
			}
		}
	}

}
