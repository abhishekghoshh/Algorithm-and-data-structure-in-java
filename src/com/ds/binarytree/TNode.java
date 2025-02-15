package com.ds.binarytree;

import java.util.Arrays;

public class TNode {
    public static final int NULL = Integer.MIN_VALUE;
    public int data;
    public int val;
    public TNode left, right, next;

    public TNode(int data) {
        this.data = this.val = data;
    }

    public TNode(int val, TNode left, TNode right) {
        this.data = this.val = val;
        this.left = left;
        this.right = right;
    }

    public TNode data(int data) {
        this.data = this.val = data;
        return this;
    }


    public TNode left(TNode left) {
        this.left = left;
        return this;
    }

    public TNode right(TNode right) {
        this.right = right;
        return this;
    }

    public TNode search(int val) {
        if (val == this.data) return this;
        if (null != this.left) {
            TNode left = this.left.search(val);
            if (null != left) return left;
        }
        if (null != this.right) {
            return this.right.search(val);
        }
        return null;
    }

    public static TNode withCount(int n) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++)
            nums[i - 1] = i;
        return withNodes(nums);
    }

    public static TNode withNodes(int... nums) {
        int n = nums.length;
        if (n == 0) return null;
        TNode[] nodes = new TNode[n];
        for (int i = 0; i < n; i++)
            nodes[i] = (nums[i] != NULL) ? new TNode(nums[i]) : null;
        for (int i = 0; i < n; i++) {
            if (null == nodes[i]) continue;
            int left = i * 2 + 1, right = i * 2 + 2;
            nodes[i].left = left < n ? nodes[left] : null;
            nodes[i].right = right < n ? nodes[right] : null;
        }
        return nodes[0];
    }
    public static TNode withObjectNodes(Integer... nums) {
        int n = nums.length;
        if (n == 0) return null;
        TNode[] nodes = new TNode[n];
        for (int i = 0; i < n; i++)
            nodes[i] = (nums[i] != null) ? new TNode(nums[i]) : null;
        for (int i = 0; i < n; i++) {
            if (null == nodes[i]) continue;
            int left = i * 2 + 1, right = i * 2 + 2;
            nodes[i].left = left < n ? nodes[left] : null;
            nodes[i].right = right < n ? nodes[right] : null;
        }
        return nodes[0];
    }

    public static TNode imbalancedBST(int n) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) nums[i - 1] = i;
        return imbalancedBST(nums, 0, n - 1);
    }

    public static TNode imbalancedBST(int... nums) {
        Arrays.sort(nums);
        return imbalancedBST(nums, 0, nums.length - 1);
    }

    private static TNode imbalancedBST(int[] nums, int l, int r) {
        if (l > r) return null;
        if (l == r) return new TNode(nums[l]);
        int mid = rand(l, r);
        TNode left = imbalancedBST(nums, l, mid - 1);
        TNode right = imbalancedBST(nums, mid + 1, r);
        return new TNode(nums[mid], left, right);
    }

    public static int rand(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static TNode makeBST(int n) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) nums[i - 1] = i;
        return makeBST(nums, 0, n - 1);
    }

    public static TNode makeBST(int... nums) {
        Arrays.sort(nums);
        return makeBST(nums, 0, nums.length - 1);
    }

    private static TNode makeBST(int[] nums, int l, int r) {
        if (l > r) return null;
        if (l == r) return new TNode(nums[l]);
        int mid = (l + r) / 2;
        TNode left = makeBST(nums, l, mid - 1);
        TNode right = makeBST(nums, mid + 1, r);
        return new TNode(nums[mid], left, right);
    }

    public TNode searchBST(int root) {
        return searchBST(this, root);
    }

    public static TNode searchBST(TNode root, int data) {
        if (null == root || root.data == data) return root;
        return data < root.data ?
                searchBST(root.left, data) :
                searchBST(root.right, data);
    }
}