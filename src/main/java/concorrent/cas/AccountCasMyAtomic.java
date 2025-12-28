package concorrent.cas;

import concorrent.cas.unsafe.MyAtomicInteger;

public class AccountCasMyAtomic implements Account{
    private MyAtomicInteger balance;

    public AccountCasMyAtomic(int balance) {
        this.balance = new MyAtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.getValue();
    }

    @Override
    public void withDraw(Integer amount) {
        while(true) {
            int prev = balance.getValue();
            int next = prev - amount;
            if(balance.compareAndSet(prev, next)) break;
        }
    }
}
