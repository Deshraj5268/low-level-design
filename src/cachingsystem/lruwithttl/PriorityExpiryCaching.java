package cachingsystem.lruwithttl;

import cachingsystem.GenericCaching;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityExpiryCaching<K,V> implements GenericCaching<K,V> {

    private Map<K,CacheEntity<V>> map = new HashMap<>();
    private int capacity;
    private PriorityQueue<CacheEntity<K>> minHeap;
    private long defaultTTL;
    private Lock lock = new ReentrantLock();

    public PriorityExpiryCaching(int capacity,long defaultTTL){
        this.capacity = capacity;
        this.defaultTTL = defaultTTL;
        this.map = new HashMap<>(capacity);
        this.minHeap = new PriorityQueue<>(capacity, (x,y)->Long.compare(x.getExpiryTime(),y.getExpiryTime()));
    }

    @Override
    public void put(K key, V value) {
        put(key,value,defaultTTL);
    }

    @Override
    public void put(K key, V value, long ttl) {
        lock.lock();
       try{
           evictionIfNeeded();
           long expiryTime = System.currentTimeMillis()+ttl;
           CacheEntity<V> cacheEntity = new CacheEntity<>(value,expiryTime);
           map.put(key,cacheEntity);
           minHeap.offer(new CacheEntity<>(key,expiryTime));
       }finally {
           lock.unlock();
       }
    }

    private void evictionIfNeeded() {
        while (map.size() >= capacity || (!minHeap.isEmpty() && minHeap.peek().isExpired())){
            CacheEntity<K> cacheEntity = minHeap.poll();
            if(cacheEntity != null){
                map.remove(cacheEntity.getKey());
            }
        }
    }

    @Override
    public V get(K key) {
        lock.lock();
        try {
            CacheEntity<V> cacheEntity = map.get(key);
            if(cacheEntity != null && !cacheEntity.isExpired()){
                return cacheEntity.getKey();
            }
            return null;
        }finally {
            lock.unlock();
        }
    }
}
