package cachingsystem.lruwithpriority;

public class CacheEntity<T> {

    private  T key;

    private long priority;

    private int index;
    private long expiryTime;

   /* public CacheEntity(T key, long timeStamp) {
        this.key = key;
        this.expiryTime = timeStamp;
    }*/

    public CacheEntity(T key, long priority) {
        this.key = key;
        this.priority = priority;
    }

    public CacheEntity(T key, long priority,int index) {
        this.key = key;
        this.priority = priority;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
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
