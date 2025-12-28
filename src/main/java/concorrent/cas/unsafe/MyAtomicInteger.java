package concorrent.cas.unsafe;

import sun.misc.Unsafe;

public class MyAtomicInteger {
    private volatile int value;
    private static final long VALUE_OFFSET;
    private static final Unsafe UNSAFE;

    static {
        UNSAFE = UnsafeAccessor.getUnsafe();
        try {
            VALUE_OFFSET = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public MyAtomicInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean compareAndSet(int expect, int update) {
        return UNSAFE.compareAndSwapInt(this, VALUE_OFFSET, expect, update);
    }
}
