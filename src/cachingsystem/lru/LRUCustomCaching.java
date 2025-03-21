package cachingsystem.lru;

import cachingsystem.Caching;

import java.util.*;

public class LRUCustomCaching implements Caching {

    int capacity;
    Map<Integer,ValueNode> keyValueMap;
    LinkedList<ValueNode> list;
    public LRUCustomCaching(int capacity){
        if(capacity < 1){
            throw new RuntimeException("size can not be less than 1");
        }
        this.capacity = capacity; // capacity validation >0 and < 10^5 hardware size
        this.keyValueMap = new HashMap<>();
        this.list = new LinkedList<>();
    }

    @Override
    public void put(int key, int value) {
        //if key present

        // key and value validation


        ValueNode valueNode = keyValueMap.get(key);
        if(valueNode != null){
            list.remove(valueNode);
            if(valueNode.getValue() != value){ // // if we allowed value can be replaceable for existing key
                valueNode.setValue(value);
            }
            list.addLast(valueNode);// present in LinkedList class
        }else{
            if(keyValueMap.size() == capacity){
                valueNode = list.removeFirst();  // present in LinkedList class
                keyValueMap.remove(valueNode.getKey());
            }
            valueNode = new ValueNode(key,value); // creating node and reference is added in map and in linked-list it will create new node
            list.addLast(valueNode);
            keyValueMap.put(key,valueNode);
        }
    }

    @Override
    public int get(int key) {
        if(!keyValueMap.isEmpty()){
            ValueNode valueNode =  keyValueMap.get(key);
            if(valueNode == null){
                return -1;
            }
            list.remove(valueNode);
            list.addLast(valueNode);
            return valueNode.getValue();
        }
        return -1;
    }

    @Override
    public int delete(int key) {
        return Caching.super.delete(key);
    }
}
