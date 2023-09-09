package cachingsystem.lfu;

public class DoublyLinkedListOps {

    int n;
    Node head,tail;

    public void add(Node node){
        if(head == null){
           tail= head = node;
        }else{
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        n++;
    }

    public void remove(Node node){
        if(node == null){
            return ;
        }
        if(node.next == null){
            tail = node.prev;
        }else {
            node.next.prev = node.prev; // last node to prev of curr
        }
        if(node.prev == null){
            head = node.next; // head is deleting
        }else {
            node.prev.next = node.next;
        }
        n--;
    }

    public int getN() {
        return n;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
}
