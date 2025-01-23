package com.problems.heap;

import java.util.*;

/*
 *
 * problem links :
 * https://leetcode.com/problems/design-twitter/description/
 * https://neetcode.io/problems/design-twitter-feed
 * https://www.naukri.com/code360/problems/design-twitter_8380711
 *
 * Solution link :
 * https://www.youtube.com/watch?v=pNichitDD2E
 *
 * https://neetcode.io/solutions/design-twitter
 * */
public class DesignTwitter {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo most optimized approach
    //  merge k list technique
    //  this code is modularized
    //  we have covered almost all the optimization,
    //  but in getFeeds method still we are doing brute force approach
    //  we can use the technique of merge k sorted list using a maxHeap
    private static void type4() {
        Twitter4 twitter = new Twitter4();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    static class Twitter4 {

        HashSet<Integer>[] followeeSet = new HashSet[501];
        List<Integer>[] tweetMap = new List[501];
        int[] timeMap = new int[10001];
        int time = 0;

        public Twitter4() {
        }

        public void postTweet(int userId, int tweetId) {
            time++; // incrementing the time everytime someone creates a tweet
            if (tweetMap[userId] == null)
                tweetMap[userId] = new ArrayList<>();
            tweetMap[userId].add(tweetId);
            timeMap[tweetId] = time;
        }


        public List<Integer> getNewsFeed(int userId) {
            // latest tweet will come at the top in the heap
            PriorityQueue<UserTweets> maxHeap = new PriorityQueue<>((tl1, tl2) -> timeMap[tl2.getLastTweetId()] - timeMap[tl1.getLastTweetId()]);
            // adding self tweet list pointer to max heap
            if (tweetMap[userId] != null && !tweetMap[userId].isEmpty()) {
                List<Integer> tweets = tweetMap[userId];
                maxHeap.offer(new UserTweets(tweets));
            }
            // adding followee tweets to the map heap
            if (followeeSet[userId] != null) {
                for (int followeeId : followeeSet[userId])
                    if (tweetMap[followeeId] != null && !tweetMap[followeeId].isEmpty()) {
                        List<Integer> tweets = tweetMap[followeeId];
                        maxHeap.offer(new UserTweets(tweets));
                    }
            }
            List<Integer> tweets = new ArrayList<>();
            // now we have all the pointers in the max heap, we can just fetch top 10 tweets
            // by using merge k list approach
            for (int i = 0; i < 10 && !maxHeap.isEmpty(); i++) {
                UserTweets userTweets = maxHeap.poll();
                tweets.add(userTweets.pollLastTweet());
                // if the user tweet list is empty then we will not that user tweet list to the map heap
                if (!userTweets.isEmpty())
                    maxHeap.offer(userTweets);
            }
            return tweets;
        }

        public void follow(int followerId, int followeeId) {
            if (followeeSet[followerId] == null)
                followeeSet[followerId] = new HashSet<>();
            followeeSet[followerId].add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followeeSet[followerId] != null)
                followeeSet[followerId].remove(followeeId);
        }

        static class UserTweets {
            public List<Integer> tweets;
            int i;

            public UserTweets(List<Integer> tweets) {
                this.tweets = tweets;
                i = tweets.size() - 1;
            }

            int pollLastTweet() {
                return tweets.get(i--);
            }

            int getLastTweetId() {
                return tweets.get(i);
            }

            boolean isEmpty() {
                return i == -1;
            }
        }
    }

    // as we know the range of userid and tweet id
    // we use some from of an array
    private static void type3() {
        Twitter3 twitter = new Twitter3();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    static class Twitter3 {

        HashSet<Integer>[] followeeSet;
        List<Integer>[] tweetMap;
        int[] timeMap;
        int time = 0;

        public Twitter3() {
            followeeSet = new HashSet[501];
            tweetMap = new List[501];
            timeMap = new int[10001];
        }

        public void postTweet(int userId, int tweetId) {
            time++;
            if (tweetMap[userId] == null)
                tweetMap[userId] = new ArrayList<>();
            tweetMap[userId].add(tweetId);
            timeMap[tweetId] = time;
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> tweets = new ArrayList<>();
            // self tweets
            if (tweetMap[userId] != null && !tweetMap[userId].isEmpty()) {
                tweets.addAll(tweetMap[userId]);
            }
            // other tweets
            if (followeeSet[userId] != null)
                for (int followeeId : followeeSet[userId])
                    if (tweetMap[followeeId] != null &&
                            !tweetMap[followeeId].isEmpty())
                        tweets.addAll(tweetMap[followeeId]);
            tweets.sort((t1, t2) -> timeMap[t2] - timeMap[t1]);
            int n = Math.min(10, tweets.size());
            return tweets.subList(0, n);
        }

        public void follow(int followerId, int followeeId) {
            if (followeeSet[followerId] == null)
                followeeSet[followerId] = new HashSet<>();
            followeeSet[followerId].add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followeeSet[followerId] != null)
                followeeSet[followerId].remove(followeeId);
        }

    }

