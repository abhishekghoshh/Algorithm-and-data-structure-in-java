package com.problems.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Problem link :
 * https://www.naukri.com/code360/problems/allocate-books_1090540
 * https://www.naukri.com/code360/problems/ayush-gives-ninjatest_1097574
 * https://www.interviewbit.com/problems/allocate-books/
 *
 * Solution is :
 * https://www.youtube.com/watch?v=Z0hwjftStI4
 * https://www.youtube.com/watch?v=gYmWHvRHu-s&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=70
 * https://www.youtube.com/watch?v=2JSQIhPcHQg
 *
 * https://takeuforward.org/data-structure/allocate-minimum-number-of-pages/
 * */
public class AllocateMinimumNumberOfPages {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// binary search on answer approach
	private static void type3() {
		List<Integer> arr = List.of(97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53,
				8, 44, 68, 90, 24);
		int m = 26;
		int n = arr.size();
		// book allocation impossible
//		if (m > n) return -1;

		int low = Collections.max(arr);
		int high = arr.stream().mapToInt(Integer::intValue).sum();
		while (low <= high) {
			int mid = (low + high) / 2;
			int students = countStudents(arr, mid);
			if (students > m) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.println(low);
	}

	public static int countStudents(List<Integer> arr, int pages) {
		int n = arr.size(); // size of array
		int students = 1;
		long pagesStudent = 0;
		for (int page : arr) {
			// add pages to the current student
			pagesStudent += page;
			// add pages to the next student
			if (pagesStudent + page > pages) {
				students++;
				pagesStudent = page;
			}
		}
		return students;
	}

	// binary search on answer approach
	private static void type2() {
		List<Integer> arr = List.of(97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53,
				8, 44, 68, 90, 24);
		int m = 26;
		int n = arr.size();
		// we don't have enough book to give one book to every person
//		if (m > n) return -1;
		// at max one can take all the books so high will be sum of pages of all books
		// at min one can take only one book but if anyone is taking one book then also
		// one has to take the book with highest number of pages, so our low will be
		// maxPage(book)
		int low = Integer.MIN_VALUE, high = 0, answer = -1, mid;
		for (int item : arr) {
			if (low < item) low = item;
			high += item;
		}
		while (low <= high) {
			mid = low + ((high - low) >> 1);
			// we are checking that if mid-number of pages can be allocated to all the
			// students or not, if possible, then we are reducing our search space, but mid
			// is also one answer
			int isValid = bookAllocationPossible(arr, m, mid);
			if (isValid == 0) {
				answer = mid;
				high = mid - 1;
			} else if (isValid == 1) {
				high = mid - 1;
			} else {
				// if mid-number pages cannot be allocated, then we have to increase the number
				// of pages, so we are changing our low to mid+1
				low = mid + 1;
			}
		}
		// the answer is -1 means we can not allocate low number of pages to everyone
		// but we have more books than students so we can allocate one book to every
		// person, so can give books to every person at least but the sum of the pages
		// of any individual will not exceed 97, otherwise answer will not be -1
		// we can give the book with 97 pages to anyone and rest of the books to give
		// simultaneously, so the maximum number of pages,that can be allocated will be
		// 97 (highest number of books or low variable)
		if (answer == -1) answer = low;
		System.out.println(answer);
	}

	private static int bookAllocationPossible(List<Integer> arr, int m, int maxPages) {
		// we are initializing our students as 1 as we are starting with our first student
		int students = 1;
		int sum = 0;
		for (int page : arr) {
			// if the student is able to take the book then we are giving
			sum = sum + page;
			// the student is unable to take the book,
			// so we are giving the book to a new student
			if (sum + page > maxPages) {
				sum = page;
				students++;
			}
		}
		return Integer.compare(m, students);
	}

	private static void type1() {
		ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(25, 46, 28, 49, 24));
		int n = 5;
		int m = 4;

		// book allocation impossible
//		if (m > n) return -1;

		int low = Collections.max(arr);
		int high = arr.stream().mapToInt(Integer::intValue).sum();
		int answer = -1;
		for (int pages = low; pages <= high; pages++) {
			if (countStudents(arr, pages) == m) {
				answer = pages;
				break;
			}
		}
		System.out.println(answer);
	}

}
