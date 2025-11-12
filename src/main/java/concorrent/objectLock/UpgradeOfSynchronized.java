package concorrent.objectLock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class UpgradeOfSynchronized {
    private final Object lock = new Object();
    private Resource resource;

    @Data
    public static class Resource {
        private int count;
        private String name;

        public void increaseCount(){
            count++;
        }
    }

    public void noCompetition(){
        new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                synchronized (lock){
                    resource.increaseCount();
                }
            }
        }).start();
    }

    public void alternativelyExecute(){
        int MAX_VALUE = 100;

        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (lock) {
                        if (resource.getCount() > MAX_VALUE) break;
                        log.info("{} : {}", Thread.currentThread().getName(), resource.getCount());
                        resource.increaseCount();
                    }
                    try {
                        Thread.sleep(50);  //  释放锁之后睡会，不去竞争，让其他线程有机会拿到锁
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "Thread_" + i).start();
        }
    }


    public void autoCompetition(){
        for(int i = 0; i < 1000; i++){
            new Thread(() -> {
                synchronized (lock){
                    resource.increaseCount();
                    log.info("{} : {}", Thread.currentThread().getName(), resource.getCount());
                }
            }).start();
        }
    }
}
