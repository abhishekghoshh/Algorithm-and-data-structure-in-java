package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem links:
 * https://practice.geeksforgeeks.org/problems/permutation-with-spaces3627/1
 * 
 * Solution link
 * https://www.youtube.com/watch?v=1cspuQ6qHW0&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=14
 * */
public class PermutationWithSpaces {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// similar to previous type
	// with 2 optimizations
	private static void type2() {
		String s = "ABCD";
		List<String> answer = new ArrayList<>();
		StringBuilder bucket = new StringBuilder();
		addSpaces2(s.toCharArray(), 0, bucket, answer);
		System.out.println(answer);
	}

	private static void addSpaces2(char[] arr, int i, StringBuilder bucket, List<String> answer) {
		if (i == arr.length - 1) {
			bucket.append(arr[i]);
			answer.add(bucket.toString());
			bucket.deleteCharAt(bucket.length() - 1);
			return;
		}
		// here we are calling the 2nd recursion call once the letter is added
		bucket.append(arr[i]);
		addSpaces2(arr, i + 1, bucket, answer);

		// once the previous recursion is finished we will add the space and call another recursion call
		bucket.append(" ");
		addSpaces2(arr, i + 1, bucket, answer);

		// now we will remove space as well as the character
		bucket.deleteCharAt(bucket.length() - 1);
		bucket.deleteCharAt(bucket.length() - 1);
	}

	// we have two options either to add space after any character or not
	// at the last index we don't have to add anything
	// we can just return from that
	// that is our base condition
	private static void type1() {
		String s = "ABCD";
		List<String> answer = new ArrayList<>();
		StringBuilder bucket = new StringBuilder();
		addSpaces1(s, 0, bucket, answer);
		System.out.println(answer);
	}

	private static void addSpaces1(String s, int i, StringBuilder bucket, List<String> answer) {
		// we are at the last character
		if (i == s.length() - 1) {
			bucket.append(s.charAt(i));
			answer.add(bucket.toString());
			bucket.deleteCharAt(bucket.length() - 1);
			return;
		}

		// here we are only choosing character
		bucket.append(s.charAt(i));
		addSpaces1(s, i + 1, bucket, answer);
		bucket.deleteCharAt(bucket.length() - 1);


		// here we are choosing character and space to part of answer
		bucket.append(s.charAt(i));
		bucket.append(" ");
		addSpaces1(s, i + 1, bucket, answer);
		bucket.deleteCharAt(bucket.length() - 1);
		bucket.deleteCharAt(bucket.length() - 1);
	}

}
