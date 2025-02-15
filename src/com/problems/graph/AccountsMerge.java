package com.problems.graph;

import java.util.*;
import java.util.Map.Entry;

/*
 * Problem link :
 * https://leetcode.com/problems/accounts-merge/
 * https://practice.geeksforgeeks.org/problems/merging-details/1
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=FMwpt_aQOGw&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=50
 * 
 * https://takeuforward.org/data-structure/accounts-merge-dsu-g-50/
 * 
 */
public class AccountsMerge {

	public static void main(String[] args) {
		type1();
	}

	// we will use a disjoint set for merging accounts
	private static void type1() {
		List<List<String>> accounts = List.of(
				List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
				List.of("John", "johnsmith@mail.com", "john00@mail.com"),
				List.of("Mary", "mary@mail.com"),
				List.of("John", "johnnybravo@mail.com")
		);

		List<List<String>> answer = accountsMerge(accounts);
		System.out.println(answer);
	}

	public static List<List<String>> accountsMerge(List<List<String>> accounts) {
		int n = accounts.size();
		// lets first create the parent array and the rank array and initialize it
		int[] parent = new int[n];
		int[] rank = new int[n];
		for (int i = 0; i < n; i++) {
			rank[i] = 0;
			parent[i] = i;
		}
		// we will map all mail to its index
		// like for johnsmith@mail.com will be mapped to 0.
		// but if we find the mail again in the map that means and ith index
		// means ith index can be mapped to 0th index, they belong to the same person
		Map<String, Integer> mailId = new HashMap<>();
		for (int k = 0; k < accounts.size(); k++) {
			List<String> mailList = accounts.get(k);
			// 0th element is for the name, so we will start from 1
			for (int i = 1; i < mailList.size(); i++) {
				String mail = mailList.get(i);
				// if the mail does not exist, then we will set it to k
				if (!mailId.containsKey(mail)) {
					mailId.put(mail, k);
				} else {
					// else we will set k to its parent node
					int parentNode = mailId.get(mail);
					union(parent, rank, parentNode, k);
				}
			}
		}
		// now we have to merge all the mails, and then we will sort the mails and add the names at last
		// todo it can be done in multiple ways, but this approach looks easier
		// we will take a array for n size, and the mails to its parent ids list
		List<String>[] mergedAccounts = new List[n];
		for (Entry<String, Integer> entry : mailId.entrySet()) {
			String mail = entry.getKey();
			int id = entry.getValue();
			// ultimate parent id of the current mail
			int parentId = find(parent, id);
			// we will initialize that index with an empty arraylist
			if (mergedAccounts[parentId] == null) mergedAccounts[parentId] = new ArrayList<>();
			mergedAccounts[parentId].add(mail);
		}
		List<List<String>> answer = new ArrayList<>();
		for (List<String> account : mergedAccounts) {
			// if the account is null, then all the mails are merged with a different index
			if (account == null) continue;
			// as per the question, we will sort it
			Collections.sort(account);
			// we can take any mail from the mail list
			String mail = account.get(0);
			// we will find the ultimate parent index of the mail and find the name from the account array list
			int parentId = find(parent, mailId.get(mail));
			String name = accounts.get(parentId).get(0);
			// as per the question, we will add the name at the 0th position
			account.add(0, name);
			answer.add(account);
		}
		return answer;
	}

	private static void union(int[] parent, int[] rank, int u, int v) {
		int baseParentU = find(parent, u);
		int baseParentV = find(parent, v);
		if (rank[baseParentU] < rank[baseParentV]) {
			parent[baseParentU] = baseParentV;
			rank[baseParentV]++;
		} else {
			parent[baseParentV] = baseParentU;
			rank[baseParentU]++;
		}
	}

	private static int find(int[] parent, int node) {
		// src == parent[src] means parent of the node
		if (node == parent[node]) return node;
		int baseParent = find(parent, parent[node]);
		parent[node] = baseParent;
		return baseParent;
	}
}
