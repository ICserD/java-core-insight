package concorrent.cas.atomics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestUsageOfAtomicArray {
    public static void main(String[] args) {
        //  normal array without ability of concurrent
        demo(
                () -> new int[10],
                (array) -> array.length,
                (array, index) -> array[index]++,
                (array) -> System.out.println(Arrays.toString(array))
        );

        //  atomic array
        demo (
                () -> new AtomicIntegerArray(10),
                (array) -> array.length(),
                (array, index) -> array.incrementAndGet(index),
                (array) -> System.out.println(array)
        );

    }

    private static <T> void demo(
            Supplier<T> supplier,
            Function<T, Integer> function,
            BiFunction<T, Integer, Integer> biFunction,
            Consumer<T> consumer
    ) {
        T array = supplier.get();
        int length = function.apply(array);
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < length; i++) {
            threads.add(new Thread(() -> {
                   for(int j = 0; j < 10000; j++) {
                       biFunction.apply(array, j % length);
                   }
                }));
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        consumer.accept(array);
    }
}
