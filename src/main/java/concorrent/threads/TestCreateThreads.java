package concorrent.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class TestCreateThreads {

    private static class SubThread extends Thread {
        public SubThread(String name){
            super(name);
        }
        @Override
        public void run(){
            log.info("Create new thread by subclass, thread name : {}", this.getName());
        }
    }

    private static void createThreadByThreadClass(){
        SubThread subThread = new SubThread("SubThread");
        subThread.start();
    }

    private static void createThreadByRunnableInterface(){
        Runnable runnable = () -> {
            log.info("thread2 start");
        };

        Thread thread2 = new Thread(runnable, "thread2");
        thread2.start();
    }

    private static void createThreadByFutureTask(){
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>(){
            @Override
            public String call(){
                log.info("thread3 start");
                return "thread3 result";
            }
        });

        Thread thread3 = new Thread(futureTask, "thread3");
        thread3.start();

        try {
            log.info("check thread3 result: {}", futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        createThreadByThreadClass();
//        createThreadByRunnableInterface();
//        createThreadByFutureTask();
        log.info("main thread");
    }
}
