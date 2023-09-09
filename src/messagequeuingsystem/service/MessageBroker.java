package messagequeuingsystem.service;


import messagequeuingsystem.model.Message;
import messagequeuingsystem.model.MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageBroker {

    private Map<String, MessageQueue> queues = new ConcurrentHashMap<>();

    public String createQueue(String queueName){
        MessageQueue messageQueue = new MessageQueue(queueName);
        // duplicate queue --
        queues.put(queueName,messageQueue);
        System.out.println(messageQueue);
        return messageQueue.getQueueName();
    }

    public boolean subscribe(String queueName,String consumer){
        MessageQueue messageQueue = queues.get(queueName);
        if(messageQueue != null) {
            messageQueue.getSubscribers().add(consumer);
            return true;
        }
        return false;
    }

    public boolean publish(String queueName, List<Message> messages){
        MessageQueue messageQueue = queues.get(queueName);
        /* if(messageQueue != null){

            // adding message adding
            messageQueue.getMessages().addAll(messages);
            notifySubscriber(messageQueue);
            return true;
        }
        return false;*/
       return messageQueueingHandling(queueName,messages);

    }

    public boolean messageQueueingHandling(String queueName,List<Message> messages){
        MessageQueue messageQueue = queues.get(queueName);
        if(messageQueue != null) {
            for (Message message : messages) {
                if (!messageQueue.getMessageIds().contains(message.getMessageId())){//handle duplicate messages
                    messageQueue.getMessageIds().add(message.getMessageId());
                    messageQueue.getMessages().add(message);
                    messageQueue.getRecentMessages().add(message);
                    if(messageQueue.getRecentMessages().size() > 10){ // think for recent messges priority queue etc .. short time
                        messageQueue.getRecentMessages().remove(0);
                    }
                    notifySubscriber(messageQueue);
                }
            }
            return true;
        }
        return false;
    }

    public void notifySubscriber(MessageQueue messageQueue){
       for(String subscriber:messageQueue.getSubscribers()){
           //notify subscriber about messages // fan-out
       }
    }

    public boolean readOffset(String queueName,String subscriber,int offset){
        MessageQueue messageQueue = queues.get(queueName);
        if(messageQueue != null && messageQueue.getSubscribers().contains(subscriber)){
            List<Message> messagesToRead = new ArrayList<>();
            int totalMessages = messageQueue.getMessages().size();
            if(offset < totalMessages){
                messagesToRead.addAll(messageQueue.getMessages().subList(offset,totalMessages));
                acknowledgeMessages(messageQueue,messagesToRead,subscriber);
            }
            return true;
        }
        return false;
    }

    public void acknowledgeMessages(MessageQueue messageQueue,List<Message> messages,String subscriber){
        System.out.println("subscriber : "+subscriber);
        messages.stream().forEach(msg-> System.out.println(msg));
    }
}
