package cachingsystem.lruwithttl;

public class CacheEntity<T> {

    private  T key;
    private long expiryTime;

    public CacheEntity(T key, long timeStamp) {
        this.key = key;
        this.expiryTime = timeStamp;
    }

    public T getKey() {
        return key;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public boolean isExpired(){
        return System.currentTimeMillis() >= expiryTime;
    }
}
