package cachingsystem;

import cachingsystem.lru.LRUCaching;
import cachingsystem.lru.LRUCustomCaching;
import cachingsystem.lruwithpriority.PriorityCaching;
import cachingsystem.lruwithttl.PriorityExpiryCaching;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LRUDriver {

    public static void main(String[] args) {


        testLRUCaching();
        testCUSTOMLRUCaching();
        testCachingWithTTL();

       // testPriorityCaching();

    }

    private static void testPriorityCaching() {

        GenericCaching<Character,Integer> caching = new PriorityCaching<>(3,500);

        caching.put('a',1,5);
        caching.put('b',2,6);
        caching.put('c',3,7);
        System.out.println(caching.get('c'));
        caching.put('d',6,5);
        caching.put('b',4,5);
        System.out.println(caching.get('b'));

    }

    private static void testCachingWithTTL() {
        GenericCaching<String, Integer> cache = new PriorityExpiryCaching<>(5, 10000);
        cache.put("key1", 1);
        cache.put("key2", 2, 5000); // Custom TTL for key2
        cache.put("key3", 3);
        System.out.println(cache.get("key1")); // Output: 1
        System.out.println(cache.get("key2")); // Output: 2
        System.out.println(cache.get("key3")); // Output: 3

        // Wait for 10 seconds to see entries expire
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cache.get("key1")); // Output: null (expired)
        System.out.println(cache.get("key2"));
        System.out.println(cache.get("key3"));



    }

    private static void testCUSTOMLRUCaching() {

        System.out.println("testCUSTOMLRUCaching");
        Caching caching = new LRUCustomCaching(2);
        caching.put(1,11);
       // caching.put(1,21);
        caching.put(3,31);
        caching.put(3,41);
        caching.put(6,61);
        System.out.println(caching.get(1));

        caching.put(6,51);
        System.out.println(caching.get(3));
        System.out.println(caching.get(6));
    }

    public static void testLRUCaching(){
        System.out.println("testLRUCaching");
        Caching caching = new LRUCaching(4);
        caching.put(1,11);
        caching.put(2,21);
        caching.put(3,31);
        caching.put(4,41);
        caching.put(6,61);
        System.out.println(caching.get(1));


        caching.put(6,51);
        System.out.println(caching.get(3));
        System.out.println(caching.get(6));
    }
}
