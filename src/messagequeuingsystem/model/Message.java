package messagequeuingsystem.model;

import java.util.Objects;
import java.util.UUID;

public class Message {

    private String content;
    private long timeStamp;
    String messageId;

  /*  public Message(String content) {
        this.content = content;
    }*/


    public Message(String content) {
        this.content = content;
        this.timeStamp = System.currentTimeMillis();
        this.messageId = UUID.randomUUID().toString();// as of nwo thinking
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                ", messageId='" + messageId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return timeStamp == message.timeStamp && Objects.equals(content, message.content) && Objects.equals(messageId, message.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, timeStamp, messageId);
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
