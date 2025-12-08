package concorrent.cas;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountSynchronizedSafe implements Account{

    private int balance;

    @Override
    public Integer getBalance() {
        return 0;
    }

    @Override
    public void withDraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
}
