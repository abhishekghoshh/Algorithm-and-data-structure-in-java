package com.problems.greedy;

import java.util.Arrays;

/*
 * 
 * problem links :
 * https://www.naukri.com/code360/problems/job-sequencing-problem_1169460
 * https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
 * 
 * Solution video :
 * https://www.youtube.com/watch?v=LjPx4wQaRIs&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=48
 *
 * https://takeuforward.org/data-structure/job-sequencing-problem/
 * */
public class JobSchedulingProblem {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// same as type1
	private static void type2() {
		int[][] jobs = {{1, 1, 30}, {2, 3, 40}, {3, 2, 10}};
		Arrays.sort(jobs, (job1, job2) -> Integer.compare(job2[2], job1[2]));
		int maxDeadline = 0;
		for (int[] job : jobs)
			if (maxDeadline < job[1]) maxDeadline = job[1];
		boolean[] slots = new boolean[maxDeadline + 1];
		int profit = 0, deadline, jobsTaken = 0;
		for (int[] job : jobs) {
			deadline = job[1];
			while (deadline >= 1 && slots[deadline]) deadline--;
			if (deadline != 0) {
				slots[deadline] = true;
				profit += job[2];
				jobsTaken++;
			}
		}
		System.out.println("Jobs taken " + jobsTaken);
		System.out.println("profit is " + profit);
	}

	// time complexity is O(n*log(n))+ O(n^2)
	// space complexity is O(n)
	private static void type1() {
		Job[] jobs = {
				new Job(1, 4, 20),
				new Job(2, 1, 10),
				new Job(3, 1, 40),
				new Job(4, 1, 30)
		};
		// we will sort the jobs by their profit in descending
		// so the highest-profit job can be taken at the first
		Arrays.sort(jobs, (job1, job2) -> Integer.compare(job2.profit, job1.profit));
		// intuition is
		// if the job has a deadline k that means the job can be done at kth time or
		// less than that,
		// but they will try to do that job at kth then 1..k-1 will be vacant
		// which can be filled by the later jobs
		// we are making sure that the higher profit jobs has the better chance of
		// picking than the lower profits
		// out of the all the jobs if a job has T deadline which is greater than all
		// others then at max we can take T jobs
		int maxDeadline = 0;
		for (Job job : jobs)
			if (maxDeadline < job.deadline) maxDeadline = job.deadline;
		// for simplicity, we are taking T+1
		// so that we don't have to think about 0 index
		boolean[] slots = new boolean[maxDeadline + 1];
		int profit = 0, deadline, jobsTaken = 0;
		for (Job job : jobs) {
			deadline = job.deadline;
			// we can take the job if that time slot is vacant if false
			// we are checking that from the deadline time to 1st time if there is any
			// vacant slot deadline is in terms of 0
			// which cannot be 0
			// so, we will go till 1st day
			while (deadline >= 1 && slots[deadline]) deadline--;
			// if deadline is not 0 then we have a vacant position to fill in
			if (deadline != 0) {
				slots[deadline] = true;
				profit = profit + job.profit;
				jobsTaken++;
			}
		}
		System.out.println("Jobs taken " + jobsTaken);
		System.out.println("profit is " + profit);
	}

	public static class Job {
		int jobId, deadline, profit;

		Job(int id, int deadline, int profit) {
			this.jobId = id;
			this.deadline = deadline;
			this.profit = profit;
		}

		@Override
		public String toString() {
			return "Job [jobId=" + jobId + ", profit=" + profit + ", deadline=" + deadline + "]";
		}
	}
}
