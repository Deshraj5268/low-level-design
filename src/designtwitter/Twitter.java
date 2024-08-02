package designtwitter;

import java.util.*;

public class Twitter {

    Map<Integer, Set<Integer>> followeesMap;
    Map<Integer,List<Tweet>> tweetMap;
    static int timeStamp;
    int maxFeedCount;
    public Twitter() {
        maxFeedCount = 10;
        timeStamp = 0;
        followeesMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweets = tweetMap.get(userId);
        if(tweets == null){
            // follow itself if it's first time
            follow(userId,userId);
            tweets = new LinkedList<>();
        }
        Tweet newTweet = new Tweet(tweetId,timeStamp++);// timeStamp is shows latest tweetTime
        tweets.add(0,newTweet); // add in the staring which show latest tweet
        tweetMap.put(userId,tweets);
    }

    public List<Integer> getNewsFeed(int userId) {


        // get all my followees
        Set<Integer> myFollowees  = followeesMap.get(userId);
        if(myFollowees == null || myFollowees.isEmpty()){
            return new LinkedList<>();// no feeds
        }
        PriorityQueue<Tweet> minHeap = finTopKFeeds(myFollowees);

        // return top max tweet
        List<Integer> topFeeds = new LinkedList<>();
        while (!minHeap.isEmpty()){
            topFeeds.add(minHeap.poll().getTweetId());
        }
        Collections.sort(topFeeds,Collections.reverseOrder());
        return topFeeds;

    }

    private  PriorityQueue<Tweet> finTopKFeeds(Set<Integer> myFollowees) {
        PriorityQueue<Tweet> minHeap = new PriorityQueue<>(
                (tweet1,tweet2)->tweet1.getTweetPostedTime()-tweet2.getTweetPostedTime());
        for(Integer followeeId: myFollowees){
            List<Tweet> followeedTweets = tweetMap.get(followeeId); // recent tweets of users
            if(followeedTweets != null){
                for (Tweet recentTweet:followeedTweets){
                    if(minHeap.size()<maxFeedCount){
                        minHeap.add(recentTweet);
                    }else {
                        if(minHeap.poll().tweetPostedTime > recentTweet.getTweetPostedTime()){ // older tweet not useful
                            break;
                        }else{
                            minHeap.poll();//remove old
                            minHeap.add(recentTweet);
                        }
                    }
                }
            }
        }
        return minHeap;
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> followeesUser = followeesMap.get(followerId);
        if(followeesUser == null){
            followeesUser = new HashSet<>();
        }
        followeesUser.add(followeeId);
        followeesMap.put(followerId,followeesUser);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followeesUser = followeesMap.get(followerId);
        if(followeeId == followerId || followeesUser == null){ // itself or no follower is not allowed
            return;
        }
        followeesUser.remove(followeeId);
    }
}
