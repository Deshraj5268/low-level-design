package cachingsystem.lfu.lfudll;

public class LFUDDLDriver {
    public static void main(String[] args) {
        LFUUsingDLL lfu = new LFUUsingDLL(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println("get :"+lfu.get(1));      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println("get :"+lfu.get(2));      // return -1 (not found)
        System.out.println( "get :"+lfu.get(3));      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println("get :"+lfu.get(1));      // return -1 (not found)
        System.out.println("get :"+lfu.get(3));      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println("get :"+lfu.get(4));      // return 4
    }
}
