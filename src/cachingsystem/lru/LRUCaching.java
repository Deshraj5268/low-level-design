package cachingsystem.lru;

import cachingsystem.Caching;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCaching implements Caching {

    int capacity;
    Map<Integer,ValueNode> keyValueMap;
    public LRUCaching(int capacity){
        this.capacity = capacity;
        this.keyValueMap = new LinkedHashMap<Integer,ValueNode>(capacity,.75f,true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, ValueNode> entry) {
                return size() > capacity;
            }
        };
    }

    @Override
    public void put(int key, int value) {
        keyValueMap.put(key,new ValueNode(key,value));
    }

    @Override
    public int get(int key) {
        if(!keyValueMap.isEmpty()){
            ValueNode valueNode =  keyValueMap.get(key);
            if(valueNode == null){
                return -1;
            }
            return valueNode.getValue();
        }
        return -1;
    }

    @Override
    public int delete(int key) {
        return Caching.super.delete(key);
    }
}
