package concorrent.threadOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class OrderedPrintUsingWaitNotify {
    public void print(String outputStr, int expectedState, int nextState){
        for(int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (state != expectedState) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                log.info("{} print {}", Thread.currentThread().getName(), outputStr);
                setState(nextState);
                notifyAll();
            }
        }
    }

    private int state;
    private int loopNumber;
}