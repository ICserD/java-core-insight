package concorrent.objectLock;

public class UsageOfSynchronized {

    static int resource = 0;

    static void useObjectLock(){
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                synchronized (lock) {
                    resource++;
                    System.out.println(resource);
                }
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                synchronized (lock) {
                    resource--;
                    System.out.println(resource);
                }
            }
        }, "Thread2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(resource);
    }

    static void useClassObjectLock(){
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                synchronized (UsageOfSynchronized.class) {
                    resource++;
                    System.out.println(resource);
                }
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                synchronized (UsageOfSynchronized.class) {
                    resource--;
                    System.out.println(resource);
                }
            }
        }, "Thread2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(resource);
    }

    public static void main(String[] args) {

//        useObjectLock();

        useClassObjectLock();

    }
}
