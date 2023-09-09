package messagequeuingsystem;


import messagequeuingsystem.model.Message;
import messagequeuingsystem.service.MessageBroker;

import java.util.ArrayList;
import java.util.List;

public class PubSubDriver {
    public static void main(String[] args) {

        MessageBroker messageBroker = new MessageBroker();
        String queueName = "myFirstQueue";
        String consumer1 = "consumer1";
        String queueNameId = messageBroker.createQueue(queueName);
        messageBroker.subscribe(queueNameId,consumer1);
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("message1"));
        messageList.add(new Message("message2"));

        messageBroker.publish(queueNameId,messageList);

        messageBroker.readOffset(queueNameId,consumer1,0);
    }
}