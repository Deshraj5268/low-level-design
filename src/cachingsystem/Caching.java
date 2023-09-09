package cachingsystem;

public interface Caching {

    void put(int key,int value);
    int get(int key);

    default int delete(int key){ // optional
        return -1;
    }

}
