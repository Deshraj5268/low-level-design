package messagequeuingsystem.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MessageQueue {
    private String queueName;
    private int id;
    public static int sequence;
    private LinkedList<Message> messages;

    private Set<String> subscribers;
    List<Message> recentMessages;
    private Set<String> messageIds;


    public MessageQueue(String queueName){
        this.queueName = queueName;
        this.messages = new LinkedList<>();
        this.id = sequence++;
        this.subscribers = new HashSet<>();
        messageIds = new HashSet<>();// can think
        this.recentMessages = new LinkedList<>();
    }

    public String getQueueName() {
        return queueName;
    }

    public List<Message> getRecentMessages() {
        return recentMessages;
    }

    public Set<String> getMessageIds() {
        return messageIds;
    }

    public  int getId() {
        return id;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public static int getSequence() {
        return sequence;
    }

    public Set<String> getSubscribers() {
        return subscribers;
    }

    @Override
    public String toString() {
        return "MessageQueue{" +
                "queueName='" + queueName + '\'' +
                ", id=" + id +
                ", messages=" + messages +
                ", subscribers=" + subscribers +
                '}';
    }
}
