package concorrent.cas;

import lombok.AllArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class AccountCasSafe implements Account {
    private AtomicInteger balance;

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withDraw(Integer amount) {
        while(true){
            int prev = balance.get();
            int next = prev - amount;
            if(balance.compareAndSet(prev, next)){
                break;
            }
        }
    }
}
