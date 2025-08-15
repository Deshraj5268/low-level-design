package ratelimiting.tokenbucketalgo;

import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket {
    private AtomicLong tokenBucket; //
    private final long capacity;
    private final long refillRatePerSecond;
    private volatile long lastRefillTimeStamp;

    public TokenBucket(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.lastRefillTimeStamp = System.nanoTime();
        tokenBucket = new AtomicLong(capacity); // initialize with full capacity
    }

    public synchronized boolean allowedRequest(int amount){
        refill();// refill bucket before serving request

        if(tokenBucket.get() >= amount){
            tokenBucket.addAndGet(-amount);
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        long elapsedTime = (now - lastRefillTimeStamp) / 1_000_000_000;

        if(elapsedTime > 0){
            long newToken = elapsedTime * refillRatePerSecond;
            long currentToken = tokenBucket.get();
            long updatedToken = Math.min(tokenBucket.get(), currentToken+newToken);
            tokenBucket.set(updatedToken);
            lastRefillTimeStamp = now;

        }
    }


}
