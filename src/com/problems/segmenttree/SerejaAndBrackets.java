package com.problems.segmenttree;

/*
 * Problem link :
 * https://codeforces.com/problemset/problem/380/C
 * 
 * Solution link :
 * 
 * 
 */
public class SerejaAndBrackets {

	public static void main(String[] args) {
		type1();
	}

	private static void type1() {
		String str = "())(())(())(";
		char[] arr = str.toCharArray();
		SegmentTree segmentTree = new SegmentTree(arr);
		System.out.println(segmentTree.full(0, 1));
		System.out.println(segmentTree.full(7, str.length() - 1));
		System.out.println(segmentTree.full(0, str.length() - 1));
	}

	public static class Brackets {
		int opening = 0;
		int closing = 0;
		int full = 0;
	}

	public static class SegmentTree {
		int n;
		Brackets[] tree;
		int arrSize;

		public SegmentTree(char[] arr) {
			arrSize = arr.length;
			n = 4 * arrSize;
			tree = new Brackets[n];
			build(arr, tree, 0, arr.length - 1, 0);
		}

		private void build(char[] arr, Brackets[] tree, int left, int right, int index) {
			Brackets brackets = new Brackets();
			if (left == right) {
				if (arr[left] == '(')
					brackets.opening++;
				else
					brackets.closing++;
			} else {
				int mid = (left + right) / 2;
				build(arr, tree, left, mid, 2 * index + 1);
				build(arr, tree, mid + 1, right, 2 * index + 2);

				Brackets leftSide = tree[2 * index + 1];
				Brackets rightSide = tree[2 * index + 2];
				// only left side opening and right side closing can make a full
				int full = Math.min(leftSide.opening, rightSide.closing);
				brackets.opening = leftSide.opening + rightSide.opening - full;
				brackets.closing = leftSide.closing + rightSide.closing - full;
				brackets.full = leftSide.full + rightSide.full + full;
			}
			tree[index] = brackets;
		}

		public int full(int l, int r) {
			return full(tree, 0, arrSize - 1, l, r, 0).full;
		}

		private Brackets full(Brackets[] tree, int low, int high, int left, int right, int index) {
			Brackets brackets = new Brackets();
			if (low > right || high < left) {
				return brackets;
			} else if (low >= left && high <= right) {
				return tree[index];
			} else {
				int mid = low + (high - low) / 2;
				Brackets leftSide = full(tree, low, mid, left, right, 2 * index + 1);
				Brackets rightSide = full(tree, mid + 1, high, left, right, 2 * index + 2);

				// only left side opening and right side closing can make a full
				int full = Math.min(leftSide.opening, rightSide.closing);
				brackets.opening = leftSide.opening + rightSide.opening - full;
				brackets.closing = leftSide.closing + rightSide.closing - full;
				brackets.full = leftSide.full + rightSide.full + full;
				return brackets;
			}
		}

		public void update(int i, int num) {
		}

	}

}
