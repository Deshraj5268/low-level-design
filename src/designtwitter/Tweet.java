package designtwitter;

public class Tweet {

    int tweetId;
    int tweetPostedTime;

    public Tweet(int tweetId, int tweetPostedTime) {
        this.tweetId = tweetId;
        this.tweetPostedTime = tweetPostedTime;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", tweetTimeStamp=" + tweetPostedTime +
                '}';
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public int getTweetPostedTime() {
        return tweetPostedTime;
    }

    public void setTweetPostedTime(int tweetPostedTime) {
        this.tweetPostedTime = tweetPostedTime;
    }
}
