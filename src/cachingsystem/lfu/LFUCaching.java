package cachingsystem.lfu;

import cachingsystem.Caching;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LFUCaching implements Caching {

    Map<Integer,Integer> keyValueHashMap;
    Map<Integer,Integer> keyFreqHashMap;
    TreeMap<Integer, Node> freqListNodeMap;
    int capacity;
    public LFUCaching(int capacity){
        this.capacity = capacity;
        keyFreqHashMap = new HashMap<>(capacity);
        keyValueHashMap = new HashMap<>(capacity);
        freqListNodeMap = new TreeMap<>();
    }
    @Override
    public void put(int key, int value) {

        if(!keyValueHashMap.containsKey(key) || capacity > 0){
            Node node = new Node(key,value);
            if(keyValueHashMap.size() == capacity){

            }
        }

    }

    @Override
    public int get(int key) {
        return 0;
    }
}
