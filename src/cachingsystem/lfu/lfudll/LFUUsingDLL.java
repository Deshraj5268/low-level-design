package cachingsystem.lfu.lfudll;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/*
* https://www.youtube.com/watch?v=0PSB9y8ehbk
* https://www.enjoyalgorithms.com/blog/least-frequently-used-cache
* */
public class LFUUsingDLL {

    Map<Integer, LFUDLLNode> keyValMap;// key, value
    Map<Integer, LinkedList<LFUDLLNode>> freqListMap; // freq, listNode of node
    int minFreq;
    int capacity;



    public LFUUsingDLL(int capacity){
        this.capacity = capacity;
        this.keyValMap = new HashMap<>();
        this.freqListMap = new HashMap<>();
    }



    /*
    * Map< key, Node>
    FeqMap<freq, List<Node>
    * Node { key, val, freq}
    *
    * if key present
    * increase the freq of that key into freqMap
    * else
    *   if capacity is full then
    *     remove the key of min freq
    *     remove that node from keyVal map as well
    *
    *    create new node with freq = 1
    *    add into keyVal map
    *    add into freqMap
    *
    *
    * */
    public void put(int key, int val){
        LFUDLLNode lfudllNode = keyValMap.get(key);

        if(lfudllNode != null){
            lfudllNode.val = val;
            increaseFreq(lfudllNode);
        }else{
            if(capacity == keyValMap.size()){
                lfudllNode = removeFromFreqMap();
                keyValMap.remove(lfudllNode.key);
            }
            lfudllNode = new LFUDLLNode(key,val);
            minFreq = 1;
            keyValMap.put(key, lfudllNode);
            addNewNodeInFrqMap(lfudllNode);
        }
    }

    private void addNewNodeInFrqMap(LFUDLLNode lfudllNode) {
        LinkedList<LFUDLLNode> minFreqList = freqListMap.get(lfudllNode.freq);
        if(minFreqList == null){
            minFreqList = new LinkedList<>();
        }
        minFreqList.addLast(lfudllNode);
        freqListMap.put(lfudllNode.freq, minFreqList);

    }

    private LFUDLLNode removeFromFreqMap() {
        LinkedList<LFUDLLNode> freqListNode = freqListMap.get(minFreq);
        if(freqListNode.size() == 1){
            freqListMap.remove(minFreq);
        }
        LFUDLLNode removeLRUNode = freqListNode.removeFirst();
        return removeLRUNode;
    }

    private void increaseFreq(LFUDLLNode lfudllNode) {
        int currentFreq = lfudllNode.freq;
        LinkedList<LFUDLLNode> currentFreqList = freqListMap.get(currentFreq);
        currentFreqList.remove(lfudllNode);
        if(currentFreqList.isEmpty()){
            freqListMap.remove(currentFreq);
            if(currentFreq == minFreq){
                minFreq++;
            }
        }
        lfudllNode.freq++;
        addNewNodeInFrqMap(lfudllNode);
    }


    /*
    * get the key from keyVal map
    * if not present return -1
    *
    * increase the key freq into freqMap
    * return val from keyVal map
    * */
    public Integer get(int key){
        LFUDLLNode lfudllNode = keyValMap.get(key);
        if(lfudllNode == null){
            return -1;
        }
        increaseFreq(lfudllNode);
        return lfudllNode.val;
    }
}
