package cachingsystem.lfu.lfudll;

public class LFUDLLNode {

    int key;
    int val;
    int freq;

    public LFUDLLNode(int key, int val){
        this.freq = 1;
        this.key = key;
        this.val = val;
    }
}
