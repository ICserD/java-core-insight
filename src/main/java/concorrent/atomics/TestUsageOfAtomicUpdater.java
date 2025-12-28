package concorrent.atomics;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestUsageOfAtomicUpdater {
    public static void main(String[] args) {
        Student student = new Student();

        AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");

        updater.compareAndSet(student, 0, 10);

        System.out.println(student.age);
    }
}
