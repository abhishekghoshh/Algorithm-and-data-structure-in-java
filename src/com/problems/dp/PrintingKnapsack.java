package com.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem link :
 * 
 * 
 * Solution link :
 * 
 * 
 */
public class PrintingKnapsack {
	public static void main(String[] args) {
		type2();
	}

	public static void type2() {
		int[][] items = {
				{1, 2},
				{4, 3},
				{5, 6},
				{6, 7}
		};
		int capacity = 10;
		int n = items.length;
		int[] val = new int[n];
		int[] wt = new int[n];
		for (int i = 0; i < n; i++) {
			val[i] = items[i][0];
			wt[i] = items[i][1];
		}
		int[][] memo = new int[n + 1][capacity + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= capacity; j++)
				if (wt[i - 1] <= j)
					memo[i][j] = Math.max(
							val[i - 1] + memo[i - 1][j - wt[i - 1]],
							memo[i - 1][j]
					);
				else
					memo[i][j] = memo[i - 1][j];
		}
		int profit = memo[n][capacity];
		int maxProfit = memo[n][capacity];
		int w = capacity;
		List<Integer> indices = new ArrayList<>();
		for (int i = n; i > 0 && w > 0; i--) {
			if (maxProfit != memo[i - 1][w]) {
				indices.add(i - 1);
				maxProfit = maxProfit - val[i - 1];
				w = w - wt[i - 1];
			}
		}
		int l = 0, r = indices.size() - 1;
		while (l < r) {
			int temp = indices.get(r);
			indices.set(r, indices.get(l));
			indices.set(l, temp);
			l++;r--;
		}
		List<List<Integer>> answer = Arrays.asList(List.of(profit), indices);
		System.out.println(answer);

	}
}
