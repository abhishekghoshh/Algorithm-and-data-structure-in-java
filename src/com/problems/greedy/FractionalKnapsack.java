package com.problems.greedy;

import java.util.Arrays;

/*
 * 
 * problem links :
 * https://www.codingninjas.com/codestudio/problems/975286
 * https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
 * 
 * Solution video :
 * https://www.youtube.com/watch?v=F_DDzYnxO14&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=50
 *
 * https://takeuforward.org/data-structure/fractional-knapsack-problem-greedy-approach/
 * */
public class FractionalKnapsack {

	public static void main(String[] args) {
		type2();
		type1();
	}

	// same as previous
	// but here we will directly sort the given input array
	private static void type2() {
		int W = 50;
		Item[] arr = {new Item(60, 10), new Item(100, 20), new Item(120, 30)};
		int n = 3;
		Arrays.sort(arr, (item1, item2) -> {
			double profit1 = (double) (item1.value) / (double) (item1.weight);
			double profit2 = (double) (item2.value) / (double) (item2.weight);
			return Double.compare(profit2, profit1);
		});

		int weight = 0;
		double profit = 0.0;

		for (int i = 0; i < n; i++) {
			if (weight + arr[i].weight <= W) {
				weight += arr[i].weight;
				profit += arr[i].value;
			} else {
				profit += ((double) arr[i].value / (double) arr[i].weight) * (double)( W - weight);
				break;
			}
		}
		System.out.println(profit);
	}

	// time complexity O(n*log(n))+O(n)
	// space complexity O(n)
	private static void type1() {
		int W = 50;
		Item[] arr = {new Item(60, 10), new Item(100, 20), new Item(120, 30)};
		int n = 3;

		double profit = 0;
		// to have the maximum profit, we have to take the highest valued object first,
		// so we will sort the items by their profit=> value/weight in descending
		Profit[] profits = new Profit[n];
		for (int i = 0; i < n; i++)
			profits[i] = new Profit(i, ((double) arr[i].value) / arr[i].weight);
		Arrays.sort(profits, (profit1, profit2) -> Double.compare(profit2.ratio, profit1.ratio));
		// we will try to take the highest valued object as a whole
		for (int i = 0; i < n; i++) {
			Item item = arr[profits[i].itemIndex];
			if (W >= item.weight) {
				// item can be taken as a whole
				W = W - item.weight;
				profit = profit + item.value;
			} else {
				// item cannot be taken as a whole,
				// so we will take the fractional object to have the highest profit
				profit = profit + profits[i].ratio * W;
				// at this time W will be 0 at we had taken the fractional weight also
				break;
			}
		}
		System.out.println(profit);
	}

	public static class Item {
		int value, weight;

		Item(int x, int y) {
			this.value = x;
			this.weight = y;
		}
	}

	public static class Profit {
		int itemIndex;
		double ratio;

		public Profit(int id, double ratio) {
			this.itemIndex = id;
			this.ratio = ratio;
		}
	}
}
