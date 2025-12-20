package concorrent.cas;

import concorrent.atomics.AtomicInt;

public class AccountCasAtomicInt implements Account{
    private final AtomicInt balance;

    public AccountCasAtomicInt(int balance) {
        this.balance = new AtomicInt(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.getInt();
    }

    @Override
    public void withDraw(Integer amount) {
        balance.compareAndSet(x -> x - amount);
    }
}
