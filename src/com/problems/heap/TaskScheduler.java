package com.problems.heap;

import java.util.*;

/*
 *
 * problem links :
 * https://leetcode.com/problems/task-scheduler/description/
 * https://neetcode.io/problems/task-scheduling
 * https://www.naukri.com/code360/problems/task-scheduler_1070424
 *
 * Solution link :
 * https://www.youtube.com/watch?v=s8p8ukTyA2I
 * https://www.youtube.com/watch?v=eGf-26OTI-A
 *
 * https://neetcode.io/solutions/task-scheduler
 * */
public class TaskScheduler {
    // todo check again later
    public static void main(String[] args) {
        // using heap
        type1();
        type2();

        // using the freq
        type3();
        type4();
        
    }

    // TODO same as previous just that we will do it in different way
    //  Study it one more time from nick white video
    private static void type4() {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        int time = leastInterval4(tasks, n);
        System.out.println(time);
    }

    private static int leastInterval4(char[] tasks, int n) {
        int t = tasks.length;
        if (n == 0) return t;
        int[] freq = new int[26];
        // count the frequencies
        for (int task : tasks) freq[task - 'A']++;

        // first we will sort the freq array
        // so at the last we will get the max freq
        Arrays.sort(freq);
        int maxF = freq[25] - 1;
        int idleSlots = (maxF - 1) * n;
        for (int i = 24; i >= 0; i--)
            idleSlots -= Math.min(freq[i], maxF - 1);

        return (idleSlots > 0) ? (idleSlots + t) : t;
    }

    // TODO explain this solution, it is best approach
    private static void type3() {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        int time = leastInterval3(tasks, n);
        System.out.println(time);
    }

    // Whatever we do we need to understand one thing
    // that we have to accommodate all the task with same char.
    // Like if the tasks are A A A B C and n=2
    // Whatever we do we have to understand that we have to finish all the A tasks.
    // 'A' can only be done like this A--A--A, other tasks can sit in between any place
    // the minimum time required here 7
    // this is the intuition here.
    // rather thinking all the tasks if we can be greedy here to find out the max frequent tasks
    // then place that out and then place the remaining tasks in between then our problem is solved
    // just that we have to think that how many max_frequent tasks are there
    // like for this => A A A A B B B B C
    // sequence will be A B C A B * A B * A B
    public static int leastInterval3(char[] tasks, int n) {
        int t = tasks.length;
        if (n == 0) return t;
        int[] freq = new int[26];
        // count the frequencies
        for (int task : tasks) freq[task - 'A']++;
        // finding the max frequency
        int maxF = 0;
        for (int f : freq) {
            maxF = Math.max(maxF, f);
        }
        // count the most frequent tasks
        int maxFreqCharCount = 0;
        for (int f : freq) {
            if (f == maxF) maxFreqCharCount++;
        }
        // this intuition is coming from the specific section of the early return of the previous to types.
        // tasks.length is also answer because if the tasks length itself is greater than (n+1) * maxFrequency
        // that means the tasks inside it are such that it can be arranged in some fashion where no similar task will be clashing each other
        return Math.max(t, (maxF - 1) * (n + 1) + maxFreqCharCount);
    }

    // todo optimized approach from the previous using heap
    //  we will do little optimization at the cycle time
    //  check it later
    private static void type2() {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        int time = leastInterval2(tasks, n);
        System.out.println(time);
    }

    public static int leastInterval2(char[] tasks, int n) {
        int t = tasks.length;
        if (n == 0) return t;
        int[] freq = new int[26];
        for (char task : tasks) freq[task - 'A']++;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((p1, p2) -> p2 - p1);
        int distinct = 0;
        for (int f : freq) {
            if (f != 0) {
                distinct++;
                maxHeap.offer(f);
            }
        }
        // if there is only one type of tasks then we can just place all the tasks n distant.
        // for one task wit will take (n+1) space so that it can not clash with similar task
        // but for the last tasks there is no remaining tasks, so we can +1 time for that
        if (distinct == 1) return (n + 1) * (t - 1) + 1;
        Queue<int[]> queue = new LinkedList<>();
        int cycle = 0;
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            // we are incrementing the cycle on every iteration
            cycle++;
            // we are taking the max frequent element
            // then we put that in the queue
            // with next cycle time when the task can be taken again
            if (!maxHeap.isEmpty()) {
                // decrementing by 1 because on this cycle we are doing the job
                int jobCount = maxHeap.poll();
                // if job count is 1 then this is the last job else we will submit the job to queue with the new time
                if (jobCount > 1) {
                    int newTime = cycle + n;
                    int[] newJob = {(jobCount - 1), newTime};
                    queue.offer(newJob);
                }
            }
            // we have done some little optimization here
            if (!queue.isEmpty() && cycle <= queue.peek()[1]) {
                if (maxHeap.isEmpty() && cycle <= queue.peek()[1]) {
                    cycle += (queue.peek()[1] - cycle);
                    maxHeap.offer(queue.poll()[0]);
                } else if (cycle == queue.peek()[1]) {
                    maxHeap.offer(queue.poll()[0]);
                }
            }
        }
        return cycle;
    }


    // todo optimized approach using heap
    //  this will be similar to how a processor will do
    //  and we will add the job freq and time at which the job would be taken again
    //  we will also use a clock and increment the clock by 1 in every iteration
    //  and we will use 2 data structure one heap and one queue
    //  heap for for pulling the most freq item, once we will pull any item and e
    private static void type1() {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        int time = leastInterval1(tasks, n);
        System.out.println(time);
    }

    public static int leastInterval1(char[] tasks, int n) {
        int t = tasks.length;
        if (n == 0) return t;
        int[] freq = new int[26];
        // calculating the freq of each task
        for (char task : tasks) freq[task - 'A']++;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int distinct = 0;
        for (int f : freq) {
            if (f != 0) {
                distinct++;
                maxHeap.offer(f);
            }
        }
        // if there is only one type of tasks then we can just place all the tasks n distant.
        // for one task wit will take (n+1) space so that it can not clash with similar task
        // but for the last tasks there is no remaining tasks, so we can +1 time for that
        if (distinct == 1) return (n + 1) * (t - 1) + 1;

        Queue<int[]> queue = new LinkedList<>();
        int time = 0;
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            // we are incrementing the cycle on every iteration
            time++;
            // we are taking the max frequent element and put it in the queue with next cycle time when the task can be taken again
            if (!maxHeap.isEmpty()) {
                // decrementing by 1 because on this cycle we are doing the job
                int jobCount = maxHeap.poll();
                // if job count is 1 then this is the last job else we will submit the job to queue with the new time
                if (jobCount > 1) {
                    int newTime = time + n;
                    int[] newJob = {(jobCount - 1), newTime};
                    queue.offer(newJob);
                }
            }
            // if the first task on the queue can be taken now, then we are adding the task again in the heap
            if (!queue.isEmpty() && queue.peek()[1] == time)
                maxHeap.offer(queue.poll()[0]);
        }
        return time;
    }
}
