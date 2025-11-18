package concorrent.objectLock.producerAndComsumerDesignPattern;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class TestMessageQueue {
    public static void main(String[] args) {
        MessageQueue msgQue = new MessageQueue(5);
        for(int i = 0; i < 7; i++){
            new Thread(() -> {
                log.info("{} try to take......", Thread.currentThread().getName());
                Message res = msgQue.take();
                log.info("{} take the res {}", Thread.currentThread().getName(), res);
            }, "消费者" + i).start();
        }

        for(int i = 0; i < 7; i++){
            int idx = i;
            new Thread(() -> {
                log.info("{} try to produce......", Thread.currentThread().getName());
                Message msg = new Message(idx, "内容" + idx);
                msgQue.put(msg);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("{} produce message {}", Thread.currentThread().getName(), msg);
            }, "生产者" + i).start();
        }
    }
}
