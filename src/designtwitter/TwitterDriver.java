package designtwitter;

import java.util.List;

/*
* https://leetcode.com/problems/design-twitter/description/
* */
public class TwitterDriver {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

       /* twitter.postTweet(100,1);
        twitter.postTweet(100,2);
        twitter.postTweet(100,8);
        twitter.postTweet(100,9);

        twitter.postTweet(101,12);
        twitter.postTweet(101,7);
        twitter.postTweet(101,6);
        twitter.postTweet(101,3);

        twitter.postTweet(102,4);
        twitter.postTweet(102,5);
        twitter.postTweet(102,10);

        twitter.follow(100,101);
        twitter.follow(100,102);*/

        //["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
        // [[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]
        twitter.postTweet(1,5);
        twitter.getNewsFeed(1);
        twitter.follow(1,2);
        twitter.postTweet(2,6);
        List<Integer> topTenTweet = twitter.getNewsFeed(1);
        System.out.println("top tweet " + topTenTweet.toString());

        //["Twitter","postTweet","postTweet","getNewsFeed"]
        //[[],[1,5],[1,3],[1]]

        twitter = new Twitter();
        twitter.postTweet(1,5);
        twitter.postTweet(1,3);
        twitter.getNewsFeed(1);

         topTenTweet = twitter.getNewsFeed(1);
        System.out.println("top tweet " + topTenTweet.toString());

    }

}
