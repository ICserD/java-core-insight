package concorrent.cas;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class AccountUnsafe implements Account{

    private int balance;

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void withDraw(Integer amount) {
        balance -= amount;
    }
}
