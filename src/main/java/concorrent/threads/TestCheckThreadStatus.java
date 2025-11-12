package concorrent.threads;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class TestCheckThreadStatus {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while(true){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "loop thread");

        thread.start();

//        ThreadPoolExecutor
//        AbstractQueuedSynchronizer

//        ThreadPoolExecutor
    }
}