    // todo little optimized from the last type
    //  as all the tweets have unique ids so we can just save (tweetId,time) in a separate map
    //  rather saving the time in the tweet map
    private static void type2() {
        Twitter2 twitter = new Twitter2();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    static class Twitter2 {
        Map<Integer, HashSet<Integer>> followeeSet;
        Map<Integer, List<Integer>> tweetMap;
        Map<Integer, Integer> timeMap;
        int time = 0;

        public Twitter2() {
            followeeSet = new HashMap<>();
            tweetMap = new HashMap<>();
            timeMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            time++;
            tweetMap.getOrDefault(userId, new ArrayList<>()).add(tweetId);
            timeMap.put(tweetId, time);
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> tweets = new ArrayList<>();
            // adding the tweets of himself
            if (tweetMap.containsKey(userId) && !tweetMap.get(userId).isEmpty()) {
                tweets.addAll(tweetMap.get(userId));
            }
            // adding others tweets
            if (followeeSet.containsKey(userId)) {
                for (int followeeId : followeeSet.get(userId))
                    if (tweetMap.containsKey(followeeId) && !tweetMap.get(followeeId).isEmpty())
                        tweets.addAll(tweetMap.get(followeeId));
            }
            tweets.sort((t1, t2) -> timeMap.get(t2) - timeMap.get(t1));
            int N = Math.min(10, tweets.size());
            return tweets.subList(0, N);
        }

        public void follow(int followerId, int followeeId) {
            followeeSet.getOrDefault(followerId, new HashSet<>())
                    .add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followeeSet.containsKey(followerId))
                followeeSet.get(followerId).remove(followeeId);
        }

    }

    // TODO optimize things here
    //  this is very brute force technique
    //  we do not need to store the tweet time along with tweet id
    //  as the tweet id are distinct we can create a separate map for tweet id
    //  Also get news feed method is also getting all feeds then sorting on that
    //  so this method can also be refactored
    private static void type1() {
        Twitter1 twitter = new Twitter1();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    static class Twitter1 {
        // user -> Set of user
        Map<Integer, HashSet<Integer>> followeeSet;

        // user -> list of tweet
        Map<Integer, List<Tweet>> tweetMap;
        int time = 0;

        public Twitter1() {
            followeeSet = new HashMap<>();
            tweetMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            time++; // updating the time
            // adding the tweet to the user map
            tweetMap.getOrDefault(userId, new ArrayList<>())
                    .add(new Tweet(tweetId, time));
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Tweet> allTweetList = new ArrayList<>();
            // adding the tweets of himself
            if (tweetMap.containsKey(userId)
                    && !tweetMap.get(userId).isEmpty()) {
                allTweetList.addAll(tweetMap.get(userId));
            }
            // adding others tweets
            if (followeeSet.containsKey(userId)) {
                for (int followeeId : followeeSet.get(userId))
                    if (tweetMap.containsKey(followeeId) &&
                            !tweetMap.get(followeeId).isEmpty())
                        allTweetList.addAll(tweetMap.get(followeeId));
            }
            allTweetList.sort((t1, t2) -> t2.time - t1.time);
            // extracting the tweets
            List<Integer> tweets = new ArrayList<>();
            int N = Math.min(10, allTweetList.size());
            for (int i = 0; i < N; i++)
                tweets.add(allTweetList.get(i).tweetId);
            return tweets;
        }

        public void follow(int followerId, int followeeId) {
            followeeSet.getOrDefault(followerId, new HashSet<>())
                    .add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followeeSet.containsKey(followerId))
                followeeSet.get(followerId).remove(followeeId);
        }

        static class Tweet {
            int tweetId;
            int time;

            public Tweet(int tweetId, int time) {
                this.tweetId = tweetId;
                this.time = time;
            }
        }
    }
}
