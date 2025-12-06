package concorrent.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

public interface Account {

    Integer getBalance();

    void withDraw(Integer amount);

    /**
     * start 1000 threads to withdraw money from an account
     */
    static void demo(Account account) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 1000; i++){
            threads.add(new Thread(() -> {
                account.withDraw(10);
            }, "thread" + i));
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Now account leave money " + account.getBalance());
    }
}
