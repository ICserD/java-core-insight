package concorrent.parker;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;
import static java.util.concurrent.locks.LockSupport.*;

@Slf4j
public class UsageOfParker {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("{} ready to invoke park()", Thread.currentThread().getName());

            park();
            log.info("{} wake up from invoking unpark() by other thread", Thread.currentThread().getName());
        });

        Thread t2 = new Thread(() -> {
            log.info("{} ready to invoke unpark()", Thread.currentThread().getName());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            unpark(t1);
            log.info("{} invoked unpark to wake up thread t1", Thread.currentThread().getName());
        });

        t1.start();
        t2.start();

    }
}
