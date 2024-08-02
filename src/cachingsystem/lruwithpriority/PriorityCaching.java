package cachingsystem.lruwithpriority;

import cachingsystem.GenericCaching;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PriorityCaching<K,V> implements GenericCaching<K,V> {

    private Map<K, CacheEntity<V>> map = new HashMap<>();
    private int capacity;
    private PriorityQueue<CacheEntity<K>> minHeap;
    private long defaultTTL;

    private int index=0;
  //  private Lock lock = new ReentrantLock();

    public PriorityCaching(int capacity, long defaultTTL){
        this.capacity = capacity;
        this.defaultTTL = defaultTTL;
        this.map = new HashMap<>(capacity);
        this.minHeap = new PriorityQueue<>(capacity, (x,y)->
                y.getPriority()== x.getPriority()?Integer.compare(x.getIndex(),y.getIndex())
        :Long.compare(y.getPriority(),x.getPriority()));
    }

    @Override
    public void put(K key, V value) {
        put(key,value,defaultTTL);
    }

    @Override
    public void put(K key, V value, long priority) {
       // lock.lock();
       try{
           evictionIfNeeded();
           CacheEntity<V> cacheEntity = new CacheEntity<>(value,priority);
           map.put(key,cacheEntity);
           minHeap.offer(new CacheEntity<>(key,priority,index++));
       }finally {
         //  lock.unlock();
       }
    }

    private void evictionIfNeeded() {
        while (map.size() >= capacity || (minHeap.peek() != null)){
            CacheEntity<K> cacheEntity = minHeap.poll();
            if(cacheEntity != null){
                map.remove(cacheEntity.getKey());
            }
        }
    }

    @Override
    public V get(K key) {
       // lock.lock();
        try {
            CacheEntity<V> cacheEntity = map.get(key);
            if(cacheEntity != null){
                return cacheEntity.getKey();
            }
            return null;
        }finally {
          //  lock.unlock();
        }
    }
}
