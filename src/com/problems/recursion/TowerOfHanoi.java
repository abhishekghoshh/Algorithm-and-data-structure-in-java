package com.problems.recursion;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/tower-of-hanoi-1587115621/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=tZELBt_y50o
 */
public class TowerOfHanoi {
	/*
	 * Tower of Hanoi is a mathematical puzzle where we have three rods (A, B, and
	 * C) and N disks. Initially, all the disks are stacked in decreasing value of
	 * diameter i.e., the smallest disk is placed on the top, and they are on rod A.
	 * The objective of the puzzle is to move the entire stack to another rod (here
	 * considered C), obeying the following simple rules: (1)Only one disk can be
	 * moved at a time. (2)Each move consists of taking the upper disk from one of
	 * the stacks and placing it on top of another stack i.e. (3)a disk can only be
	 * moved if it is the uppermost disk on a stack. (4)No disk may be placed on top
	 * of a smaller disk.
	 */
	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	private static void type3() {
	}

	// this is not an optimal approach
	private static void type2() {
		int totalDisks = 3;
		char fromDisk = 'A';
		char toDisk = 'B';
		char extraDisk = 'C';
		shift(totalDisks, fromDisk, toDisk, extraDisk);
	}

	// Our job is to move n disk from A disk to B disk, and we have one extra C disk
	// we can divide the task in three parts
	// first move the n-1 from A -> C
	// then move nth disk from A-> B
	// then move that n-1 disks from C->B
	// time complexity is O(2^n)
	private static void type1() {
		int totalDisks = 2;
		int fromDisk = 1;
		int toDisk = 2;
		int extraDisk = 3;
		long count = toh(totalDisks, fromDisk, toDisk, extraDisk);
		System.out.println(count);
	}

	private static long toh(int n, int fromDisk, int toDisk, int extraDisk) {
		// if there is no disk to move
		if (n == 1) {
			System.out.println("move disk " + n + " from rod " + fromDisk + " to rod " + toDisk);
			return 1;
		}
		long count = 1;
		// move the n-1 from A -> C
		count += toh(n - 1, fromDisk, extraDisk, toDisk);
		// move nth disk from A-> B
		System.out.println("move disk " + n + " from rod " + fromDisk + " to rod " + toDisk);
		// move that n-1 disks from C->B
		count += toh(n - 1, extraDisk, toDisk, fromDisk);

		return count;
	}

	private static void shift(int totalDisks, char fromDisk, char toDisk, char extraDisk) {
		// if there is no disk to move
		if (totalDisks == 0) return;
		// move the n-1 from A -> C
		shift(totalDisks - 1, fromDisk, extraDisk, toDisk);
		// move nth disk from A-> B
		System.out.printf("Moving disk no: %d from %c disk to %c disk \n", totalDisks, fromDisk, toDisk);
		// move that n-1 disks from C->B
		shift(totalDisks - 1, extraDisk, toDisk, fromDisk);
	}

}
