package ratelimiting;

import ratelimiting.tokenbucketalgo.TokenBucket;

public class TokenBucketDriver {

    public static void main(String[] args) throws InterruptedException {
        TokenBucket limiter = new TokenBucket(5, 2); // 5 tokens max, 2 per sec refill

        for (int i = 0; i < 15; i++) {
            boolean allowed = limiter.allowedRequest(1);
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Rejected"));
            Thread.sleep(400); // Simulate request interval
        }
    }
}
