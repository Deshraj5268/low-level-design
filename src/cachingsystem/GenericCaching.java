package cachingsystem;

public interface GenericCaching<K,V> {

    void put(K key,V value);

    void put(K key,V value,long ttl);
    V get(K key);

}
