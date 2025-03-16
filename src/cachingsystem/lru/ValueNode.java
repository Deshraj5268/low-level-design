package cachingsystem.lru;

public class ValueNode {

    private int key; // redundant but while remove old key from hashmap it's required for searching
    private int value;


    public ValueNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
