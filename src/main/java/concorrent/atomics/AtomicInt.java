package concorrent.atomics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class AtomicInt {
    private AtomicInteger value;

    public AtomicInt(int value) {
        this.value = new AtomicInteger(value);
    }

    public int getInt() {
        return value.get();
    }

    public void compareAndSet(IntUnaryOperator operator) {
        while(true) {
            int prev = this.value.get();
            int next = operator.applyAsInt(prev);
            if(value.compareAndSet(prev, next)){
                break;
            }
        }
    }

}
