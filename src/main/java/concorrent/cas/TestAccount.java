package concorrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAccount {
    public static void main(String[] args) throws InterruptedException {
        Account accountUnSafe = new AccountUnsafe(10000);
        Account accountSynchronizedSafe = new AccountSynchronizedSafe(10000);
        Account accountCasSafe = new AccountCasSafe(new AtomicInteger(10000));

        Account.demo(accountUnSafe);
        Account.demo(accountSynchronizedSafe);
        Account.demo(accountCasSafe);
    }
}