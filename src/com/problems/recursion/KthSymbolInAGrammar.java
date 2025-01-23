package com.problems.recursion;

/*
 * Problem link :
 * https://leetcode.com/problems/k-th-symbol-in-grammar/description/
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=5P84A0YCo_Y&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=10
 * https://www.youtube.com/watch?v=pmD2HCKaqRQ
 *
 * https://neetcode.io/solutions/k-th-symbol-in-grammar
 * */
public class KthSymbolInAGrammar {
	// We build a table of n rows (1-indexed). We start by writing 0 in the 1st row.
	// Now in every subsequent row, we look at the previous row and replace each
	// occurrence of 0 with 01, and each occurrence of 1 with 10.For example, for n
	// = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110. Given two
	// integer n and k, return the kth (1-indexed) symbol in the nth row of a table
	// of n rows.
	public static void main(String[] args) {
		type1();
		type2();
	}

	// TODO it is also very efficient approach just the thinking process is little bit different
	private static void type2() {
		int n = 3, k = 4;
		int value = kthGrammar1(n, k);
		System.out.printf("at %d row at %d column value is %d \n", n, k, value);
	}

	// we know that x bit is changing to xX,
	// but if we consider series like 0110,
	// it is converting into 01101001
	// even for a series also it is the same.
	// if x is 0110 then x X will be 0110 1001
	// which is the next series
	// we just have to know that, is it residing in the first half or the second half.
	// so if it is in the first half then series2[k] = series1[k]
	// else series2[k] = opposite series1[size/2 - k]
	private static int kthGrammar1(int n, int k) {
		// in the first row there is only 0
		if (n == 1) return 0;
		// size is the total size of the series that is 2^n-1
		int rowSize = (1 << (n - 1));
		int half = rowSize / 2;
		if (k <= half) // it is in the first half
			return kthGrammar1(n - 1, k);
		else // it is in the second half
			return 1 - kthGrammar1(n - 1, k - half);
	}


	// TODO very efficient approach explain it in the interview
	// we know 0 creates 01 and 1 creates 10.
	// and at n=1 and k=1 the value is 0
	// the series is like 0 => 01 => 0110 => 01101001
	// if we look close, then we will find that x is converted to xX, where X is the opposite bit of x.
	// so if by somehow we come to know the x, then we will know about the next series.
	// in xX, the 1st bit is the same, while the 2nd bit is opposite,
	// so we can conclude that if it is an odd bit position then it is the same bit else opposite
	private static void type1() {
		int n = 3, k = 4;
		int value = kthGrammar(n, k);
		System.out.printf("at %d row at %d column value is %d \n", n, k, value);
	}

	private static int kthGrammar(int n, int k) {
		// in the first row there is only 0
		if (n == 1) return 0;
		// finding the bit for previous n
		int prevK = (k + 1) / 2; // if current layer it is k then prev layer it will be (k+1)/2
		int previous = kthGrammar(n - 1, prevK);
		// if k is odd, then the same else opposite [in xX] if k is odd then bit will be same else opposite
		return isOdd(k) ? previous : (1 - previous);
	}

	static boolean isOdd(int x) {
		return (x & 1) == 1;
	}

}
