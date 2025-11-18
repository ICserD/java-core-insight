package concorrent.objectLock.producerAndComsumerDesignPattern;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Deque;
import java.util.LinkedList;

@Data
@AllArgsConstructor
public class MessageQueue {

    private final Deque<Message> messageQueue = new LinkedList<>();
    private int capacity;

    public Message take(){
        synchronized (messageQueue){
            while(messageQueue.isEmpty()){
                try {
                    messageQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException("The thread for taking message has been interrupted, error : ", e);
                }
            }

            Message message = messageQueue.pollFirst();

            messageQueue.notifyAll();

            return message;
        }
    }


    public void put(Message message){
        synchronized (messageQueue){
            while(messageQueue.size() > capacity){
                try {
                    messageQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException("The thread for putting message has been interrupted, error : ", e);
                }
            }

            messageQueue.offerLast(message);

            messageQueue.notifyAll();
        }
    }

}
