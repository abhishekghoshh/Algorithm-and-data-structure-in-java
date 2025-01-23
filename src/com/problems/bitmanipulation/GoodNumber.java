package com.problems.bitmanipulation;

/*
 * Problem link :
 *
 * Solution link
 *
 *
 * */
// A number z is good when it can be divided in two numbers x,y such that x^y=z x&y=0
public class GoodNumber {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// x or y will be 0 only if in z we have only one 1 bit
	// then it will have only 1 bit to distribute among x and y
	// example 0,2,4,8,16
	private static void type3() {
		int[] arr = { 6, 11, 0, 8, 4, 32, 11 };
		int count = 0;
		int num, numberOfOne, reminder;
		for (int i = 0; i < arr.length; i++) {
			num = arr[i];
			numberOfOne = 0;
			while (num > 0) {
				reminder = num % 2;
				if (reminder == 1) {
					numberOfOne++;
				}
				num = num / 2;
			}
			count = count + (numberOfOne > 1 ? 1 : 0);
		}
		System.out.println(count);
	}

	// Optimized approach with extra space
	// time complexity n*log(k+1))
	// space complexity O(1)
	// In previous type we have seen everything is dependent on z bit
	// we don't need to create x and y, we just need to know if x or y is 0 or not
	// x, y bit only got any value when z bit is 1
	// so ultimately we need to divide the 1 bits to x and y
	// then count x and y 1th bit's
	private static void type2() {
		int[] arr = { 6, 11, 0, 8, 4, 32 };
		int count = 0;
		int num, leftSum, rightSum, reminder;
		boolean toggle = true;
        for (int j : arr) {
            num = j;
            leftSum = rightSum = 0;
            while (num > 0) {
                reminder = num % 2;
                if (reminder == 1) {
                    if (toggle) {
                        leftSum++;
                        toggle = false;
                    } else {
                        rightSum++;
                        toggle = true;
                    }
                }
                num = num / 2;
            }
            count = count + (leftSum != 0 && rightSum != 0 ? 1 : 0);
        }
		System.out.println(count);
	}

	// Optimized approach with extra space
	// time complexity n*log(k+1))
	// space complexity O(3*log(k+1))
	private static void type1() {
		int[] arr = { 6, 11, 0, 8, 4, 32 };
		int count = 0;
        for (int num : arr) {
            if (num != 0) {
                int[] binary = inBinary(num);
                count = ifGoodNumber(binary) ? (count + 1) : count;
            }
        }
		System.out.println(count);
	}

	// make the binary of z
	private static int[] inBinary(int num) {
		int length = 0, num_ = num, id;
		while (num_ > 0) {
			num_ = num_ / 2;
			length++;
		}
		int[] binary = new int[length];
		num_ = num;
		id = 0;
		while (num_ > 0) {
			binary[length - id - 1] = num_ % 2;
			id++;
			num_ = num_ / 2;
		}
		return binary;
	}

	// let's say z is 101 in binary
	// we can divide this bits
	// we can see one thing
	// if the z bit is 1 then we can assign x and y bits as either 1,0 or 0,1 and
	// x^y=z x&y=0 condition satisfies
	// if the z bit is 0 then we can assign x and y bits as either 0,0 and x^y=z
	// x&y=0 condition satisfies
	// lastly we are checking if anu number is 0 or not
	private static boolean ifGoodNumber(int[] bin) {
		int[] left = new int[bin.length];
		int[] right = new int[bin.length];
		boolean flick = true;
		for (int i = 0; i < bin.length; i++) {
			if (bin[i] == 0) {
				left[i] = 0;
				right[i] = 0;
			} else {
				if (flick) {
					left[i] = 1;
					right[i] = 0;
					flick = false;
				} else {
					left[i] = 0;
					right[i] = 1;
					flick = true;
				}
			}
		}
		int leftSum = 0, rightSum = 0;
		for (int i = 0; i < bin.length; i++) {
			leftSum = leftSum + left[i];
			rightSum = rightSum + right[i];
		}
		return leftSum != 0 && rightSum != 0;
	}
}
