package com.problems.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * 
 * 
 * Solution link :
 * 
 * 
 */
public class BitSet {

	public static void main(String[] args) {
		type1();
	}

	// constraint 0 <= n <= 30
	// if the constraint is 0 <= n <= 60 then we can use double data type
	// space complexity O(1)
	// time complexity O(1)
	private static void type1() {
		Set set = new Set();
		set.add(4);
		set.add(9);
		set.add(25);
		System.out.println(set.contains(4) + " " + set.contains(9) + " " + set.contains(25));
		set.remove(9);
		System.out.println(set.contains(4) + " " + set.contains(9) + " " + set.contains(25));
		System.out.println(set);
	}

	private static class Set {
		int set = 0;//0 <= n <= 30
//		long longSet = 0L;// 0 <= n <= 60

		// time complexity O(1)
		// space complexity O(1)
		public void add(int num) {
			int mask = 1 << num;
			set = set | mask;
		}

		// time complexity O(1)
		// space complexity O(1)
		public void remove(int num) {
			int mask = ~(1 << num);
			set = set & mask;
		}

		// time complexity O(1)
		// space complexity O(1)
		public boolean contains(int num) {
			int mask = 1 << num;
			return (set & mask) != 0;
		}

		public List<Integer> numbers() {
			List<Integer> list = new ArrayList<>();
			int set = this.set;
			int index = 0;
			while (set != 0) {
				if ((set & 1) == 1) {
					list.add(index);
				}
				index++;
				set = set >> 1;
			}
			return list;
		}

		@Override
		public String toString() {
			return numbers().toString();
		}
	}

}
