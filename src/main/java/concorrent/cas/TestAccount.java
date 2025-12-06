package concorrent.cas;

public class TestAccount {
    public static void main(String[] args) throws InterruptedException {
        Account accountUnSafe = new AccountUnsafe(10000);

        Account.demo(accountUnSafe);
    }
}