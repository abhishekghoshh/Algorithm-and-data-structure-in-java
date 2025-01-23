package com.problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem link :
 * https://leetcode.com/problems/rotting-oranges/
 * https://www.naukri.com/code360/problems/701655
 * https://www.naukri.com/code360/problems/rotting-oranges_701655
 *
 * Solution link :
 * https://www.youtube.com/watch?v=yf3oUhkvqA0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=10
 *
 * https://takeuforward.org/data-structure/rotten-oranges-min-time-to-rot-all-oranges-bfs/
 */
public class RottenOranges {

    // 2 represents a rotten orange
    // 1 represents a Fresh orange
    // 0 represents an Empty Cell
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }


    // TODO this is not the most optimized approach
    //  here we are visiting one node more than one time
    //  only thing is here we are not using any extra spaces other than recursion stack
    // using dfs to traverse the nodes
    // here we are not using any extra queue or stack
    // here we will directly mark on the matrix cell itself
    private static void type3() {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int answer = orangesRotting3(grid);
        System.out.println(answer);

    }

    // we have to use any marker on cell that is not 0,1 or 2
    // because these values will already be present on the matrix
    // we use anything greater than 2
    // we will treat any 2 as the starting time
    public static int orangesRotting3(int[][] grid) {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                // we will start the transform once we find any rotten apple
                // we will start the time with 2 treating as 0
                // we go as far as possible with a single rotten orange
                if (grid[i][j] == 2) orangesRotting3(i, j, grid, 2);

        // here again we will initialize with time as 2 rather than 0
        // as the initial rotten oranges were marked as 2
        // at last we will subtract with 2 with the max time
        int time = 2; // we could also use a stack variable
        for (int[] row : grid) {
            for (int cell : row) {
                // cell is 1 means there is at least one cell that is not traversed
                if (cell == 1) return -1;
                //
                time = Math.max(time, cell);
            }
        }
        // we started with time 2 so we will decrease the
        return (time - 2);
    }

    // using dfs with recursion stack
    public static void orangesRotting3(int i, int j, int[][] grid, int time) {
        if (isOutOfBounds(i, j, grid)
                || isCellEmpty(i, j, grid)
                || isCellAlreadyVisited(i, j, time, grid))
            return;
        // updating the rotting time for this cell
        grid[i][j] = time;
        // call all 4 sides with time + 1
        orangesRotting3(i - 1, j, grid, time + 1);
        orangesRotting3(i + 1, j, grid, time + 1);
        orangesRotting3(i, j - 1, grid, time + 1);
        orangesRotting3(i, j + 1, grid, time + 1);
    }

    private static boolean isCellEmpty(int i, int j, int[][] grid) {
        return grid[i][j] == 0;
    }

    private static boolean isOutOfBounds(int i, int j, int[][] grid) {
        return i < 0 || i >= grid.length || j < 0 || j >= grid[0].length;
    }

    private static boolean isCellAlreadyVisited(int i, int j, int time, int[][] grid) {
        // essentially, we could use just 1 < grid[i][j]
        // that means anything that already is marked, but
        //  we are forgetting one edge condition.
        // if a cell is already marked by a far rotten cell but there is a close rotten cell.
        // so we have to update this cell value with the lesser value.
        // so that is why we have added this grid[i][j] < time
        // that means the cell is already visited by a nearest rotten oranges,
        // and we do not need to visit this again
        return 1 < grid[i][j] && grid[i][j] < time;
    }




    // We will BFS here
    // we will store the initial rotten oranges in a queue
    // then add the second then third and so on
    // and every time we will increment time variable
    private static void type2() {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int answer = orangesRotting2(grid);
        System.out.println(answer);
    }

    // we will use level wise traversal
    private static int orangesRotting2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int totalOranges = 0;
        // Put the position of all rotten oranges in the queue
        // count the number of fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
                // if the cell is not 0 means there is an orange, either good or bad
                if (grid[i][j] != 0) totalOranges++;
            }
        }
        // if the total oranges are 0 then we do not need to do anything
        if (totalOranges == 0) return 0;
        int time = 0, orangeCount = 0;

        // rather than manually checking, we had added the dx and dy in an array
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        // bfs starting from initially rotten oranges
        while (!queue.isEmpty()) {
            // take the current size of the queue
            // because we need to check that for the current set of oranges
            // it is possible to rot new oranges or not
            int size = queue.size();
            orangeCount += size;
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                // finding all 4 side nodes
                for (int j = 0; j < 4; j++) {
                    int x = point[0] + dx[j];
                    int y = point[1] + dy[j];
                    // if the index is out of bound and the cell is having fresh oranges then we will
                    // set it to 2 to mark it is rotten
                    if (isInBounds(x, y, grid) && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            // if queue is not empty that means the previous label successfully added some new rotten oranges
            // which will take one more unit of time
            if (!queue.isEmpty()) time++;

        }
        return (totalOranges == orangeCount) ? time : -1;
    }

    private static boolean isInBounds(int x, int y, int[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    // using bfs with queue,
    // we will start from the oranges that are rotten
    // we will store the point and apply bfs on them
    private static void type1() {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int answer = orangesRotting1(grid);
        System.out.println(answer);
    }

    private static int orangesRotting1(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int freshOranges = 0, max = 0;
        int x, y, time;
        Queue<Point> queue = new LinkedList<>();
        // we will count fresh oranges and store rotten oranges in to queue
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // we will also store the time along with the coordinates
                if (grid[i][j] == 2) queue.offer(new Point(i, j, 0));
                if (grid[i][j] == 1) freshOranges++;
            }
        }
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            x = point.x;
            y = point.y;
            time = point.time;
            // checking the max time
            max = Math.max(max, time);
            // visiting all 4 sides of the cell and reduce the fresh oranges count
            if (y > 0 && grid[x][y - 1] == 1) {
                freshOranges--;
                grid[x][y - 1] = 2;
                queue.offer(new Point(x, y - 1, time + 1));
            }
            if (x > 0 && grid[x - 1][y] == 1) {
                freshOranges--;
                grid[x - 1][y] = 2;
                queue.offer(new Point(x - 1, y, time + 1));
            }
            if (x < r - 1 && grid[x + 1][y] == 1) {
                freshOranges--;
                grid[x + 1][y] = 2;
                queue.offer(new Point(x + 1, y, time + 1));
            }
            if (y < c - 1 && grid[x][y + 1] == 1) {
                freshOranges--;
                grid[x][y + 1] = 2;
                queue.offer(new Point(x, y + 1, time + 1));
            }
        }
        // if the fresh oranges count is not 0 then we will return -1
        return (freshOranges == 0) ? max : -1;
    }

    private static class Point {
        public int x;
        public int y;
        public int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
